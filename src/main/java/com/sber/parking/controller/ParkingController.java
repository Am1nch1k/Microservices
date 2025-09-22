package com.sber.parking.controller;

import com.sber.parking.dto.ParkingEntryRequestDto;
import com.sber.parking.dto.ParkingEntryResponseDto;
import com.sber.parking.dto.ParkingExitRequestDto;
import com.sber.parking.dto.ParkingExitResponseDto;
import com.sber.parking.dto.ParkingReportResponseDto;
import com.sber.parking.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
@Tag(name = "Parking Controller", description = "Управление парковкой")
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/entry")
    @Operation(summary = "Регистрация въезда автомобиля")
    public ParkingEntryResponseDto registerEntry(@RequestBody ParkingEntryRequestDto requestDto) {
        return parkingService.registerEntry(requestDto);
    }

    @PostMapping("/exit")
    @Operation(summary = "Регистрация выезда автомобиля")
    public ParkingExitResponseDto registerExit(@RequestBody ParkingExitRequestDto requestDto) {
        return parkingService.registerExit(requestDto);
    }

    @GetMapping("/report")
    @Operation(summary = "Получение отчета по парковке за период")
    public ParkingReportResponseDto getReport(
            @NotNull
            @Validated
            @RequestParam("start_date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,

            @NotNull
            @Validated
            @RequestParam("end_date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return parkingService.generateReport(startDate, endDate);
    }
}