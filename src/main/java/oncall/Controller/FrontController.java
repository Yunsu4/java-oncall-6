package oncall.Controller;


import java.util.List;
import java.util.Map;
import oncall.enums.ClassificationOfWork;
import oncall.model.ScheduleManager;
import oncall.model.Workers;
import oncall.view.InputView;
import oncall.view.OutputView;
import oncall.view.error.ErrorException;

public class FrontController {
    private InputView inputView;
    private OutputView outputView;
    private DateController dateController;


    public FrontController(InputView inputView, OutputView outputView,
                           DateController dateController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.dateController = dateController;
    }

    public void runMatchingProgram() throws ErrorException {
        Map<Integer, ClassificationOfWork> parsedDays = dateController.handleDate();
        int month = dateController.getMonth();
        List<String> dayOfWeekOfTheMonth = dateController.getDowOfTheMonth();

        Workers workers = new Workers(inputView);
        ScheduleManager scheduleManager = new ScheduleManager(workers);

        for (int day = 1; day <= parsedDays.size(); day++) {
            String dow = scheduleManager.matchingSchedule(parsedDays, dayOfWeekOfTheMonth, day);
            outputView.printWorkSchedule(month, day, dow, scheduleManager.getSchedule(day));
        }
    }
}
