package com.example.examcontrol.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

//Сущность фото, в самой программе не используется, но нужна для работы с фотографиями и базой данных
//Чтобы понимать, какая фотография относится к какой камере
@Data
@Builder
public class Photo {
    @Id
    private String idScreen;
    private String time;
    private String idCam;
    private String path;
}
