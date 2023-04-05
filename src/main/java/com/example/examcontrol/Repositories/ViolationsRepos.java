package com.example.examcontrol.Repositories;

import com.example.examcontrol.Models.Violations;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ViolationsRepos extends CrudRepository<Violations, String> {
    @Override
    List<Violations> findAll();
    List<Violations> findAllByCity(String city);
    List<Violations> findAllBySchool(String School);
    List<Violations> findAllByAuditorium(String auditorium);
}
