package com.example.examcontrol.Controllers;

import com.example.examcontrol.Models.Camera;
import com.example.examcontrol.Services.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final CameraService cameraService;

    @Autowired
    public MainController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping("/")
    public String mainPage(){
        return "/index";
    }
}
