package com.rrdm.todo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class AddTodoController {
    final ObservableList<String> choices = FXCollections.observableArrayList(
            "LOW", "MED", "HIGH"
    );


    @FXML
    TextField titleField;

    @FXML
    TextArea descField;

    @FXML
    ComboBox<String> priorityComboBox;

    @FXML
    Button addTodoButton;

    @FXML
    Button cancelButton;

    ObservableList<Todo> tasksList;
    ObservableList<Todo> inProgressList;
    ObservableList<Todo> completedList;

    @FXML
    protected void onAddTodoButtonClick() throws IOException, SQLException {
        String title = titleField.getText();
        String desc = descField.getText();
        PRIORITY priority = PRIORITY.valueOf(priorityComboBox.getValue().toUpperCase());

        if (validateData(title)) {
            Todo todo = new Todo(title, desc, priority);
            TodoDAO.addTodo(todo);
            tasksList.add(todo);
            ((Stage) titleField.getScene().getWindow()).close();
        }
    }

    public void initialize() {
        priorityComboBox.setItems(choices);
        priorityComboBox.setValue("Low");
    }

    private boolean validateData(String title) {

        if (title.isEmpty()) {
            titleField.setBorder(new Border(new BorderStroke(
                    Color.RED, // Border color
                    BorderStrokeStyle.SOLID, // Border style
                    CornerRadii.EMPTY, // Corner radius
                    BorderWidths.DEFAULT // Border width
            )));
        }
        return !title.isEmpty();

    }

    @FXML
    protected void onCancelButtonClick() {

    }

    public void setLists(ObservableList<Todo> tasksList, ObservableList<Todo> inProgressList, ObservableList<Todo> completedList) {
        this.tasksList = tasksList;
        this.inProgressList = inProgressList;
        this.completedList = completedList;
    }

}
