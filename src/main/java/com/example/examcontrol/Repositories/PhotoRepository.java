package com.example.examcontrol.Repositories;

import com.example.examcontrol.Models.Photo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> {
    List<Photo> findAllByIdCam(String idCam);
}
