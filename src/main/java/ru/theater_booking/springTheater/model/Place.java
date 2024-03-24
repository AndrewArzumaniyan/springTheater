package ru.theater_booking.springTheater.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.theater_booking.springTheater.model.type.PlaceType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long placeId;

    @ManyToOne
    @JoinColumn(name = "scene_id", referencedColumnName = "scene_id")
    private Scene scene;

    @Enumerated(EnumType.STRING)
    @Column(name = "place_type")
    private PlaceType placeType;

    @Column(name = "row_num")
    private Integer rowNum;

    @Column(name = "seat_num")
    private Integer seatNum;
}
