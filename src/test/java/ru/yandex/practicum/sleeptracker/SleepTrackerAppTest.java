package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepTrackerAppTest {
    @Test
    void testCountSessions() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2026, 5, 12, 23, 40),
                        LocalDateTime.of(2026, 5, 13, 7, 20), Quality.GOOD),
                new SleepingSession(LocalDateTime.of(2026, 5, 14, 0, 0),
                        LocalDateTime.of(2026, 5, 14, 3, 30), Quality.NORMAL)
        );
        CountSessionsFunction func = new CountSessionsFunction();
        long count = func.apply(sessions).getValue();
        assertEquals(2, count);
    }

    @Test
    void testCountSessionsEmpty() {
        List<SleepingSession> sessions = List.of();
        CountSessionsFunction func = new CountSessionsFunction();
        long count = func.apply(sessions).getValue();
        assertEquals(0, count);
    }

    @Test
    void testMinDurationFunction() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2026, 5, 10, 22, 40),
                        LocalDateTime.of(2026, 5, 11, 7, 30), Quality.GOOD),
                new SleepingSession(LocalDateTime.of(2026, 5, 14, 23, 0),
                        LocalDateTime.of(2026, 5, 15, 8, 15), Quality.NORMAL)
        );
        MinDurationFunction func = new MinDurationFunction();
        long min = func.apply(sessions).getValue();
        assertEquals(530, min);
    }

    @Test
    void testMinDurationFunctionEmpty() {
        List<SleepingSession> sessions = List.of();
        MinDurationFunction func = new MinDurationFunction();
        long min = func.apply(sessions).getValue();
        assertEquals(0, min);
    }

    @Test
    void testAvgDurationFunction() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2026, 5, 10, 22, 40),
                        LocalDateTime.of(2026, 5, 11, 7, 30), Quality.GOOD),
                new SleepingSession(LocalDateTime.of(2026, 5, 14, 23, 0),
                        LocalDateTime.of(2026, 5, 15, 8, 15), Quality.NORMAL)
        );
        AvgDurationFunction func = new AvgDurationFunction();
        double avr = func.apply(sessions).getValue();
        assertEquals(542.5, avr);
    }

    @Test
    void testAvgDurationFunctionEmpty() {
        List<SleepingSession> sessions = List.of();
        AvgDurationFunction func = new AvgDurationFunction();
        double avr = func.apply(sessions).getValue();
        assertEquals(0.0, avr);
    }

    @Test
    void testCountBadSessionsFunction() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2026, 5, 10, 22, 40),
                        LocalDateTime.of(2026, 5, 11, 7, 30), Quality.GOOD),
                new SleepingSession(LocalDateTime.of(2026, 5, 14, 23, 0),
                        LocalDateTime.of(2026, 5, 15, 8, 15), Quality.NORMAL),
                new SleepingSession(LocalDateTime.of(2026, 5, 17, 3, 50),
                        LocalDateTime.of(2026, 5, 17, 7, 50), Quality.BAD),
                new SleepingSession(LocalDateTime.of(2026, 5, 18, 22, 0),
                        LocalDateTime.of(2026, 5, 19, 4, 20), Quality.BAD)

        );
        CountBadSessionsFunction func = new CountBadSessionsFunction();
        long bad = func.apply(sessions).getValue();
        assertEquals(2, bad);
    }

    @Test
    void testCountBadSessionsFunctionEmpty() {
        List<SleepingSession> sessions = List.of();
        CountBadSessionsFunction func = new CountBadSessionsFunction();
        long bad = func.apply(sessions).getValue();
        assertEquals(0, bad);
    }

    @Test
    void testCountSleeplessNightsFunction() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2026, 5, 10, 22, 40),
                        LocalDateTime.of(2026, 5, 11, 7, 30), Quality.GOOD),
                new SleepingSession(LocalDateTime.of(2026, 5, 14, 23, 0),
                        LocalDateTime.of(2026, 5, 15, 8, 15), Quality.NORMAL)
        );
        CountSleeplessNightsFunction func = new CountSleeplessNightsFunction();
        long sleepLess = func.apply(sessions).getValue();
        assertEquals(3, sleepLess);
    }

    @Test
    void testCountSleeplessNightsEmpty() {
        List<SleepingSession> sessions = List.of();
        CountSleeplessNightsFunction func = new CountSleeplessNightsFunction();
        long sleepless = func.apply(sessions).getValue();
        assertEquals(0, sleepless);
    }

    @Test
    void testChronotypeOwl() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2026, 5, 10, 23, 30),
                        LocalDateTime.of(2026, 5, 11, 9, 30), Quality.GOOD),
                new SleepingSession(LocalDateTime.of(2026, 5, 11, 23, 45),
                        LocalDateTime.of(2026, 5, 12, 10, 0), Quality.NORMAL)
        );
        ChronotypeFunction func = new ChronotypeFunction();
        String result = func.apply(sessions).getValue();
        assertEquals("Сова", result);
    }

    @Test
    void testChronotypeLark() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2026, 5, 10, 21, 59),
                        LocalDateTime.of(2026, 5, 11, 6, 30), Quality.GOOD),
                new SleepingSession(LocalDateTime.of(2026, 5, 11, 21, 45),
                        LocalDateTime.of(2026, 5, 12, 6, 59), Quality.NORMAL)
        );
        ChronotypeFunction func = new ChronotypeFunction();
        String result = func.apply(sessions).getValue();
        assertEquals("Жаворонок", result);
    }

    @Test
    void testChronotypeDove() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2026, 5, 10, 23, 59),
                        LocalDateTime.of(2026, 5, 11, 6, 30), Quality.GOOD),
                new SleepingSession(LocalDateTime.of(2026, 5, 11, 22, 45),
                        LocalDateTime.of(2026, 5, 12, 7, 0), Quality.NORMAL)
        );
        ChronotypeFunction func = new ChronotypeFunction();
        String result = func.apply(sessions).getValue();
        assertEquals("Голубь", result);
    }

    @Test
    void testChronotypeMixed() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2026, 5, 10, 23, 30),
                        LocalDateTime.of(2026, 5, 11, 9, 30), Quality.GOOD),
                new SleepingSession(LocalDateTime.of(2026, 5, 11, 21, 45),
                        LocalDateTime.of(2026, 5, 12, 6, 30), Quality.NORMAL),
                new SleepingSession(LocalDateTime.of(2026, 5, 12, 23, 15),
                        LocalDateTime.of(2026, 5, 13, 9, 45), Quality.GOOD)
        );
        ChronotypeFunction func = new ChronotypeFunction();
        String result = func.apply(sessions).getValue();
        assertEquals("Сова", result);  // 2 совы, 1 жаворонок
    }

    @Test
    void testChronotypeEmpty() {
        List<SleepingSession> sessions = List.of();
        ChronotypeFunction func = new ChronotypeFunction();
        String result = func.apply(sessions).getValue();
        assertEquals("Голубь", result);
    }
}