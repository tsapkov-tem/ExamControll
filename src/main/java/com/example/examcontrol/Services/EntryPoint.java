package com.example.examcontrol.Services;

import com.example.examcontrol.Models.Camera;
import com.example.examcontrol.Models.Notification;
import com.example.examcontrol.Models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.util.Optional;

public class EntryPoint {

    @Autowired
    private PhotoService photoService;
    private CameraService cameraService;
    private SimpMessageSendingOperations sendingOperations;
    @MessageMapping("/topic/public")
    public void phoneDetected(String idCam, String time, String path){
        Photo photo = Photo.builder ()
                .idScreen (idCam + time).
                idCam (idCam).
                path(path).
                time (time).build ();
        photoService.save (photo);

        Notification notification = new Notification();
        notification.setId(idCam);
        notification.setInfo("На камере " + idCam + " замечен телефон");
        sendingOperations.convertAndSend("/topic/public", notification);
    }

    @MessageMapping("/topic/public")
    public void moveDetected(String idCam){
        Optional<Camera> camera = cameraService.getById (idCam);
        System.out.println(camera.get().getCity());
        Notification notification = new Notification();
        notification.setId(idCam);
        notification.setInfo("На камере " + idCam + " замечены странные действия");
        sendingOperations.convertAndSend("/topic/public", notification);
    }
}
