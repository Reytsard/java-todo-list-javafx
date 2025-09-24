package com.rrdm.todo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TodoListController {

    TodoDAO todoDAO = new TodoDAO();
    ArrayList<Todo> tasks = new ArrayList<>(2);
    ArrayList<Todo> inProgress = new ArrayList<>(2);
    ArrayList<Todo> completed = new ArrayList<>(2);

    @FXML
    Button addButton;

    @FXML
    VBox todo;

    @FXML
    VBox doing;

    @FXML
    VBox finished;

    @FXML
    ListView<Todo> tasksListView;

    @FXML
    ListView<Todo> inProgressListView;

    @FXML
    ListView<Todo> completedListView;

    public void initialize() {

        System.out.println("initializing todolist");

        ArrayList<Todo> todos = todoDAO.getTodos();

        for (int i = 0; i < todos.size() - 1; i++) {
            Todo currentTodo = todos.get(i);
            switch (currentTodo.getStatus()) {
                case "todo" -> tasks.add(currentTodo);
                case "inProgress" -> inProgress.add(currentTodo);
                case "completed" -> completed.add(currentTodo);
            }
        }

        ObservableList<Todo> tasksList = FXCollections.observableArrayList(tasks);
        ObservableList<Todo> inProgressList = FXCollections.observableArrayList(inProgress);
        ObservableList<Todo> completedList = FXCollections.observableArrayList(completed);

        updateListViews(tasksList, inProgressList, completedList);

        System.out.println("taskslists: " + tasksList.size());
    }

    private void updateListViews(ObservableList<Todo> tasksList, ObservableList<Todo> inProgressList, ObservableList<Todo> completedList) {
        tasksListView = new ListView<>(tasksList);
        inProgressListView = new ListView<>(inProgressList);
        completedListView = new ListView<>(completedList);
    }

    @FXML
    protected void onAddButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(AddTodoController.class.getResource("addtodo-view.fxml"));
        Stage addTodoListStage = new Stage();
        addTodoListStage.setTitle("Add Todo");
        Scene scene = new Scene(loader.load(), 400, 400);
        addTodoListStage.setScene(scene);
        addTodoListStage.show();
    }

    public void updateList() {
        System.out.println("updating todolist");

        ArrayList<Todo> todos = todoDAO.getTodos();

        for (int i = 0; i < todos.size() - 1; i++) {
            Todo currentTodo = todos.get(i);
            switch (currentTodo.getStatus()) {
                case "todo" -> tasks.add(currentTodo);
                case "inProgress" -> inProgress.add(currentTodo);
                case "completed" -> completed.add(currentTodo);
            }
        }

        ObservableList<Todo> tasksList = FXCollections.observableArrayList(tasks);
        ObservableList<Todo> inProgressList = FXCollections.observableArrayList(inProgress);
        ObservableList<Todo> completedList = FXCollections.observableArrayList(completed);

        updateListViews(tasksList, inProgressList, completedList);

        System.out.println("taskslists: " + tasksList.size());
    }


}
