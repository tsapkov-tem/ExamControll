package com.example.examcontrol.Models;

import lombok.Data;

//Сущность оповещнения
@Data
public class Notification {
    String id;
    String idCam;
    String info;
    Boolean viewed;
}
