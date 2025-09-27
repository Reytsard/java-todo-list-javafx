package com.rrdm.todo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditCardController {

    final ObservableList<String> priorities = FXCollections.observableArrayList("LOW","MED","HIGH");

    ObservableList<Todo> observableList;


    Todo oldTodo;

    @FXML
    TextField titleField;

    @FXML
    TextArea descriptionField;

    @FXML
    ComboBox<String> prioComboBox;

    @FXML
    Button cancelButton;

    @FXML
    Button saveButton;

    public void initialize() {
        prioComboBox.setItems(priorities);
    }

    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onSaveButtonClick() {
        try {
            Todo newTodo = new Todo(titleField.getText(), descriptionField.getText(), PRIORITY.valueOf(prioComboBox.getValue()));

            System.out.println(TodoDAO.getTodos());
            TodoDAO.updateTodo(newTodo);
            observableList.set(observableList.indexOf(oldTodo),newTodo);

            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println("Error updating todo: " +e);
        }
    }



    public void setTitleField(String title) {
        titleField.setText(title);
    }

    public void setDescriptionField(String desc) {
        descriptionField.setText(desc);
    }

    public void setPrioComboBox(PRIORITY priority) {
        prioComboBox.setValue(priority.toString());
    }

    public void setOldTodo(Todo oldTodo) {
        this.oldTodo = oldTodo;
    }

    public void setObservableList(ObservableList<Todo> list){
        observableList = list;
    }
}
