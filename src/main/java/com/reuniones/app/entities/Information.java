package com.reuniones.app.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Information {

    private List<People> people;
    private WorkTime work;

    public Information() {}

    public Information(List<People> people, WorkTime work) {
        this.people = people;
        this.work = work;
    }
}
