package ru.theater_booking.springTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.theater_booking.springTheater.model.view.PlaceView;

import java.util.List;

public interface PlaceViewRepository extends JpaRepository<PlaceView, Long> {
    @Query(value = "SELECT * FROM get_places_by_begin_id(:begin_id_param)", nativeQuery = true)
    List<PlaceView> getPlacesByBeginId(int begin_id_param);
}
