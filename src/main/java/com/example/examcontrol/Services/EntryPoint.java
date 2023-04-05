package com.example.examcontrol.Services;

import com.example.examcontrol.Models.Camera;
import com.example.examcontrol.Models.Notification;
import com.example.examcontrol.Models.Photo;
import com.example.examcontrol.Repositories.NotificationRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryPoint {

    private final PhotoService photoService;
    private final CameraService cameraService;
    private final NotificationRepos notificationRepos;

    @Autowired
    public EntryPoint(PhotoService photoService, CameraService cameraService, NotificationRepos notificationRepos) {
        this.photoService = photoService;
        this.cameraService = cameraService;
        this.notificationRepos = notificationRepos;
    }

    public Notification phoneDetected(String idCam, String time, String path){
        Photo photo = Photo.builder ()
                .idScreen (idCam + time).
                idCam (idCam).
                path(path).
                time (time).build ();
        photoService.save (photo);

        Notification notification = new Notification();
        Camera camera = cameraService.getById(idCam).orElse(null);
        try {
            notification.setInfo("На камере города" + camera.getCity() + "Школы номер " + camera.getSchool() + "Аудитории " + camera.getAuditorium() + " замечен телефон");
            notification.setViewed(false);
            notificationRepos.save(notification);
            return notification;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Notification moveDetected(String idCam){
        Notification notification = new Notification();
        Camera camera = cameraService.getById(idCam).orElse(null);
        try {
            notification.setInfo("На камере города" + camera.getCity() + "Школы номер " + camera.getSchool() + "Аудитории " + camera.getAuditorium() + " замечены странные действия");
            notification.setViewed(false);
            notificationRepos.save(notification);
            return notification;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
