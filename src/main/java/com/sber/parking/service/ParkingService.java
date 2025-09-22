package com.sber.parking.service;

import com.sber.parking.dto.ParkingEntryRequestDto;
import com.sber.parking.dto.ParkingEntryResponseDto;
import com.sber.parking.dto.ParkingExitRequestDto;
import com.sber.parking.dto.ParkingExitResponseDto;
import com.sber.parking.dto.ParkingReportResponseDto;

import java.time.LocalDateTime;

public interface ParkingService {

    ParkingEntryResponseDto registerEntry(ParkingEntryRequestDto requestDto);

    ParkingExitResponseDto registerExit(ParkingExitRequestDto requestDto);

    ParkingReportResponseDto generateReport(LocalDateTime start, LocalDateTime end);
}
