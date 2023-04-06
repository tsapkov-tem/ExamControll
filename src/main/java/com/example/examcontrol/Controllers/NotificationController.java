package com.example.examcontrol.Controllers;

import com.example.examcontrol.Models.Notification;
import com.example.examcontrol.Repositories.NotificationRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
Контроллер, для оповещений
Rest controller, так как оповещения передаются по Ajax, поэтому методы возвращают String значение
*/

@RestController
public class NotificationController {
    private final NotificationRepos notificationRepos;
    @Autowired
    public NotificationController(NotificationRepos notificationRepos) {
        this.notificationRepos = notificationRepos;
    }


    //Приходит запрос от клиента, если по заданным параметрам есть оповещение, он его получает
    @GetMapping("/request")
    public String request(@RequestParam(value="ids") String idString){
        List<String> ids = List.of(idString.split(",")); //На вход у нас строка из id
        List<Notification> notifications = notificationRepos.findAllNotificationByViewedAndIdCamIn(false, ids); //Требуются только
        //те оповещения, которые еще не показывались, и айди камер которых совпадают с теми камерами, за которыми пользователь наблюдает
        //в данный момент
        if(!notifications.isEmpty()) { //Если таких замечаний нет
            Notification notification = notifications.get(0);
            notification.setViewed(true);
            notificationRepos.save(notification);
            return notification.getInfo(); //Если есть
        }
        return "Нет замечаний";
    }


    //Когда пытаются получить доступ к странице, которая не доступна из за аутентификации
    @GetMapping("/accessDenied")
    public String denied(){
        return "У вас нет доступа";
    }
}
