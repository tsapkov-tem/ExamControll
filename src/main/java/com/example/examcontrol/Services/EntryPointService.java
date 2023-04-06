package com.example.examcontrol.Services;

import com.example.examcontrol.Repositories.NotificationRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py4j.GatewayServer;

@Service
public class EntryPointService {

    //Открываем порт Java-Python
    @Autowired
    public EntryPointService(PhotoService photoService, CameraService cameraService, NotificationRepos notificationRepos){
        GatewayServer gatewayServer = new GatewayServer(new EntryPoint (photoService, cameraService, notificationRepos));
        gatewayServer.start();
        System.out.println("Gateway Server Started");
    }
}
