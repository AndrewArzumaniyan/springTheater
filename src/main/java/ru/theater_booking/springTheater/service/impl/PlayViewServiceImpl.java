package ru.theater_booking.springTheater.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.theater_booking.springTheater.model.view.PlayView;
import ru.theater_booking.springTheater.repository.PlayViewRepository;
import ru.theater_booking.springTheater.service.PlayViewService;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayViewServiceImpl implements PlayViewService {
    private final PlayViewRepository repository;

    @Override
    public List<PlayView> findAllPlays() {
        return repository.findAll();
    }

    @Override
    public List<PlayView> getPlaysByYearAndMonth(int year, int month) {
        return repository.getPlaysByYearAndMonth(year, month);
    }

    @Override
    public List<PlayView> findPlaysInTheater(long theaterId) {
        return repository.findByTheaterId(theaterId);
    }
}
