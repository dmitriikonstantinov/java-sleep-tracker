package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AvgDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Double>> {
    @Override
    public SleepAnalysisResult<Double> apply(List<SleepingSession> sessions) {
        double avrDuration = sessions.stream().mapToLong(s -> Duration.between(s.getStart(), s.getEnd())
                .toMinutes()).average().orElse(0.0);
return new SleepAnalysisResult<>("Средняя продолжительность (мин)", avrDuration);
    }
}
