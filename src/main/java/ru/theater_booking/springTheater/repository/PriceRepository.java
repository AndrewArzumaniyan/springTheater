package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.theater_booking.springTheater.model.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
