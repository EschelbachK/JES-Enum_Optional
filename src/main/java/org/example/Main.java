package org.example;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        PersonRepository repo = new PersonRepository();

        // Drei Personen anlegen
        repo.addPerson(new Person(1, "Anna", DaysOfWeek.DIENSTAG, Gender.WEIBLICH));
        repo.addPerson(new Person(2, "Bernd", DaysOfWeek.SONNTAG, Gender.MAENNLICH));
        repo.addPerson(new Person(3, "Chris", DaysOfWeek.FREITAG, Gender.DIVERS));

        // Gibt Wochentag oder "Wochenende" aus
        System.out.println(Days.getDayType(DaysOfWeek.MITTWOCH));
        System.out.println(Days.getDayType(DaysOfWeek.SAMSTAG));

        System.out.println("Anzahl nach Gender:");
        Map<Gender, Long> genderCounts = repo.countByGender();
        for (Gender gender : genderCounts.keySet()) {
            System.out.println(gender + ": " + genderCounts.get(gender));
        }

        // Lieblings-Tag filtern und ausgeben
        System.out.println("Personen mit Lieblingswochentag Dienstag:");
        for (Person p : repo.findByFavoriteDay(DaysOfWeek.DIENSTAG)) {
            System.out.println(p.name());
        }

        // -Optional- Suche nach ID
        int idToSearch = 2;
        repo.findById(idToSearch).ifPresentOrElse(
                p -> System.out.println("Gefunden (ID " + idToSearch + "): " + p.name() + ", Lieblings-Tag: " + p.favoriteDay()),
                () -> System.out.println("Keine Person mit ID " + idToSearch + " gefunden.")
        );

        // -Optional- Suche nach Name
        String nameToSearch = "Anna";
        repo.findByName(nameToSearch).ifPresentOrElse(
                p -> System.out.println("Gefunden (Name " + nameToSearch + "): ID=" + p.id() + ", Lieblings-Tag: " + p.favoriteDay()),
                () -> System.out.println("Keine Person mit Name " + nameToSearch + " gefunden.")
        );
    }
}
