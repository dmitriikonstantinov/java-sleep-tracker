package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepTrackerApp {
    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> FUNCTIONS = List.of(
            new CountSessionsFunction(),
            new MinDurationFunction(),
            new MaxDurationFunction(),
            new AvgDurationFunction(),
            new CountBadSessionsFunction(),
            new CountSleeplessNightsFunction(),
            new ChronotypeFunction()
    );

    public static List<SleepingSession> readSession(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        return lines.stream().map(line -> {
            String[] part = line.split(";");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
            LocalDateTime start = LocalDateTime.parse(part[0], formatter);
            LocalDateTime end = LocalDateTime.parse(part[1], formatter);
            Quality quality = Quality.valueOf(part[2]);
            return new SleepingSession(start, end, quality);
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        Path path = Path.of("src/main/resources/sleep_log.txt");
        List<SleepingSession> sessions;
        try {
            sessions = readSession(path);
        } catch (IOException e) {
            System.err.println("Не удалось прочитать файл: " + e.getMessage());
            return;
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка формата даты в файле: " + e.getMessage());
            return;
        }

        for (Function<List<SleepingSession>, SleepAnalysisResult> func : FUNCTIONS) {
            SleepAnalysisResult result = func.apply(sessions);
            System.out.println(result.getDescription() + ": " + result.getValue());
        }

    }

}
