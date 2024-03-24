package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.theater_booking.springTheater.model.Director;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
