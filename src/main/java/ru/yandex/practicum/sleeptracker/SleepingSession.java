package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Quality quality;

    public SleepingSession(LocalDateTime start, LocalDateTime end, Quality quality) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Первым должно быть начало сна");
        }
        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
    public Quality getQuality() { return quality; }
}
