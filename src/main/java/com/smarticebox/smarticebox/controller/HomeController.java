package com.smarticebox.smarticebox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Smart Icebox Backend is Running!";
    }

    @GetMapping("/status")
    public String status() {
        return "Temperature SAFE | Door LOCKED | Cloud CONNECTED";
    }
}
