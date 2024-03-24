package ru.theater_booking.springTheater.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.theater_booking.springTheater.exception.TheaterNotFoundException;
import ru.theater_booking.springTheater.model.Theater;
import ru.theater_booking.springTheater.repository.TheaterRepository;
import ru.theater_booking.springTheater.service.TheaterService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TheaterServiceImpl implements TheaterService {
    private final TheaterRepository repository;

    @Override
    public List<Theater> findAllTheaters() {
        return repository.findAll();
    }

    @Override
    public Theater saveTheater(Theater theater) {
        return repository.save(theater);
    }

    @Override
    public Optional<Theater> findTheaterById(long theaterId) {
        return repository.findById(theaterId);
    }

    @Override
    public Theater updateTheater(Theater theater) {
        Theater existingTheater = repository.findById(theater.getTheaterId())
                .orElseThrow(() -> new TheaterNotFoundException(theater.getTheaterId()));

        existingTheater.setName(theater.getName());
        existingTheater.setInfo(theater.getInfo());
        existingTheater.setCity(theater.getCity());
        existingTheater.setStreet(theater.getStreet());
        existingTheater.setHouse(theater.getHouse());
        existingTheater.setLat(theater.getLat());
        existingTheater.setLng((theater.getLng()));

        return repository.save(existingTheater);
    }

    @Override
    public void deleteTheater(long theaterId) {
        repository.deleteById(theaterId);
    }
}
