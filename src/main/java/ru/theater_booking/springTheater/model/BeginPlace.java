package ru.theater_booking.springTheater.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "begin_place")
public class BeginPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "begin_place_id")
    private Long beginPlaceId;

    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "begin_id", referencedColumnName = "begin_id")
    private Begin begin;

    @Column(name = "status")
    private Boolean status;
}
