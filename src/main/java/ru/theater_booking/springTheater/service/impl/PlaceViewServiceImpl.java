package ru.theater_booking.springTheater.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.theater_booking.springTheater.model.view.PlaceView;
import ru.theater_booking.springTheater.repository.PlaceViewRepository;
import ru.theater_booking.springTheater.service.PlaceViewService;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaceViewServiceImpl implements PlaceViewService {
    private final PlaceViewRepository repository;

    @Override
    public List<PlaceView> getPlacesByBeginId(int begin_id_param) {
        return repository.getPlacesByBeginId(begin_id_param);
    }
}
