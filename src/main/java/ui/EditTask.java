package ui;

import controller.EditTaskController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import model.Task;

import java.io.IOException;

// Edit task UI
public class EditTask extends StackPane {
    private final Task task;

    public EditTask(Task task) {
        this.task = task;
        this.load();
    }

    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/EditTask.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            EditTaskController controller = fxmlLoader.<EditTaskController>getController();
            controller.setTask(task);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}