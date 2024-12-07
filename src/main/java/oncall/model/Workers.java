package oncall.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import oncall.view.InputView;
import oncall.view.error.ErrorException;
import oncall.view.error.InputErrorType;


public class Workers {
    private static final String COMMA = "\\s*,\\s*";
    public static final int MAX_NUMBER_OF_WORKER = 35;
    public static final int MIN_NUMBER_OF_WORKER = 5;
    public static final int MAX_LENGTH_OF_NAME = 5;

    private List<String> weekday;
    private List<String> holiday;
    private final InputView inputView;

    public Workers(InputView inputView) {
        this.inputView = inputView;
        getValidWorkers();
    }

    public List<String> getWeekday() {
        return weekday;
    }

    public List<String> getHoliday() {
        return holiday;
    }

    private void getValidWorkers() {
        try {
            weekday = getValidWeekdayWorkers();
            holiday = getValidHolidayWorkers();
        } catch (ErrorException e) {
            System.out.println(e.getMessage());
            getValidWorkers();
        }
    }

    private List<String> getValidWeekdayWorkers() throws ErrorException {
        String input = inputView.enteredWeekdayWorker();
        String[] workers = extractValidInput(input);
        checkValidInput(workers);

        return new LinkedList<>(Arrays.asList(workers));
    }

    private List<String> getValidHolidayWorkers() throws ErrorException {
        String input = inputView.enteredHolidayWorker();
        String[] workers = extractValidInput(input);
        checkValidInput(workers);

        return new LinkedList<>(Arrays.asList(workers));
    }

    private static void checkValidInput(String[] workers) {
        if (workers.length > MAX_NUMBER_OF_WORKER || workers.length < MIN_NUMBER_OF_WORKER) {
            throw new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT);
        }
        for (String worker : workers) {
            if (worker.length() > MAX_LENGTH_OF_NAME) {
                throw new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT);
            }
        }
        Set<String> validWorkers = new HashSet<>(Arrays.asList(workers));
        if (validWorkers.size() != workers.length) {
            throw new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT);
        }
    }

    private String[] extractValidInput(String input) throws ErrorException {
        checkIsEmpty(input);
        return input.split(COMMA);
    }

    private void checkIsEmpty(String input) {
        if (input.isEmpty()) {
            throw new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT);
        }
    }
}
