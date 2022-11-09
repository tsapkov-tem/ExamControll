package com.example.examcontrol.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Camera {
    @Id
    private String idCam;
    private String city;
    private String school;
    private int auditorium;
    private int numberCamera;
}
