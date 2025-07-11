package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DaysTest {

    @Test
    void getDayType() {
        // Test für einen Wochentag (MITTWOCH)
        String resultWeekday = Days.getDayType(DaysOfWeek.MITTWOCH);
        // Erwartet wird der Name des Tages als String zurückgegeben
        assertEquals("MITTWOCH", resultWeekday);

        // Test für einen Wochenende-Tag (SAMSTAG)
        String resultWeekend = Days.getDayType(DaysOfWeek.SAMSTAG);
        // Erwartet wird "Wochenende" zurückgegeben
        assertEquals("Wochenende", resultWeekend);

        // Test für SONNTAG (Wochenende)
        String resultSunday = Days.getDayType(DaysOfWeek.SONNTAG);
        // Erwartet wird "Wochenende"
        assertEquals("Wochenende", resultSunday);
    }
}
