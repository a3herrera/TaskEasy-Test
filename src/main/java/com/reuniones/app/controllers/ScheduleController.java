package com.reuniones.app.controllers;


import com.reuniones.app.entities.Person;
import com.reuniones.app.entities.WorkTime;
import com.reuniones.app.services.SchedulesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedule")
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
    @ResponseBody
    Person addOne(@RequestBody Person person) {
        return meetingService.addNew(person);
    }

    @PostMapping(path = "/news", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void addMultiplies(@RequestBody List<Person> personsList) {
        meetingService.addMultiplies(personsList);
    }

    @PostMapping(path = "/work", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    WorkTime setWorkHours(@RequestBody WorkTime workTime) {
        return meetingService.setWorkTime(workTime);
    }

    @GetMapping(path = "/work", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    WorkTime getworkHours() {
        return meetingService.getWorkTime();
    }

}
