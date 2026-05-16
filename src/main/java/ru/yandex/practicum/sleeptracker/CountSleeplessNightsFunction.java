package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.time.temporal.ChronoUnit;

public class CountSleeplessNightsFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "Бессонные ночи";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, 0L);
        }
        Set<LocalDate> coveredNight = new HashSet<>();
        sessions.stream().forEach(s -> {
            LocalDateTime start = s.getStart();
            LocalDateTime end = s.getEnd();
            LocalDate date = start.toLocalDate().equals(end.toLocalDate()) ? start.toLocalDate() : end.toLocalDate();
            LocalDateTime startNight = date.atStartOfDay();
            LocalDateTime endNight = startNight.plusHours(Constants.NIGHT_END.getHour());
            if (start.isBefore(endNight) && end.isAfter(startNight)) {
                coveredNight.add(date);
            }
        });
        LocalDate firstStart = sessions.stream()
                .map(s -> s.getStart().toLocalDate())
                .min(LocalDate::compareTo)
                .orElse(null);

        LocalDate lastEnd = sessions.stream()
                .map(s -> s.getEnd().toLocalDate())
                .max(LocalDate::compareTo)
                .orElse(null);
        long totalNights = ChronoUnit.DAYS.between(firstStart, lastEnd);
        if (sessions.get(0).getStart().getHour() < 12) {
            totalNights++;
        }
        long sleeplessNights = totalNights - coveredNight.size();
        return new SleepAnalysisResult(DESCRIPTION, sleeplessNights);
    }
}
