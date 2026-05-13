package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class CountSleeplessNightsFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        Set<LocalDate> coveredNight = new HashSet<>();
        sessions.stream().forEach(s -> {
            LocalDateTime start = s.getStart();
            LocalDateTime end = s.getEnd();
            LocalDate date = start.toLocalDate().equals(end.toLocalDate()) ? start.toLocalDate() : end.toLocalDate();
            LocalDateTime startNight = date.atStartOfDay();
            LocalDateTime endNight = startNight.plusHours(6);
            if (start.isBefore(endNight) && end.isAfter(startNight)) {
                coveredNight.add(date);
            }
        });
        LocalDate firstStart = sessions.stream().map(s -> s.getStart().toLocalDate())
                .min(LocalDate::compareTo).orElse(null);

        LocalDate lastEnd = sessions.stream()
                .map(s -> s.getEnd().toLocalDate())
                .max(LocalDate::compareTo)
                .orElse(null);
        long totalNights = java.time.temporal.ChronoUnit.DAYS.between(firstStart, lastEnd);
        long sleeplessNights = totalNights - coveredNight.size();
        return new SleepAnalysisResult<>("Бессонные ночи", sleeplessNights);
    }
}
