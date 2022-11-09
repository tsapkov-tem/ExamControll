package com.example.examcontrol.Services;

import org.springframework.stereotype.Service;
import py4j.GatewayServer;

@Service
public class EntryPointService {
    public EntryPointService(){
        GatewayServer gatewayServer = new GatewayServer(new EntryPoint (photoService, cameraService));
        gatewayServer.start();
        System.out.println("Gateway Server Started");
    }
}
