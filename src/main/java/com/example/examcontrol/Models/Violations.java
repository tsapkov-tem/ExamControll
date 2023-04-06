package com.example.examcontrol.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
//Сущность нарушения
@Data
public class Violations {
    @Id
    private String idViolations;
    private String city;
    private String school;
    private String auditorium;
    private String numberCamera;
    private String info;
}

