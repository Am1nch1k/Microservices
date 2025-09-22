package com.sber.parking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingReportResponseDto {

    private long totalEntries;
    private long totalExits;
    private long averageParkingDurationMinutes;
}