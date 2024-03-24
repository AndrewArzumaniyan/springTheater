package ru.theater_booking.springTheater.model.json;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class BeginsJson implements Serializable {
    private Long begin_id;
    private String scene;
    private Timestamp begin_time;
}
