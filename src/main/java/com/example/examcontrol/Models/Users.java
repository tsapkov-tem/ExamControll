package com.example.examcontrol.Models;
import lombok.Data;
import org.springframework.data.annotation.Id;

//Сущность пользователя
@Data
public class Users {
    @Id
    private String idUser;
    private String username;
    private String password;
    private Role role;
}
