package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.theater_booking.springTheater.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
