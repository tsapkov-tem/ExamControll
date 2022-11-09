package com.example.examcontrol.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Photo {
    @Id
    private String idScreen;
    private String time;
    private String idCam;
    private String path;
}
