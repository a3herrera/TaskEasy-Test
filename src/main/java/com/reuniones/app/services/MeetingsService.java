package com.reuniones.app.services;

import com.reuniones.app.entities.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingsService {

    private List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        return persons;
    }


    private boolean existName(String name) {
        return persons.stream().map(Person::getName).anyMatch(name::equals);
    }

    public Person findByName(String name) {
        List<Person> result = persons.stream().filter(s -> s.getName().equals(name)).limit(1).collect(Collectors.toList());
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public void addNew(Person newPerson) {
        if (existName(newPerson.getName())) {

        } else {
            persons.add(newPerson);
        }
    }

    public void addSimultaneous(List<Person> personsList) {
        personsList.stream().forEach((T) -> addNew(T));
    }


}
