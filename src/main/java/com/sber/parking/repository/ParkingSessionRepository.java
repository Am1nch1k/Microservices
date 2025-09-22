package com.sber.parking.repository;

import com.sber.parking.entity.ParkingSession;
import com.sber.parking.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    boolean existsByVehicleAndExitTimeIsNull(Vehicle vehicle);

    Optional<ParkingSession> findByVehicleAndExitTimeIsNull(Vehicle vehicle);

    @Query("SELECT COUNT(p) FROM ParkingSession p WHERE p.entry_time BETWEEN :start AND :end")
    long countEntriesBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(p) FROM ParkingSession p WHERE p.exit_time BETWEEN :start AND :end")
    long countExitsBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query(value = """
    SELECT SUM(EXTRACT(EPOCH FROM (exit_time - entry_time)) / 60)
    FROM parking_sessions
    WHERE exit_time IS NOT NULL AND entry_time BETWEEN :start AND :end
    """, nativeQuery = true)
    Long averageParkingDurationMinutes(@Param("start") LocalDateTime start,
                                       @Param("end") LocalDateTime end);
}