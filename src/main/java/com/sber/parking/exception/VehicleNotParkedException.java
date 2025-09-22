package com.sber.parking.exception;

public class VehicleNotParkedException extends ParkingException {

    public VehicleNotParkedException(String licensePlate) {
        super("Автомобиль с номером '" + licensePlate + "' не находится на парковке");
    }
}