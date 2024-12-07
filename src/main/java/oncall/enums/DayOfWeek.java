package oncall.enums;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import oncall.view.error.ErrorException;
import oncall.view.error.InputErrorType;

public enum DayOfWeek {
    MONDAY("월", 1),
    TUESDAY("화", 2),
    WEDNESDAY("수", 3),
    THURSDAY("목", 4),
    FRIDAY("금", 5),
    SATURDAY("토", 6),
    SUNDAY("일", 7);

    final String dow;
    final int value;

    DayOfWeek(String dow, int value) {
        this.dow = dow;
        this.value = value;
    }

    public static Integer getValidDayOfWeek(String input) throws ErrorException{
        return Arrays.stream(DayOfWeek.values())
                .filter(dayOfWeek -> Objects.equals(dayOfWeek.dow, input))
                .map(dayOfWeek -> dayOfWeek.value)
                .findAny()
                .orElseThrow(() -> new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT));
    }

    public static List<String> getDow(int numberOfDaysAMonth, int firstDayOfWeek){
        List<String> days = new LinkedList<>();

        int dayOfWeek = firstDayOfWeek;
        for(int date =1; date<=numberOfDaysAMonth;date++){

            if(dayOfWeek == 8){
                dayOfWeek =1;
            }
            days.add(classifyDow(dayOfWeek));
            dayOfWeek++;

        }
        return days;
    }

    private static String classifyDow(int input) {
        return Arrays.stream(DayOfWeek.values())
                .filter(dayOfWeek -> Objects.equals(dayOfWeek.value, input))
                .map(dayOfWeek -> dayOfWeek.dow)
                .findAny()
                .orElseThrow(() -> new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT));

    }
}

