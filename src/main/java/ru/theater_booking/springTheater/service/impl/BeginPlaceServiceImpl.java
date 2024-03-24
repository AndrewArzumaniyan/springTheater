package ru.theater_booking.springTheater.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.theater_booking.springTheater.model.BeginPlace;
import ru.theater_booking.springTheater.repository.BeginPlaceRepository;
import ru.theater_booking.springTheater.service.BeginPlaceService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BeginPlaceServiceImpl implements BeginPlaceService {
    private BeginPlaceRepository repository;

    @Override
    public Optional<BeginPlace> getBeginPlaceById(Long beginPlaceId) {
        return repository.findById(beginPlaceId);
    }

    @Override
    public Optional<BeginPlace> updateBeginPlaceStatus(Long beginPlaceId, boolean status) {
        Optional<BeginPlace>  beginPlaceOptional = repository.findById(beginPlaceId);

        if (beginPlaceOptional.isPresent()) {
            BeginPlace beginPlace = beginPlaceOptional.get();
            beginPlace.setStatus(status);
            return Optional.of(repository.save(beginPlace));
        }
        return Optional.empty();
    }
}
