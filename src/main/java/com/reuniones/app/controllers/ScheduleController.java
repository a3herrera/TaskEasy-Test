package com.reuniones.app.controllers;


import com.reuniones.app.entities.Person;
import com.reuniones.app.services.SchedulesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persons")
public class ScheduleController {


    private SchedulesService meetingService;

    public ScheduleController(SchedulesService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Person> getAll() {
        return meetingService.getPersons();
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    Person findByName(@RequestParam String name) {
        return meetingService.findByName(name);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void addPerson(@RequestBody Person person) {
        meetingService.addNew(person);
    }

    @PostMapping(path = "/news", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void newPersons(@RequestBody List<Person> personsList) {
        meetingService.addSimultaneous(personsList);
    }

}
