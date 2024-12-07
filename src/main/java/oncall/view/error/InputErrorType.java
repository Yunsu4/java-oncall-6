package oncall.view.error;

public enum InputErrorType {

    ERROR_MESSAGE("[ERROR] "),
    NEED_AVAILABLE_INPUT("유효하지 않은 입력 값입니다."),
    NEED_REENTER_INPUT(" 다시 입력해 주세요.");

    final String message;

    InputErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
