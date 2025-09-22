package com.sber.parking.exception;

public class VehicleAlreadyParkedException extends ParkingException {

    public VehicleAlreadyParkedException(String licensePlate) {
        super("Автомобиль с номером '" + licensePlate + "' уже находится на парковке");
    }
}