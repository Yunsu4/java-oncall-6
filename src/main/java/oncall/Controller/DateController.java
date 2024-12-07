package oncall.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import oncall.enums.ClassificationOfWork;
import oncall.enums.DayOfWeek;
import oncall.view.InputView;
import oncall.view.error.ErrorException;
import oncall.view.error.InputErrorType;
import org.mockito.internal.util.Supplier;


public class DateController {

    private final String COMMA = "\\s*,\\s*";
    private final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final List<String> MONTH_IN_YEAR = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
            "12");

    private final InputView inputView;
    private int month;
    private int firstDayOfWeek;
    private int numberOfDaysAMonth;


    public DateController(InputView inputView) {
        this.inputView = inputView;
    }

    public int getMonth() {
        return month;
    }

    public List<String> getDowOfTheMonth() {
        return DayOfWeek.getDow(numberOfDaysAMonth, firstDayOfWeek);
    }

    public Map<Integer, ClassificationOfWork> handleDate() {
        while (true) {
            try {
                String[] enteredMonthAndDow = getValidInput(inputView::enteredMonthAndDow, this::extractValidInput);
                month = getValidMonth(enteredMonthAndDow[0]);
                numberOfDaysAMonth = getDaysOfTheMonth(month);
                firstDayOfWeek = DayOfWeek.getValidDayOfWeek(enteredMonthAndDow[1]);
                return ClassificationOfWork.getClassificationOfWork(month, numberOfDaysAMonth, firstDayOfWeek);
            } catch (ErrorException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getValidMonth(String input) throws ErrorException {
        boolean isMatch = MONTH_IN_YEAR.stream()
                .anyMatch(oneMonth -> oneMonth.equals(input));
        if (isMatch) {
            return Integer.parseInt(input);
        }
        throw new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT);
    }

    private int getDaysOfTheMonth(int enteredMonth) {
        if (enteredMonth < 0 || enteredMonth >= DAYS_IN_MONTH.length) {
            throw new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT);
        }
        return DAYS_IN_MONTH[enteredMonth];
    }

    private String[] extractValidInput(String input) throws ErrorException {
        checkIsEmpty(input);
        String[] splitInput = input.split(COMMA);
        if (splitInput.length != 2) {
            throw new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT);
        }
        return splitInput;
    }

    private void checkIsEmpty(String input) throws ErrorException {
        if (input.isEmpty()) {
            throw new ErrorException(InputErrorType.NEED_AVAILABLE_INPUT);
        }
    }

    private <T> T getValidInput(Supplier<String> inputSupplier, Function<String, T> converter) {
        while (true) {
            String input = inputSupplier.get();
            try {
                return converter.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
