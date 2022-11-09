package com.example.examcontrol.Services;

import com.example.examcontrol.Models.Camera;
import com.example.examcontrol.Models.Photo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EntryPoint {

    private final PhotoService photoService;
    private final CameraService cameraService;

    @Autowired
    public EntryPoint(PhotoService photoService, CameraService cameraService) {
        this.photoService = photoService;

        this.cameraService = cameraService;
    }

    public void phoneDetected(String time, String idCam, String idScreen){
        Photo photo = Photo.builder ()
                .idScreen (idScreen).
                idCam (idCam).
                time (time).build ();
        photoService.save (photo);
        //todo
    }

    public void moveDetected(String idCam){
        Optional<Camera> camera = cameraService.getById (idCam);
        //todo
    }
}
