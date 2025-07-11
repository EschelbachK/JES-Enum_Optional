package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonRepository {

    // Liste zur Speicherung aller Personen
    private final List<Person> persons = new ArrayList<>();

    // Fügt eine neue Person zur Liste hinzu
    public void addPerson(Person person) {
        persons.add(person);
    }

    // Gibt eine Kopie der gesamten Personenliste zurück
    public List<Person> getAllPersons() {
        return new ArrayList<>(persons);
    }

    // Zählt die Anzahl der Personen nach Geschlecht
    public Map<Gender, Long> countByGender() {
        Map<Gender, Long> result = new HashMap<>();
        for (Person person : persons) {
            Gender gender = person.gender();
            result.put(gender, result.getOrDefault(gender, 0L) + 1);
        }
        return result;
    }

    // Gibt eine Liste aller Personen zurück, deren Lieblingswochentag übereinstimmt
    public List<Person> findByFavoriteDay(DaysOfWeek day) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (person.favoriteDay().equals(day)) {
                result.add(person);
            }
        }
        return result;
    }

    // -Optional- <Person> anhand der ID finden
    public Optional<Person> findById(int id) {
        for (Person person : persons) {
            if (person.id() == id) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    // -Optional- <Person> anhand des Namens finden
    public Optional<Person> findByName(String name) {
        for (Person person : persons) {
            if (person.name().equalsIgnoreCase(name)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }
}
