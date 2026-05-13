package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class ChronotypeFunction implements Function<List<SleepingSession>, SleepAnalysisResult<String>> {
    @Override
    public SleepAnalysisResult<String> apply(List<SleepingSession> sessions) {
        final int[] owl = {0};
        final int[] lark = { 0 };
        final int[] dove = { 0 };
        sessions.stream().forEach(s -> {
            LocalDateTime start = s.getStart();
            LocalDateTime end = s.getEnd();
            LocalDate date = start.toLocalDate().equals(end.toLocalDate()) ? start.toLocalDate() : end.toLocalDate();
            LocalDateTime startNight = date.atStartOfDay();
            LocalDateTime endNight = startNight.plusHours(6);
            if (start.isBefore(endNight) && end.isAfter(startNight)) {
                if (start.toLocalTime().isAfter(LocalTime.of(23, 0)) && end.toLocalTime()
                        .isAfter(LocalTime.of(9, 0))) {
                    owl[0]++;
                } else if (start.toLocalTime().isBefore(LocalTime.of(22, 0)) && end.toLocalTime()
                        .isBefore(LocalTime.of(7, 0))) {
                    lark[0]++;
                } else {
                    dove[0]++;
                }
            }
        });
        String resultType;
        if (owl[0] > lark[0] && owl[0] > dove[0]) {
            resultType = "Сова";
        } else if (lark[0] > owl[0] && lark[0] > dove[0]) {
            resultType = "Жаворонок";
        } else {
            resultType = "Голубь";
        }
        return new SleepAnalysisResult<>("Хронотип", resultType);
    }
}
