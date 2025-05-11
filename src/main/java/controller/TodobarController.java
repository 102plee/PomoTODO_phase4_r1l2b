package controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Task;
import ui.EditTask;
import ui.ListView;
import ui.PomoTodoApp;
import utility.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Controller class for Todobar UI
public class TodobarController implements Initializable {
    @FXML
    private Label descriptionLabel;
    @FXML
    private JFXHamburger todoActionsPopUpBurger;
    @FXML
    private StackPane todoActionsPopUpContainer;
    @FXML
    private JFXRippler todoOptionsPopUpRippler;
    @FXML
    private StackPane todoOptionsPopUpBurger;

    private Task task;
    private JFXPopup todobarPopUp;
    private JFXPopup viewPopUp;

    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: sets the task in this Todobar
    //          updates the Todobar UI label to task's description
    public void setTask(Task task) {
        this.task = task;
        descriptionLabel.setText(task.getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTodobarPopUp();
        loadTodobarPopUpListener();

        loadTodobarActionsPopUp();
        loadTodobarActionsPopUpListener();
    }

    // EFFECTS: load options pop up (setting, exit)
    private void loadTodobarPopUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/TodoOptionsPopUp.fxml"));
            fxmlLoader.setController(new TodoBarPopUpController());
            todobarPopUp = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void loadTodobarActionsPopUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/TodoActionsPopUp.fxml"));
            fxmlLoader.setController(new TodoBarActionsPopUpController());
            viewPopUp = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    // EFFECTS: show options pop up when its icon is clicked
    private void loadTodobarPopUpListener() {
        todoOptionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                todobarPopUp.show(todoOptionsPopUpBurger,
                    JFXPopup.PopupVPosition.TOP,
                    JFXPopup.PopupHPosition.RIGHT,
                    -12,
                    15);
            }
        });
    }

    // EFFECTS: show options pop up when its icon is clicked
    private void loadTodobarActionsPopUpListener() {
        todoActionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                viewPopUp.show(todoActionsPopUpBurger,
                    JFXPopup.PopupVPosition.TOP,
                    JFXPopup.PopupHPosition.LEFT,
                    12,
                    15);
            }
        });
    }

    // Inner class: option pop up controller
    class TodoBarPopUpController {
        @FXML
        private JFXListView<?> optionPopUpList;

        @FXML
        private void submit() {
            int selectedIndex = optionPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    Logger.log("TodoBarPopUpController", "Editing this task.");
                    PomoTodoApp.setScene(new EditTask(task));
                    break;
                case 1:
                    Logger.log("TodoBarPopUpController", "Deleting this task.");
                    PomoTodoApp.getTasks().remove(task);
                    PomoTodoApp.setScene(new ListView(PomoTodoApp.getTasks()));
                    break;
                default:
                    Logger.log("TodoBarPopUpController", "No action is implemented for the selected option");
            }
            todobarPopUp.hide();
        }
    }

    class TodoBarActionsPopUpController {
        @FXML
        private JFXListView<?> actionPopUpList;

        @FXML
        private void submit() {
            int selectedIndex = actionPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                default:
                    Logger.log("TodoBarPopUpController", "No action is implemented for the selected option");
            }
            viewPopUp.hide();
        }
    }

}
