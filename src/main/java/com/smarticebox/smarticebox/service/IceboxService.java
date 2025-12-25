package com.smarticebox.smarticebox.service;

import com.smarticebox.smarticebox.model.IceboxStatus;
import com.smarticebox.smarticebox.repository.IceboxRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
public class IceboxService {

    private final IceboxRepository repository;

    public IceboxService(IceboxRepository repository) {
        this.repository = repository;
    }

    public IceboxStatus evaluateStatus(double temperature, double humidity, boolean doorOpen) {

        String safetyStatus;
        String alertMessage = "No Alert";

        if (temperature > 8) {
            safetyStatus = "UNSAFE";
            alertMessage = "Temperature Alert: Overheat (Above 8°C)";
        } else if (temperature < 2) {
            safetyStatus = "UNSAFE";
            alertMessage = "Temperature Alert: Undercool (Below 2°C)";
        } else {
            safetyStatus = "SAFE";
        }

        IceboxStatus status = new IceboxStatus(
                temperature,
                humidity,
                doorOpen,
                safetyStatus,
                alertMessage
        );

        // ✅ SAVE TO MYSQL
        return repository.save(status);
    }

    // ✅ HISTORY FROM MYSQL
    public List<IceboxStatus> getHistory() {
        LocalDateTime sevenDaysAgo =
                LocalDateTime.now().minus(7, ChronoUnit.DAYS);

        return repository.findByCreatedAtAfterOrderByCreatedAtDesc(sevenDaysAgo);
    }
}
