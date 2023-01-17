package com.example.examcontrol.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py4j.GatewayServer;

@Service
public class EntryPointService {
    private final PhotoService photoService;


    @Autowired
    public EntryPointService(PhotoService photoService){
        this.photoService = photoService;
        GatewayServer gatewayServer = new GatewayServer(new EntryPoint (this.photoService));
        gatewayServer.start();
        System.out.println("Gateway Server Started");
    }
}
