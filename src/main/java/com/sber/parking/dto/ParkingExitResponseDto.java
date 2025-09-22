package com.sber.parking.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ParkingExitResponseDto {
    private String licensePlate;
    private LocalDateTime exitTime;
}