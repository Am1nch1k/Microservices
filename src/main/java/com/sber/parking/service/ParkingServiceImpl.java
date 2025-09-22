package com.sber.parking.service;

import com.sber.parking.dto.ParkingEntryRequestDto;
import com.sber.parking.dto.ParkingEntryResponseDto;
import com.sber.parking.dto.ParkingExitRequestDto;
import com.sber.parking.dto.ParkingExitResponseDto;
import com.sber.parking.dto.ParkingReportResponseDto;
import com.sber.parking.entity.ParkingSession;
import com.sber.parking.entity.Vehicle;
import com.sber.parking.exception.VehicleAlreadyParkedException;
import com.sber.parking.exception.VehicleNotFoundException;
import com.sber.parking.exception.VehicleNotParkedException;
import com.sber.parking.repository.ParkingSessionRepository;
import com.sber.parking.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingServiceImpl implements ParkingService {

    private final VehicleRepository vehicleRepository;
    private final ParkingSessionRepository parkingSessionRepository;

    @Override
    @Transactional
    public ParkingEntryResponseDto registerEntry(ParkingEntryRequestDto requestDto) {
        Vehicle existingVehicle = vehicleRepository.findById(requestDto.getLicensePlate()).orElse(null);

        if (existingVehicle != null && parkingSessionRepository.existsByVehicleAndExitTimeIsNull(existingVehicle)) {
            throw new VehicleAlreadyParkedException(requestDto.getLicensePlate());
        }
        log.info("Въезд: {}", requestDto);
        Vehicle vehicle = existingVehicle != null
                ? existingVehicle
                : vehicleRepository.save(Vehicle.builder()
                        .type(requestDto.getType())
                        .licensePlate(requestDto.getLicensePlate())
                        .build());

        ParkingSession session = ParkingSession.builder()
                .vehicle(vehicle)
                .entryTime(LocalDateTime.now())
                .build();

        ParkingSession saved = parkingSessionRepository.save(session);
        return ParkingEntryResponseDto.builder()
                .licensePlate(saved.getVehicle().getLicensePlate())
                .entryTime(saved.getEntryTime())
                .build();
    }

    @Override
    @Transactional
    public ParkingExitResponseDto registerExit(ParkingExitRequestDto requestDto) {
        log.info("Выезд: {}", requestDto);
        Vehicle vehicle = vehicleRepository.findById(requestDto.getLicensePlate())
                .orElseThrow(() -> new VehicleNotFoundException(requestDto.getLicensePlate()));
        ParkingSession session = parkingSessionRepository
                .findByVehicleAndExitTimeIsNull(vehicle)
                .orElseThrow(() -> new VehicleNotParkedException(requestDto.getLicensePlate()));

        session.setExitTime(LocalDateTime.now());
        return ParkingExitResponseDto.builder()
                .licensePlate(session.getVehicle().getLicensePlate())
                .exitTime(session.getExitTime())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ParkingReportResponseDto generateReport(LocalDateTime start, LocalDateTime end) {
        log.info("Генерация отчета с {} по {}", start, end);
        long entries = parkingSessionRepository.countEntriesBetween(start, end);
        long exits = parkingSessionRepository.countExitsBetween(start, end);
        Long averageDuration = parkingSessionRepository.averageParkingDurationMinutes(start, end);
        return ParkingReportResponseDto.builder()
                .totalEntries(entries)
                .totalExits(exits)
                .averageParkingDurationMinutes(averageDuration)
                .build();
    }
}
