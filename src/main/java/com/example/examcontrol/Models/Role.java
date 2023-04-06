package com.example.examcontrol.Models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

//Роли пользователей
public enum Role {
    USER(Set.of (Permission.READ)),
    ADMIN(Set.of (Permission.All));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions ().stream ()
                .map(permissions -> new SimpleGrantedAuthority (permissions.getPermission()))
                .collect(Collectors.toSet());
    }
}
