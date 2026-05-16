package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChronotypeFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "Хронотип";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(DESCRIPTION, Chronotype.DOVE.getDisplayName());
        }
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

        int owl = counts.get(Chronotype.OWL);
        int lark = counts.get(Chronotype.LARK);
        int dove = counts.get(Chronotype.DOVE);

        Chronotype resultType;
        if (owl > lark && owl > dove) {
            resultType = Chronotype.OWL;
        } else if (lark > owl && lark > dove) {
            resultType = Chronotype.LARK;
        } else {
            resultType = Chronotype.DOVE; // ничья или все равны
        }
        return new SleepAnalysisResult(DESCRIPTION, resultType.getDisplayName());
    }
}
