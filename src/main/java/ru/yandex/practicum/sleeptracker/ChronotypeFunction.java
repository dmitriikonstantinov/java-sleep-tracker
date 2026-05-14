package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChronotypeFunction implements Function<List<SleepingSession>, SleepAnalysisResult<String>> {
    private static final String DESCRIPTION = "Хронотип";

    @Override
    public SleepAnalysisResult<String> apply(List<SleepingSession> sessions) {
        Map<Chronotype, Integer> counts = new HashMap<>();
        counts.put(Chronotype.OWL, 0);
        counts.put(Chronotype.LARK, 0);
        counts.put(Chronotype.DOVE, 0);
        sessions.stream().forEach(s -> {
            LocalDateTime start = s.getStart();
            LocalDateTime end = s.getEnd();
            LocalDate date = start.toLocalDate().equals(end.toLocalDate()) ? start.toLocalDate() : end.toLocalDate();
            LocalDateTime startNight = date.atStartOfDay();
            LocalDateTime endNight = startNight.plusHours(Constants.NIGHT_END.getHour());
            if (start.isBefore(endNight) && end.isAfter(startNight)) {
                if (start.toLocalTime().isAfter(Constants.OWL_SLEEP_START) ||
                        start.toLocalTime().isBefore(Constants.OWL_SLEEP_END) ||
                        end.toLocalTime().isAfter(Constants.OWL_WAKE_END)) {
                    counts.put(Chronotype.OWL, counts.get(Chronotype.OWL) + 1);
                } else if (start.toLocalTime().isBefore(Constants.LARK_SLEEP_END) && end.toLocalTime()
                        .isBefore(Constants.LARK_WAKE_END)) {
                    counts.put(Chronotype.LARK, counts.get(Chronotype.LARK) + 1);
                } else {
                    counts.put(Chronotype.DOVE, counts.get(Chronotype.DOVE) + 1);
                }
            }
        });
        Chronotype resultType = counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(Chronotype.DOVE);
        return new SleepAnalysisResult<>(DESCRIPTION, resultType.getDisplayName());
    }
}
