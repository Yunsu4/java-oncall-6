package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String enteredMonthAndDow() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        return getInput();
    }

    public String enteredWeekdayWorker() {
        System.out.print(LINE_SEPARATOR+ "평일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        return getInput();
    }

    public String enteredHolidayWorker() {
        System.out.print(LINE_SEPARATOR+ "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>");
        return getInput();
    }


    private String getInput() {
        return Console.readLine();
    }
}
