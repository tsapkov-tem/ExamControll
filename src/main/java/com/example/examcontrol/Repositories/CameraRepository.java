package com.example.examcontrol.Repositories;

import com.example.examcontrol.Models.Camera;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CameraRepository extends MongoRepository<Camera, String> {
    @Override
    List<Camera> findAll();
    List<Camera> findAllByCity(String city);
    List<Camera> findAllBySchool(String School);
    List<Camera> findAllByAuditorium(String auditorium);
}
