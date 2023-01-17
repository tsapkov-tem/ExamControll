package com.example.examcontrol.Services;

import com.example.examcontrol.Models.Notification;
import com.example.examcontrol.Models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class EntryPoint {

    public static LinkedList<Notification> notifications = new LinkedList<>();
    private final PhotoService photoService;

    @Autowired
    public EntryPoint(PhotoService photoService) {
        this.photoService = photoService;
    }

    public Notification phoneDetected(String idCam, String time, String path){
        Photo photo = Photo.builder ()
                .idScreen (idCam + time).
                idCam (idCam).
                path(path).
                time (time).build ();
        photoService.save (photo);

        Notification notification = new Notification();
        notification.setId(idCam);
        notification.setInfo("На камере " + idCam + " замечен телефон");
        notifications.add(notification);
        System.out.println("phone");
        return notification;
    }

    public Notification moveDetected(String idCam){
        Notification notification = new Notification();
        notification.setId(idCam);
        notification.setInfo("На камере " + idCam + " замечены странные действия");
        System.out.println(notification.getInfo());
        notifications.add(notification);
        return notification;
    }
}
