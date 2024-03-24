package ru.theater_booking.springTheater.model.view;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.theater_booking.springTheater.model.type.PlaceType;
import ru.theater_booking.springTheater.model.json.PlacesJson;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlaceView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "price")
    private double price;

    @Column(name = "place_type")
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "places")
    private List<PlacesJson> places;
}
