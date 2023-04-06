package com.example.examcontrol.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
Класс конфигурации Spring Security. Необходим для обеспечения безопасности.
А конкретно, модификацией доступа к страницам в зависимости от роли, требование аутендификации при входе,
выдача страницы авторизации и тд.
*/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private  final UserDetailsService userDetailsService;

    //Bean для аутентификации пользователей из базы данных
    @Autowired
    public SecurityConfig(@Qualifier ("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    //Основной конфигуратор
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf ().disable ()
                .authorizeRequests ()
                .anyRequest ()
                .authenticated ()
                .and()
                .formLogin().permitAll ()
                 .defaultSuccessUrl ("/observation", true)
                .and()
                .logout ()
                .logoutRequestMatcher (new AntPathRequestMatcher ("/logout", "GET"))
                .invalidateHttpSession (true)
                .clearAuthentication (true)
                .deleteCookies ("JSESSIONID")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }

    //Указываем, что аутентификация происходит с помощью базы данных
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider (daoAuthenticationProvider ());
    }

    //пароли хранятся в закодированном виде
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder (12);
    }


    //Метод для обеспечения аутентификации с помощью БД
    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider ();
        daoAuthenticationProvider.setPasswordEncoder (passwordEncoder ());
        daoAuthenticationProvider.setUserDetailsService (userDetailsService );
        return daoAuthenticationProvider;
    }
}
