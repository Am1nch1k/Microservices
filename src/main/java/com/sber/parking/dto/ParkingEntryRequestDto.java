package com.sber.parking.dto;

import com.sber.parking.entity.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingEntryRequestDto {

    @NotBlank(message = "Номер автомобиля не может быть пустым")
    @Pattern(regexp = "^[A-Z]{3}[0-9]{3}$", message = "Номер автомобиля должен соответствовать шаблону")
    private String licensePlate;

    @NotNull(message = "Тип автомобиля обязателен")
    private VehicleType type;
}