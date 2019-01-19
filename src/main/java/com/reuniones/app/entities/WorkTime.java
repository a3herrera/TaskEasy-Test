package com.reuniones.app.entities;

import lombok.Data;

import java.sql.Time;

@Data
public class WorkTime {

    private Time startHour;
    private Time endHour;

    private Time lunchStart;
    private Time lunchEnd;
}
