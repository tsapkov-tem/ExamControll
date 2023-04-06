package com.example.examcontrol.Security;

import com.example.examcontrol.Models.Users;
import com.example.examcontrol.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Сервис работы с базой данных. Позволяет использовать данные для авторизации пользователей из бд
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    //Получаем пользователя по логину и сверяем пароль
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersOptional = Optional.ofNullable ((usersRepository.findByUsername (username)));
        Users user = usersOptional.orElseThrow(() -> new UsernameNotFoundException ("User doesn't exist"));
        return UserSecurity.fromUser (user);
    }
}
