package oncall.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import oncall.enums.ClassificationOfWork;


public class ScheduleManager {
    private static final int RESET_INDEX = 0;
    public static final String HOLIDAY_WEEKDAY_MESSAGE = "(휴일)";
    
    private final List<String> weekdayWorkers;
    private final List<String> holidayWorkers;
    private final List<String> workSchedule;

    private int weekdayWorkerIndex = 0;
    private int holidayWorkerIndex = 0;
    
    public ScheduleManager(Workers workers){
        weekdayWorkers = workers.getWeekday();
        holidayWorkers = workers.getHoliday();
        this.workSchedule = new ArrayList<>();
    }

    public String getSchedule(int day){
        return workSchedule.get(day-1);
    }

    public String matchingSchedule(Map<Integer, ClassificationOfWork> parsedDays, List<String> dayOfWeekOfTheMonth, int day){
        String dow = dayOfWeekOfTheMonth.get(day - 1);
        if (parsedDays.get(day).equals(ClassificationOfWork.WEEKDAY)) {
            weekdayWorkerIndex = generateSchedule(weekdayWorkerIndex, weekdayWorkers, workSchedule);
        }
        if (!parsedDays.get(day).equals(ClassificationOfWork.WEEKDAY)) {
            holidayWorkerIndex = generateSchedule(holidayWorkerIndex, holidayWorkers, workSchedule);
        }
        return markHolidayWeekday(parsedDays, day, dow);
    }

    private String markHolidayWeekday(Map<Integer, ClassificationOfWork> parsedDays, int day, String dow) {
        if (parsedDays.get(day).equals(ClassificationOfWork.HOLIDAY_WEEKDAY)) {
            dow = dow + HOLIDAY_WEEKDAY_MESSAGE;
        }
        return dow;
    }

    private int generateSchedule(int workerIndex, List<String> workers, List<String> workSchedule) {
        int nextIndex = resetIndexNumberAtTheEnd(workerIndex, workers);
        if (checkContinuous(workers.get(workerIndex), workSchedule)) {
            Collections.swap(workers, workerIndex, nextIndex);
        }
        workSchedule.add(workers.get(workerIndex));
        return updateIndex(workerIndex, nextIndex);
    }

    private int resetIndexNumberAtTheEnd(int workerIndex, List<String> workers) {
        return (workerIndex == workers.size() - 1) ? RESET_INDEX : workerIndex + 1;
    }

    private int updateIndex(int workerIndex, int nextIndex) {
        return (nextIndex == RESET_INDEX) ? RESET_INDEX : workerIndex + 1;
    }

    private boolean checkContinuous(String worker, List<String> workSchedule) {
        if (workSchedule.size() > 1) {
            int lastIndex = workSchedule.size() - 1;
            return workSchedule.get(lastIndex).equals(worker);
        }
        return false;
    }
}
