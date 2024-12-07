package oncall;

import oncall.Controller.DateController;
import oncall.Controller.FrontController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        DateController dateController = new DateController(inputView);

        final FrontController frontController = new FrontController(
                inputView, outputView, dateController);

        frontController.runMatchingProgram();

    }
}
