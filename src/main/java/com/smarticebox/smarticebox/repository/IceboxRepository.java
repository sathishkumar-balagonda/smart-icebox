package com.smarticebox.smarticebox.repository;

import com.smarticebox.smarticebox.model.IceboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDateTime;
import java.util.List;

public interface IceboxRepository extends JpaRepository<IceboxStatus, Long> {

    List<IceboxStatus> findByCreatedAtAfterOrderByCreatedAtDesc(
            LocalDateTime time
    );
}