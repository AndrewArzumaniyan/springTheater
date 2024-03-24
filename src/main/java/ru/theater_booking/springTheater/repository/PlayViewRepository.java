package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.theater_booking.springTheater.model.view.PlayView;

import java.util.List;

public interface PlayViewRepository extends JpaRepository<PlayView, Long> {
    @Query(value = "SELECT * FROM get_plays_by_year_and_month(:year, :month)", nativeQuery = true)
    List<PlayView> getPlaysByYearAndMonth(int year, int month);

    List<PlayView> findByTheaterId(long theaterId);
}
