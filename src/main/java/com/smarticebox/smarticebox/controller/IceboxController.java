package com.smarticebox.smarticebox.controller;

import com.smarticebox.smarticebox.model.IceboxStatus;
import com.smarticebox.smarticebox.service.IceboxService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class IceboxController {
    private final IceboxService iceboxService;

    public IceboxController(IceboxService iceboxService) {
        this.iceboxService = iceboxService;
    }

    @GetMapping("/update")
    public IceboxStatus updateStatus(
            @RequestParam double temperature,
            @RequestParam double humidity,
            @RequestParam boolean doorOpen
    ) {
        return iceboxService.evaluateStatus(temperature, humidity, doorOpen);
    }
    
    @GetMapping("/history")
    public List<IceboxStatus> history() {
        return iceboxService.getHistory();
    } 

}
