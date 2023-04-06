package com.example.examcontrol.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;

//Сущность камеры
@Data
public class Camera {
    @Id
    private String idCam;
    private String city;
    private String school;
    private String auditorium;
    private String numberCamera;
}
