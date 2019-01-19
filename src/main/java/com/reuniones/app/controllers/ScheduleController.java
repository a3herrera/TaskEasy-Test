package com.reuniones.app.controllers;


import com.reuniones.app.entities.Information;
import com.reuniones.app.entities.People;
import com.reuniones.app.entities.WorkTime;
import com.reuniones.app.services.SchedulesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("schedule")
public class ScheduleController {


    private SchedulesService meetingService;

    public ScheduleController(SchedulesService meetingService) {
        this.meetingService = meetingService;
    }


    @PostMapping(path="/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Information setAll(@RequestBody Information information) {
        return meetingService.setInformation(information);
    }

    private <T> T getValue(Optional<T> optional, T defaultValue) {
        if (optional.isPresent()) {
            return optional.get();
        }
        return defaultValue;
    }

    @GetMapping(path="/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String, List<String>> schedules(@RequestParam("at_least") Optional<Integer> atLeast,
                                           @RequestParam("free") Optional<Boolean> free) {
        int requiredSize = getValue(atLeast, -1);
        boolean checkFreeTime = getValue(free, false);
        return meetingService.schedules(checkFreeTime, requiredSize);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<People> getAll() {
        return meetingService.getPeople();
    }

    @GetMapping(path = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    People findByName(@RequestParam String name) {
        return meetingService.findByName(name);
    }

    @PostMapping(path = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    People addOne(@RequestBody People people) {
        return meetingService.addOnePerson(people);
    }

    @PostMapping(path = "/people", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void addMultiplies(@RequestBody List<People> personsList) {
        meetingService.addPeople(personsList);
    }

    @PostMapping(path = "/time", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    WorkTime setWorkHours(@RequestBody WorkTime workTime) {
        return meetingService.setWorkTime(workTime);
    }

    @GetMapping(path = "/time", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    WorkTime getworkHours() {
        return meetingService.getWorkTime();
    }


}
