package com.reuniones.app.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class People {

    private String name;
    private List<Time> schedules;

    public void setSchedules(List<Time> schedules) {
        this.schedules = schedules.stream().distinct().sorted().collect(Collectors.toList());
    }
}