package ru.theater_booking.springTheater.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.theater_booking.springTheater.model.view.PlaceView;
import ru.theater_booking.springTheater.service.PlaceViewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/place")
@AllArgsConstructor
public class PlaceViewController {
    private PlaceViewService service;

    @GetMapping("/all/begin/{begin_id_param}")
    public ResponseEntity<List<PlaceView>> findAllPlacesByBeginId(@PathVariable int begin_id_param) {
        List<PlaceView> placeViews = service.getPlacesByBeginId(begin_id_param);

        if (placeViews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(placeViews);
    }
}
