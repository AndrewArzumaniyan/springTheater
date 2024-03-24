package ru.theater_booking.springTheater.exception;

public class TheaterNotFoundException extends RuntimeException {
    public TheaterNotFoundException(Long theaterId) {
        super("Theater not found with id " + theaterId);
    }
}
