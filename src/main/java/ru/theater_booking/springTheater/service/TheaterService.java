package ru.theater_booking.springTheater.service;

import org.springframework.stereotype.Service;
import ru.theater_booking.springTheater.model.Theater;

import java.util.List;
import java.util.Optional;

@Service
public interface TheaterService {
    List<Theater> findAllTheaters();
    Theater saveTheater(Theater theater);
    Optional<Theater> findTheaterById(long theaterId);
    Theater updateTheater(Theater theater);
    void deleteTheater(long theaterId);
}
