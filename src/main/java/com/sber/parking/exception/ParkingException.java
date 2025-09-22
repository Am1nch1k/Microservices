package com.sber.parking.exception;

public abstract class ParkingException extends RuntimeException {

    public ParkingException(String message) {
        super(message);
    }
}