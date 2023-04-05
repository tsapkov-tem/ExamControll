package com.example.examcontrol.Controllers;
import com.example.examcontrol.Models.Camera;
import com.example.examcontrol.Models.Violations;
import com.example.examcontrol.Services.CameraService;
import com.example.examcontrol.Services.ViolationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    private final CameraService cameraService;
    private final ViolationsService violationsService;

    @Autowired
    public MainController(CameraService cameraService, ViolationsService violationsService) {
        this.cameraService = cameraService;
        this.violationsService = violationsService;
    }

    @GetMapping("/report")
    @PreAuthorize("hasAnyAuthority('all', 'read')")
    public String report(Model model){
        model.addAttribute("report", new Violations());
        return "report";
    }

    @PostMapping("/report")
    @PreAuthorize("hasAnyAuthority('all', 'read')")
    public String reportSave(Model model, Violations violation){
        model.addAttribute("report", new Violations());
        violationsService.save(violation);
        return "report";
    }

    @GetMapping("/observation")
    @PreAuthorize("hasAnyAuthority('all', 'read')")
    public String observation(Model model){
        List<Camera> cams = cameraService.getAll();
        model.addAttribute("camera", new Camera());
        model.addAttribute("cams", cams);
        return "observation";
    }

    @PostMapping ("/observation/search")
    @PreAuthorize("hasAnyAuthority('all', 'read')")
    public String observationSearch(Model model, Camera camera){
        List<Camera> cams = cameraService.getAll();
        if (!camera.getCity().equals("")){
            cams.retainAll(cameraService.getByCity(camera.getCity()));
        }
        if (!camera.getSchool().equals("")){
            cams.retainAll(cameraService.getBySchool(camera.getSchool()));
        }
        if (!camera.getAuditorium().equals("")){
            cams.retainAll(cameraService.getByAuditorium(camera.getAuditorium()));
        }
        model.addAttribute("camera", new Camera());
        model.addAttribute("cams", cams);
        return "observation";
    }

    @GetMapping("/violations")
    @PreAuthorize("hasAuthority('all')")
    public String violations(Model model){
        List<Violations> vios = violationsService.getAll();
        model.addAttribute("violation", new Violations());
        model.addAttribute("vios", vios);
        return "violations";
    }

    @PostMapping ("/violations/search")
    @PreAuthorize("hasAuthority('all')")
    public String violationsSearch(Model model, Violations violations){
        List<Violations> vios = violationsService.getAll();
        if (!violations.getCity().equals("")){
            vios.retainAll(violationsService.getByCity(violations.getCity()));
        }
        if (!violations.getSchool().equals("")){
            vios.retainAll(violationsService.getBySchool(violations.getSchool()));
        }
        if (!violations.getAuditorium().equals("")){
            vios.retainAll(violationsService.getByAuditorium(violations.getAuditorium()));
        }
        model.addAttribute("violation", new Violations());
        model.addAttribute("vios", vios);
        return "violations";
    }
}
