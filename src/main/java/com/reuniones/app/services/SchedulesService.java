package com.reuniones.app.services;

import com.reuniones.app.entities.Person;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulesService {

    private List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        return persons;
    }


    private boolean existName(String name) {
        return persons.stream().map(Person::getName).anyMatch(name::equals);
    }

    public Person findByName(String name) {
        return persons.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    public void addNew(Person newPerson) {
        if (existName(newPerson.getName())) {
            Person person = findByName(newPerson.getName());
            List<Time> schedules = person.getSchedules();

        } else {
            persons.add(newPerson);
        }
    }

    public void addSimultaneous(List<Person> personsList) {
        personsList.forEach((T) -> addNew(T));
    }


}
