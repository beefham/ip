package commands;

import java.io.IOException;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Deadline;

public class AddDeadlineCommand extends AddTaskCommand {
    LocalDate deadline;

    public AddDeadlineCommand(String desc, boolean isDone, LocalDate deadline) {
        super(desc, isDone);
        this.deadline = deadline;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Deadline newDeadline = new Deadline(this.desc, this.isDone, this.deadline);
        taskList.addTask(newDeadline);
        ui.printAddTask(newDeadline);
        try {
            storage.writeTasksToFile(taskList, storage.getTaskFile());
        } catch (IOException e) {
            ui.printFileWriteFail(storage.getTaskFile());
        }
    }
}
