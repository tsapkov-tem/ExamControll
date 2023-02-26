package com.example.examcontrol.Controllers;

import com.example.examcontrol.Models.Notification;
import com.example.examcontrol.Services.EntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
public class NotificationController {
    @Autowired
    private final EntryPoint entryPoint;

    public NotificationController(EntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @GetMapping("/request")
    public String request(){
        System.out.println("Get");
        if(!entryPoint.notifications.isEmpty())
        return entryPoint.notifications.poll().getInfo();
        return "Нет замечаний";
    }

}
