package com.reuniones.app.entities;

import lombok.Data;

import java.util.List;

@Data
public class AllDTO {

    private List<Person> personal;
    private WorkTime workTime;

}
