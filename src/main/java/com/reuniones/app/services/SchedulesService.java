package com.reuniones.app.services;

import com.reuniones.app.entities.Information;
import com.reuniones.app.entities.People;
import com.reuniones.app.entities.WorkTime;
import com.reuniones.app.utils.DataCheck;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SchedulesService {

    private List<People> people = new ArrayList<>();

    @Getter
    private WorkTime workTime = new WorkTime();
    @Getter
    private List<Time> workTimes;

    private List<Time> lunchTimes;

    public List<People> getPeople() {
        return people.stream().sorted(Comparator.comparing(People::getName)).collect(Collectors.toList());
    }

    public People findByName(String name) {
        return people.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    public People addOnePerson(People newPerson) {
        People person = findByName(newPerson.getName());
        if (person != null) {
            if (!DataCheck.isEmptyList(newPerson.getSchedules())) {
                if (person.getSchedules() == null) {
                    person.setSchedules(new ArrayList<>());
                }
                person.setSchedules(Stream.concat(
                        person.getSchedules().stream(),
                        newPerson.getSchedules().stream()).collect(Collectors.toList()));
            }
        } else {
            this.people.add(newPerson);
            return newPerson;
        }
        return person;
    }

    public void addPeople(List<People> personsList) {
        personsList.forEach(this::addOnePerson);
    }

    private List<Time> generateTimes(Time init, Time end) {
        List<Time> times = new ArrayList<>();
        while (init.before(end)) {
            times.add(init);
            init = Time.valueOf(init.toLocalTime().plusMinutes(30));
        }
        return times;
    }

    public WorkTime setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
        this.workTimes = generateTimes(this.workTime.getStartHour(), this.workTime.getEndHour());
        this.lunchTimes = generateTimes(this.workTime.getLunchStart(), this.workTime.getLunchEnd());
        return this.workTime;
    }

    public Information setInformation(Information allInformation) {
        setWorkTime(allInformation.getWork());
        addPeople(allInformation.getPeople());
        return new Information(getPeople(), getWorkTime());
    }


    private boolean existTime(Time time, List<Time> times) {
        return times.stream().anyMatch(time::equals);
    }

    private boolean checkTime(Time time, List<Time> times, boolean free) {
        return !free == times.stream().anyMatch(time::equals);
    }

    public Map<String, List<String>> schedules(boolean free, int requiredPeople) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        if (!DataCheck.isEmptyList(workTimes)) {
            List<Time> times = !free ? workTimes : workTimes.stream().filter(k -> !existTime(k, lunchTimes)).collect(Collectors.toList());
            for (Time time : times) {
                List<String> abc = getPeople().stream().filter(n -> checkTime(time, n.getSchedules(), free)).map(People::getName).collect(Collectors.toList());
                if (requiredPeople == -1 || (requiredPeople > 0 && abc.size() >= requiredPeople)) {
                    result.put(time.toString(), abc);
                }
            }
        }
        return result;
    }

}
