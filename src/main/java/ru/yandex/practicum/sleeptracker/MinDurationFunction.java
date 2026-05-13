package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long minDuration = sessions.stream().mapToLong(s -> Duration.between(s.getStart(), s.getEnd())
                .toMinutes()).min().orElse(0);
        return new SleepAnalysisResult<>("Минимальная продолжительность (мин)", minDuration);
    }
}
