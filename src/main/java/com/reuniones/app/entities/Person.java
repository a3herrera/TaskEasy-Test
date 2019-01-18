package com.reuniones.app.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Time;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

    private String name;
    private List<Time> schedules;

}