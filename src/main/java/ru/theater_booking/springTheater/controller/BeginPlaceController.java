package ru.theater_booking.springTheater.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.theater_booking.springTheater.model.BeginPlace;
import ru.theater_booking.springTheater.service.BeginPlaceService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/begin-place")
@AllArgsConstructor
public class BeginPlaceController {
    private BeginPlaceService service;

    @GetMapping("/{beginPlaceId}")
    public ResponseEntity<BeginPlace> findBeginPlaceById(@PathVariable Long beginPlaceId) {
        Optional<BeginPlace> beginPlaceOptional = service.getBeginPlaceById(beginPlaceId);

        return beginPlaceOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{beginPlaceId}")
    public ResponseEntity<BeginPlace> updateBeginPlaceStatus(@PathVariable Long beginPlaceId, @RequestBody boolean newStatus) {
        Optional<BeginPlace> updatedBeginPlace = service.updateBeginPlaceStatus(beginPlaceId, newStatus);
        return updatedBeginPlace.map(beginPlace -> new ResponseEntity<>(beginPlace, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
