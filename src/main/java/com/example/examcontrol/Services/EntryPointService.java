package com.example.examcontrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py4j.GatewayServer;

@Service
public class EntryPointService {
    private final PhotoService photoService;
    private final CameraService cameraService;


    @Autowired
    public EntryPointService(PhotoService photoService, CameraService cameraService){
        this.cameraService = cameraService;
        GatewayServer gatewayServer = new GatewayServer(new EntryPoint ());
        gatewayServer.start();
        System.out.println("Gateway Server Started");
        this.photoService = photoService;
    }
}
