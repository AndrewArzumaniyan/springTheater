package ru.theater_booking.springTheater.model.json;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PlacesJson implements Serializable {
    private Long begin_place_id;
    private boolean status;
    private int row_num;
    private int seat_num;
}
