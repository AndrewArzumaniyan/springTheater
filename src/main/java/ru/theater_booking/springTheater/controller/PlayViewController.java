package ru.theater_booking.springTheater.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.theater_booking.springTheater.model.view.PlayView;
import ru.theater_booking.springTheater.service.PlayViewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/play")
@AllArgsConstructor
public class PlayViewController {
    private PlayViewService service;

    @GetMapping("/all")
    public ResponseEntity<List<PlayView>> findAllPlays() {
        List<PlayView> playViews = service.findAllPlays();

        if (playViews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playViews);
    }

    // date format: yyyy-mm
    @GetMapping("/all/date/{date}")
    public ResponseEntity<List<PlayView>> findAllPlaysPerDate(@PathVariable String date) {
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        List<PlayView> playViews = service.getPlaysByYearAndMonth(year, month);

        if (playViews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playViews);
    }

    @GetMapping("/all/theater/{theatreId}")
    public ResponseEntity<List<PlayView>> findPlaysInTheater(@PathVariable  long theaterId) {
        List<PlayView> playViews = service.findPlaysInTheater(theaterId);

        if (playViews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(playViews);
    }
}
