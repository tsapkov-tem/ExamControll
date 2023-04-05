package com.example.examcontrol.Controllers;

import com.example.examcontrol.Models.Notification;
import com.example.examcontrol.Repositories.NotificationRepos;
import com.example.examcontrol.Services.EntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {
    private final NotificationRepos notificationRepos;
    @Autowired
    public NotificationController(NotificationRepos notificationRepos) {
        this.notificationRepos = notificationRepos;
    }

    @GetMapping("/request")
    public String request(@RequestParam(value="ids") String idString){
        List<String> ids = List.of(idString.split(","));
        List<Notification> notifications = notificationRepos.findAllNotificationByViewedAndIdIn(false, ids);
        if(!notifications.isEmpty()) {
            Notification notification = notifications.get(0);
            notification.setViewed(true);
            notificationRepos.save(notification);
            return notification.getInfo();
        }
        return "Нет замечаний";
    }

    @GetMapping("/accessDenied")
    public String denied(){
        return "У вас нет доступа";
    }
}
