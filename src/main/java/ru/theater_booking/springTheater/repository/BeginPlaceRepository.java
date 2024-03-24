package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.theater_booking.springTheater.model.BeginPlace;

public interface BeginPlaceRepository extends JpaRepository<BeginPlace, Long> {
}
