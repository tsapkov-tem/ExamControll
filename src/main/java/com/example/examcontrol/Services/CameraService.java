package com.example.examcontrol.Services;

import com.example.examcontrol.Models.Camera;
import com.example.examcontrol.Repositories.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//Сервис для работы с камерами
@Service
public class CameraService {

    private final CameraRepository cameraRepository;

    @Autowired
    public CameraService(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    public void save(Camera camera){
        cameraRepository.save (camera);
    }

    public void deleteById(String id){
        cameraRepository.deleteById (id);
    }

    public List<Camera> getAll(){
        return cameraRepository.findAll();
    }

    public Optional<Camera> getById(String id){
        return cameraRepository.findById (id);
    }

    public List<Camera> getByCity(String city){
        return cameraRepository.findAllByCity (city);
    }

    public List<Camera> getBySchool(String school){
        return cameraRepository.findAllBySchool (school);
    }

    public List<Camera> getByAuditorium(String auditorium){
        return cameraRepository.findAllByAuditorium (auditorium) ;
    }
}
