package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class CountBadSessionsFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {
    private static final String DESCRIPTION = "Сессии плохого качества сна";

    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long badSessions = sessions.stream()
                .filter(s -> s.getQuality() == Quality.BAD)
                .count();
        return new SleepAnalysisResult<>(DESCRIPTION, badSessions);
    }
}
