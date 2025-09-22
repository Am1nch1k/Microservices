package com.sber.parking.exception;

public class VehicleNotFoundException extends ParkingException {

    public VehicleNotFoundException(String licensePlate) {
        super("Автомобиль с номером '" + licensePlate + "' не найден");
    }
}