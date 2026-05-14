package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {
    private static final String DESCRIPTION = "Максимальная продолжительность (мин)";

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long maxDuration = sessions.stream().mapToLong(s -> Duration.between(s.getStart(), s.getEnd())
                .toMinutes()).max().orElse(0);
        return new SleepAnalysisResult<>(DESCRIPTION, maxDuration);
    }
}
