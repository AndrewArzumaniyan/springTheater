package ru.theater_booking.springTheater.service;

import org.springframework.stereotype.Service;
import ru.theater_booking.springTheater.model.view.PlaceView;

import java.util.List;

@Service
public interface PlaceViewService {
    List<PlaceView> getPlacesByBeginId(int begin_id_param);
}
