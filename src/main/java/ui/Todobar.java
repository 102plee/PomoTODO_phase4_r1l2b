package ui;

import controller.TodobarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import model.Task;

import java.io.IOException;

// Each task is presented in a Todobar
public class Todobar extends VBox {
    private final Task task;

    public Todobar(Task task) {
        this.task = task;
        load();
    }

    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/Todobar.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            TodobarController controller = fxmlLoader.<TodobarController>getController();
            controller.setTask(task);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
