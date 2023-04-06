package com.example.examcontrol.Services;

import com.example.examcontrol.Models.Violations;
import com.example.examcontrol.Repositories.ViolationsRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//Сервис работы с нарушениями
@Service
public class ViolationsService {

    private final ViolationsRepos violationsRepos;


    @Autowired
    public ViolationsService(ViolationsRepos violationsRepos) {
        this.violationsRepos = violationsRepos;
    }

    public void save(Violations violations){
        violationsRepos.save (violations);
    }

    public void deleteById(String id){
        violationsRepos.deleteById (id);
    }

    public List<Violations> getAll(){
        return violationsRepos.findAll();
    }

    public Optional<Violations> getById(String id){
        return violationsRepos.findById (id);
    }

    public List<Violations> getByCity(String city){
        return violationsRepos.findAllByCity (city);
    }

    public List<Violations> getBySchool(String school){
        return violationsRepos.findAllBySchool (school);
    }

    public List<Violations> getByAuditorium(String auditorium) {
        return violationsRepos.findAllByAuditorium(auditorium);
    }
}
