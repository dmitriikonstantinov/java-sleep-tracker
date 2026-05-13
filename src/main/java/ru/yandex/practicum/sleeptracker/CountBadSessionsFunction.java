package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class CountBadSessionsFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long badSessions = sessions.stream().filter(s -> s.getQuality() == Quality.BAD).count();
        return new SleepAnalysisResult<>("Сессии плохого качества сна", badSessions);
    }
}
