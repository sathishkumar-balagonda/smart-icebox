package com.smarticebox.smarticebox.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "icebox_status")
public class IceboxStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperature;
    private boolean doorOpen;
    private String safetyStatus;
    private String alertMessage;
    private double humidity; 
    
    // ✅ REQUIRED by JPA (DO NOT DELETE)
    public IceboxStatus() {
    }

    // ✅ Your constructor (used in service)
    public IceboxStatus(double temperature, double humidity, boolean doorOpen,
                        String safetyStatus, String alertMessage) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.doorOpen = doorOpen;
        this.safetyStatus = safetyStatus;
        this.alertMessage = alertMessage;
    }

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public double getTemperature() {
        return temperature;
    }
    
    public double getHumidity() { 
    	return humidity; 
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public String getSafetyStatus() {
        return safetyStatus;
    }

    public String getAlertMessage() {
        return alertMessage;
    }
    
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
