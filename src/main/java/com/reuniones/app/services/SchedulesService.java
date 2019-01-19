package com.reuniones.app.services;

import com.reuniones.app.entities.Person;
import com.reuniones.app.entities.WorkTime;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SchedulesService {

    private WorkTime workTime = new WorkTime();
    private List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        return persons;
    }

    public Person findByName(String name) {
        return persons.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    public Person addNew(Person newPerson) {
        Person person = findByName(newPerson.getName());
        if (person != null) {
            if (newPerson.getSchedules() != null && !newPerson.getSchedules().isEmpty()) {
                if (person.getSchedules() == null) {
                    person.setSchedules(new ArrayList<>());
                }
                person.setSchedules(Stream.concat(
                        person.getSchedules().stream(),
                        newPerson.getSchedules().stream()).collect(Collectors.toList()));
            }
        } else {
            persons.add(newPerson);
            return newPerson;
        }
        return person;
    }

    public void addMultiplies(List<Person> personsList) {
        personsList.forEach((T) -> addNew(T));
    }

    public WorkTime setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
        return this.workTime;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }
    //    private void generateWorkTimer(){
//        Time myTime = java.sql.Time.valueOf("15:30:00");
//        LocalTime localTime = myTime.toLocalTime().plusMinutes(30);
//        System.out.println(myTime);
//        System.out.println(Time.valueOf(localTime));
//    }

}
