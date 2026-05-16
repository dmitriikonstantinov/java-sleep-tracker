package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;

public final class Constants {
    public static final LocalTime NIGHT_END = LocalTime.of(6, 0);
    public static final LocalTime OWL_SLEEP_START = LocalTime.of(23, 0);
    public static final LocalTime OWL_SLEEP_END = LocalTime.of(4, 0);
    public static final LocalTime OWL_WAKE_END = LocalTime.of(9, 0);
    public static final LocalTime LARK_SLEEP_END = LocalTime.of(22, 0);
    public static final LocalTime LARK_WAKE_END = LocalTime.of(7, 0);

}
