package commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

/**
 * This command adds a task to the taskList, given a description and flag.
 * TODO: Will split further to account for to-do, deadline and event.
 */

public class AddTaskCommand implements Command {

    String desc;
    boolean isDone;

    public AddTaskCommand(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public String execute(Ui ui, TaskList taskList, Storage storage) {
        Task newTask = new Task(this.desc, this.isDone);
        taskList.addTask(newTask);
        try {
            storage.writeTasksToFile(taskList, storage.getTaskFile());
        } catch (IOException e) {
            return ui.getFileWriteFailResponse(storage.getTaskFile());
        }
        return ui.getAddTaskResponse(newTask);
    }

    public boolean isQuit() {
        return false;
    }
}
