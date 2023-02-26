package com.example.examcontrol.Repositories;

import com.example.examcontrol.Models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, String> {
    Users findByUsername(String s);
}
