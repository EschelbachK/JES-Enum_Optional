package org.example;

public class Days {

    // Gibt zurück, ob der Tag ein Wochentag ist oder "Wochenende"
    public static String getDayType(DaysOfWeek day) {
        return switch (day) {
            // Bei Wochentagen wird der Name des Tages zurückgegeben
            case MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG -> day.name();

            // Bei Samstag und Sonntag wird "Wochenende" zurückgegeben
            case SAMSTAG, SONNTAG -> "Wochenende";

            // Falls ein unbekannter Wert übergeben wird
            default -> throw new IllegalArgumentException("Unbekannter Tag: " + day);
        };
    }
}
