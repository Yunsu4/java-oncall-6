package oncall.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ClassificationOfWork {
    WEEKDAY(true, false, false),
    HOLIDAY_WEEKDAY(true, true, false),
    HOLIDAY(false, false, true);

    final boolean isWeekday;
    final boolean isHolidayWeekday;
    final boolean isHoliday;

    ClassificationOfWork(boolean isWeekday, boolean isHolidayWeekday, boolean isHoliday){
        this.isWeekday = isWeekday;
        this.isHolidayWeekday = isHolidayWeekday;
        this.isHoliday = isHoliday;
    }

    public static Map<Integer, ClassificationOfWork> getClassificationOfWork(int month, int numberOfDaysAMonth, int dayOfWeek){
        Map<Integer, ClassificationOfWork> days = new LinkedHashMap<>();

        int dow = dayOfWeek;
        for(int date =1; date<=numberOfDaysAMonth;date++){
            if(dow == 8){
                dow =1;
            }
            days.put(date, classify(dow));
            handleHoliday(month, date, days);
            dow++;

        }
        return days;
    }

    private static ClassificationOfWork classify(int dayOfWeek) {
        if(dayOfWeek <= 5){
            return WEEKDAY;
        }
        return HOLIDAY;
    }

    private static void handleHoliday(int month, int date, Map<Integer, ClassificationOfWork> days) {
        if(month == 1 && date == 1){
            days.put(date, HOLIDAY_WEEKDAY);
        }

        if(month == 3 && date == 1){
            days.put(date, HOLIDAY_WEEKDAY);
        }

        if(month == 5 && date == 5){
            days.put(date, HOLIDAY_WEEKDAY);
        }

        if(month == 5 && date == 5){
            days.put(date, HOLIDAY_WEEKDAY);
        }

        if(month == 5 && date == 5){
            days.put(date, HOLIDAY_WEEKDAY);
        }

        if(month == 6 && date == 6){
            days.put(date, HOLIDAY_WEEKDAY);
        }

        if(month == 8 && date == 15){
            days.put(date, HOLIDAY_WEEKDAY);
        }

        if(month == 10 && date == 3){
            days.put(date, HOLIDAY_WEEKDAY);
        }

        if(month == 10 && date == 9){
            days.put(date, HOLIDAY_WEEKDAY);
        }

        if(month == 12 && date == 25){
            days.put(date, HOLIDAY_WEEKDAY);
        }
    }


}
