package com.sber.parking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingExitRequestDto {

    @NotBlank(message = "Номер автомобиля не может быть пустым")
    private String licensePlate;
}