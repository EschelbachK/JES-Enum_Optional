package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest {

    private PersonRepository repo;

    @BeforeEach
    void setUp() {
        // Vor jedem Test wird ein neues Repository angelegt und mit Personen gefüllt
        repo = new PersonRepository();
        repo.addPerson(new Person(1, "Anna", DaysOfWeek.DIENSTAG, Gender.WEIBLICH));
        repo.addPerson(new Person(2, "Bernd", DaysOfWeek.SONNTAG, Gender.MAENNLICH));
        repo.addPerson(new Person(3, "Chris", DaysOfWeek.FREITAG, Gender.DIVERS));
        repo.addPerson(new Person(4, "Diana", DaysOfWeek.MONTAG, Gender.WEIBLICH));
    }

    @Test
    void addPerson() {
        // Neue Person anlegen
        Person newPerson = new Person(5, "Elias", DaysOfWeek.MITTWOCH, Gender.MAENNLICH);

        // Neue Person zum Repository hinzufügen
        repo.addPerson(newPerson);

        // Alle Personen aus dem Repository holen
        List<Person> all = repo.getAllPersons();

        // Prüfen, ob die neue Person in der Liste ist
        assertTrue(all.contains(newPerson));

        // Prüfen, ob die Gesamtanzahl jetzt 5 ist
        assertEquals(5, all.size());
    }

    @Test
    void getAllPersons() {
        // Alle Personen aus dem Repository holen
        List<Person> all = repo.getAllPersons();

        // Prüfen, ob 4 Personen enthalten sind (aus setUp)
        assertEquals(4, all.size());

        // Prüfen, ob mindestens eine Person mit Name "Anna" dabei ist
        assertTrue(all.stream().anyMatch(p -> p.name().equals("Anna")));

        // Prüfen, ob mindestens eine Person mit Name "Bernd" dabei ist
        assertTrue(all.stream().anyMatch(p -> p.name().equals("Bernd")));
    }

    @Test
    void countByGender() {
        // Anzahl der Personen pro Geschlecht zählen
        Map<Gender, Long> counts = repo.countByGender();

        // Prüfen, ob es 2 weibliche Personen gibt
        assertEquals(2L, counts.get(Gender.WEIBLICH));

        // Prüfen, ob es 1 männliche Person gibt
        assertEquals(1L, counts.get(Gender.MAENNLICH));

        // Prüfen, ob es 1 Person mit divers gibt
        assertEquals(1L, counts.get(Gender.DIVERS));
    }

    @Test
    void findByFavoriteDay() {
        // Personen mit Lieblings-Tag Dienstag suchen
        List<Person> dienstagPeople = repo.findByFavoriteDay(DaysOfWeek.DIENSTAG);

        // Prüfen, ob genau 1 Person gefunden wurde
        assertEquals(1, dienstagPeople.size());

        // Prüfen, ob diese Person Anna heißt
        assertEquals("Anna", dienstagPeople.get(0).name());

        // Personen mit Lieblings-Tag Sonntag suchen
        List<Person> sonntagPeople = repo.findByFavoriteDay(DaysOfWeek.SONNTAG);

        // Prüfen, ob genau 1 Person gefunden wurde
        assertEquals(1, sonntagPeople.size());

        // Prüfen, ob diese Person Bernd heißt
        assertEquals("Bernd", sonntagPeople.get(0).name());
    }

    @Test
    void findById() {
        // Person mit ID 2 suchen
        Optional<Person> person = repo.findById(2);

        // Prüfen, ob eine Person gefunden wurde
        assertTrue(person.isPresent());

        // Prüfen, ob der Name der gefundenen Person "Bernd" ist
        assertEquals("Bernd", person.get().name());

        // Person mit nicht vorhandener ID 99 suchen
        Optional<Person> noPerson = repo.findById(99);

        // Prüfen, dass keine Person gefunden wurde
        assertTrue(noPerson.isEmpty());
    }

    @Test
    void findByName() {
        // Person mit Namen "Chris" suchen
        Optional<Person> person = repo.findByName("Chris");

        // Prüfen, ob eine Person gefunden wurde
        assertTrue(person.isPresent());

        // Prüfen, ob die ID der gefundenen Person 3 ist
        assertEquals(3, person.get().id());

        // Person mit nicht vorhandenem Namen suchen
        Optional<Person> noPerson = repo.findByName("NichtVorhanden");

        // Prüfen, dass keine Person gefunden wurde
        assertTrue(noPerson.isEmpty());
    }
}
