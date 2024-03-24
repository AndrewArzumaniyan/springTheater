package ru.theater_booking.springTheater.service;

import org.springframework.stereotype.Service;
import ru.theater_booking.springTheater.model.BeginPlace;

import java.util.Optional;

@Service
public interface BeginPlaceService {
    Optional<BeginPlace> getBeginPlaceById(Long beginPlaceId);

    Optional<BeginPlace> updateBeginPlaceStatus(Long beginPlaceId, boolean status);
}
