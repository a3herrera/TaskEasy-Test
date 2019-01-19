package com.reuniones.app.entities;

import lombok.Data;

import java.sql.Time;

@Data
public class WorkTime {

    private static final String DEFAULT = "00:00:00";
    private Time startHour = Time.valueOf(DEFAULT);
    private Time endHour = Time.valueOf(DEFAULT);

    private Time lunchStart = Time.valueOf(DEFAULT);
    private Time lunchEnd = Time.valueOf(DEFAULT);
}
