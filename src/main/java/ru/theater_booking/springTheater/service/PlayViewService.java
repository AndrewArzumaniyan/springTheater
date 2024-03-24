package ru.theater_booking.springTheater.service;

import org.springframework.stereotype.Service;
import ru.theater_booking.springTheater.model.view.PlayView;

import java.util.List;

@Service
public interface PlayViewService {
    List<PlayView> findAllPlays();
    List<PlayView> getPlaysByYearAndMonth(int year, int month);
    List<PlayView> findPlaysInTheater(long theaterId);
}
