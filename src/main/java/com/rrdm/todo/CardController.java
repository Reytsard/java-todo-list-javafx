package com.rrdm.todo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CardController {

    Todo todo;
    static ObservableList<Todo> tasksList;
    static ObservableList<Todo> inProgressList;
    static ObservableList<Todo> completedList;

    @FXML
    AnchorPane rootNode;

    @FXML
    Button leftMoveButton;

    @FXML
    Button rightMoveButton;

    @FXML
    Label titleLabel;

    @FXML
    Label descLabel;

    @FXML
    Button editButton;

    @FXML
    protected void onEditButtonClick() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("editcard-view.fxml"));

        Stage newStage = new Stage();
        Scene newScene = new Scene(loader.load(), 400, 400);
        newStage.setScene(newScene);
        newStage.show();

        EditCardController controller = loader.getController();
        switch (todo.getStatus()){
            case "todo" -> controller.setObservableList(tasksList);
            case "inProgress" -> controller.setObservableList(inProgressList);
            case "completed" -> controller.setObservableList(completedList);
        }
        controller.setOldTodo(todo);
        controller.setDescriptionField(todo.getDesc());
        controller.setTitleField(todo.getTitle());
        controller.setPrioComboBox(todo.getPriority());

    }

    @FXML
    protected void moveRightButtonClick() {
        switch (todo.getStatus()) {
            case "todo":
                tasksList.remove(todo);
                todo.setStatus("inProgress");
                inProgressList.add(todo);
                break;

            case "inProgress":
                inProgressList.remove(todo);
                todo.setStatus("completed");
                completedList.add(todo);
                break;

            default:
        }
        TodoDAO.updateTodo(todo);
    }

    @FXML
    protected void moveLeftButtonClick() {
        switch (todo.getStatus()) {
            case "inProgress":
                inProgressList.remove(todo);
                todo.setStatus("todo");
                tasksList.add(todo);
                break;

            case "completed":
                completedList.remove(todo);
                todo.setStatus("inProgress");
                inProgressList.add(todo);
                break;

            default:
        }
        TodoDAO.updateTodo(todo);
    }

    public void setText(Todo todo) {
        setTodo(todo);
        titleLabel.setText(todo.getTitle());
        descLabel.setText(todo.getDesc());
    }

    public void setText(String title, String desc) {
        titleLabel.setText(title);
        descLabel.setText(desc);
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public Node getRootNode() {
        return rootNode;
    }


}
