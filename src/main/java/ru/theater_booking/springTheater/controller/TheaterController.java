package ru.theater_booking.springTheater.controller;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.theater_booking.springTheater.model.Theater;
import ru.theater_booking.springTheater.service.TheaterService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/theater")
@AllArgsConstructor
public class TheaterController {
    private TheaterService service;
    @GetMapping("/all")
    public ResponseEntity<List<Theater>> findAllTheater() {
        List<Theater> theaters = service.findAllTheaters();

        if (theaters.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/{theaterId}")
    public ResponseEntity<Theater> findTheaterById(@PathVariable Long theaterId) {
        Optional<Theater> theaterOptional = service.findTheaterById(theaterId);
        return theaterOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        Theater savedTheater = service.saveTheater(theater);
        return new ResponseEntity<>(savedTheater, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Theater> updateTheater(@RequestBody Theater theater) {
        Theater updatedTheater = service.updateTheater(theater);
        return new ResponseEntity<>(updatedTheater, HttpStatus.CREATED);
    }

    @DeleteMapping("/{theaterId}")
    public ResponseEntity<TypeResolutionContext.Empty> deleteTheater(@PathVariable Long theaterId) {
        service.deleteTheater(theaterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
