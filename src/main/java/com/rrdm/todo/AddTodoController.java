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
import java.util.List;

public class AddTodoController {
    final ObservableList<String> choices = FXCollections.observableArrayList(
    "Low","Med","High"
    );
    TodoListController todoListController;

    TodoDAO todoDAO;

    @FXML
    TextField titleField;

    @FXML
    TextArea descField;

    @FXML
    ComboBox<String> priorityComboBox ;

    @FXML
    Button addTodoButton;

    @FXML
    Button cancelButton;

    @FXML
    protected void onAddTodoButtonClick() {
        String title = titleField.getText();
        String desc = descField.getText();
        PRIORITY priority = PRIORITY.valueOf(priorityComboBox.getValue().toUpperCase());

        if(validateData(title,desc)){
            Todo todo = new Todo(title,desc,priority);
            if(todoDAO != null){
                todoDAO.addTodo(todo);
            }else{
                todoDAO = new TodoDAO();
                todoDAO.addTodo(todo);
            }
            ((Stage) titleField.getScene().getWindow()).close();
            todoListController.updateList();
        }
    }

    public void initialize(){
        todoListController = new TodoListController();
        priorityComboBox.setItems(choices);
        priorityComboBox.setValue("Low");
    }

    private boolean validateData(String title, String desc) {


        if (title.isEmpty()) {
            titleField.setBorder(new Border(new BorderStroke(
                    Color.RED, // Border color
                    BorderStrokeStyle.SOLID, // Border style
                    CornerRadii.EMPTY, // Corner radius
                    BorderWidths.DEFAULT // Border width
            )));
        }
        if(desc.isEmpty()){
            descField.setBorder(new Border(new BorderStroke(
                    Color.RED, // Border color
                    BorderStrokeStyle.SOLID, // Border style
                    CornerRadii.EMPTY, // Corner radius
                    BorderWidths.DEFAULT // Border width
            )));
        }
        return !title.isEmpty() && !desc.isEmpty();

    }

    @FXML
    protected void onCancelButtonClick() {

    }
}
