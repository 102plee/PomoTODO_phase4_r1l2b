package ui;

import controller.ListViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import model.Task;
import utility.JsonFileIO;

import java.io.IOException;
import java.util.List;

// List View: Tasks are listed (in no particular order)
public class ListView extends StackPane {
    private final List<Task> tasks;

    // REQUIRES: task != null
    // MODIFIES: this
    public ListView(List<Task> tasks) {
        this.tasks = tasks;
        JsonFileIO.write(tasks);
        this.load();
    }

    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/ListView.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            ListViewController controller = fxmlLoader.<ListViewController>getController();
            controller.setData(tasks);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}