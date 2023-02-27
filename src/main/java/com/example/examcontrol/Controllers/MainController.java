package com.example.examcontrol.Controllers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/observation")
    @PreAuthorize("hasAnyAuthority('all', 'read')")
    public String observation(){
        return "observation";
    }

    @GetMapping("/violations")
    @PreAuthorize("hasAuthority('all')")
    public String violations(){
        return "violations";
    }
}
