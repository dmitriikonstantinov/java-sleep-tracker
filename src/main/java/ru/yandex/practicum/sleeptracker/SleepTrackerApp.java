package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class SleepTrackerApp {

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

        System.out.println(new CountSessionsFunction().apply(sessions).getDescription()
                + ": " + new CountSessionsFunction().apply(sessions).getValue());

        System.out.println(new MinDurationFunction().apply(sessions).getDescription()
                + ": " + new MinDurationFunction().apply(sessions).getValue());

        System.out.println(new MaxDurationFunction().apply(sessions).getDescription()
                + ": " + new MaxDurationFunction().apply(sessions).getValue());

        System.out.println(new AvgDurationFunction().apply(sessions).getDescription()
                + ": " + new AvgDurationFunction().apply(sessions).getValue());

        System.out.println(new CountBadSessionsFunction().apply(sessions).getDescription()
                + ": " + new CountBadSessionsFunction().apply(sessions).getValue());

        System.out.println(new CountSleeplessNightsFunction().apply(sessions).getDescription()
                + ": " + new CountSleeplessNightsFunction().apply(sessions).getValue());

        System.out.println(new ChronotypeFunction().apply(sessions).getDescription()
                + ": " + new ChronotypeFunction().apply(sessions).getValue());

    }

}
