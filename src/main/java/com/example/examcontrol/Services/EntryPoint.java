package com.example.examcontrol.Services;

import com.example.examcontrol.Models.Camera;
import com.example.examcontrol.Models.Notification;
import com.example.examcontrol.Models.Photo;
import com.example.examcontrol.Repositories.NotificationRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//Сервис соединения python и Java
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
    //При засечении на камере телефона на стороне python, Java вызывает этот метод
    public Notification phoneDetected(String idCam, String time, String path){
        Photo photo = Photo.builder () //Заполняем сущность фото
                .idScreen (idCam + time).
                idCam (idCam).
                path(path).
                time (time).build ();
        photoService.save (photo);

        Notification notification = new Notification(); //Сохраняем оповещение
        Camera camera = cameraService.getById(idCam).orElse(null);
        try {
            notification.setInfo("На камере города " + camera.getCity() + " Школы номер " + camera.getSchool() + " Аудитории " + camera.getAuditorium() + " замечен телефон");
            notification.setIdCam(idCam);
            notification.setViewed(false);
            notificationRepos.save(notification);
            return notification;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    //При засечении на камере подозрительных движений на стороне python, Java вызывает этот метод
    public Notification moveDetected(String idCam){
        Notification notification = new Notification(); //Заполняем данные в оповещение
        Camera camera = cameraService.getById(idCam).orElse(null);
        try {
            notification.setInfo("На камере города " + camera.getCity() + " Школы номер " + camera.getSchool() + " Аудитории " + camera.getAuditorium() + " замечены странные действия");
            notification.setIdCam(idCam);
            notification.setViewed(false);
            notificationRepos.save(notification);
            return notification;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
