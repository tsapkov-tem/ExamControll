package com.example.examcontrol.Services;

import com.example.examcontrol.Models.Photo;
import com.example.examcontrol.Repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//Сервис работы с фото
@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void save(Photo photo){
        photoRepository.save (photo);
    }

    public void deleteById(String id){
        photoRepository.deleteById (id);
    }

    public List<Photo> getAllByIdCam(String idCam){
        return photoRepository.findAllByIdCam (idCam) ;
    }

    public Optional<Photo> gitById(String id){
        return photoRepository.findById (id);
    }
}
