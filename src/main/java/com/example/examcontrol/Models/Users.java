package com.example.examcontrol.Models;
import lombok.Data;

@Data
public class Users {
    private String idUser;
    private String username;
    private String password;
    private Role role;
}
