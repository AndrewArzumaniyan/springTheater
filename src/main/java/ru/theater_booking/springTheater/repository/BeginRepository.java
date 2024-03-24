package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.theater_booking.springTheater.model.Begin;

public interface BeginRepository extends JpaRepository<Begin, Long> {
}
