package com.rrdm.todo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TodoListController {

    ArrayList<Todo> todos = new ArrayList<>();

    CardListCell cardListCell;

    // Make ObservableLists the main data store:
    private ObservableList<Todo> tasksList;
    private ObservableList<Todo> inProgressList;
    private ObservableList<Todo> completedList;

    @FXML
    ListView<Todo> tasksListView;
    @FXML
    ListView<Todo> inProgressListView;
    @FXML
    ListView<Todo> completedListView;

    @FXML
    Button addButton;
    @FXML
    VBox todo;
    @FXML
    VBox doing;
    @FXML
    VBox finished;

    public void initialize() throws SQLException {

        tasksList = FXCollections.observableArrayList();
        inProgressList = FXCollections.observableArrayList();
        completedList = FXCollections.observableArrayList();
        System.out.println("initializing todolist");

        try{
            CardController.tasksList = tasksList;
            CardController.inProgressList = inProgressList;
            CardController.completedList = completedList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        tasksListView.setCellFactory(lv -> new CardListCell());

        inProgressListView.setCellFactory(lv -> new CardListCell());

        completedListView.setCellFactory(lv -> new CardListCell());

        // bind the ListViews once
        tasksListView.setItems(tasksList);
        inProgressListView.setItems(inProgressList);
        completedListView.setItems(completedList);

        loadTodos();
    }

    public void addToTasks(Todo todo) {
        tasksList.add(todo);
        tasksListView.setItems(tasksList);
    }

    private void loadTodos() throws SQLException {
        // clear before reloading
        tasksList.clear();
        inProgressList.clear();
        completedList.clear();

        todos = TodoDAO.getTodos();

        for (Todo currentTodo : todos) {
            switch (currentTodo.getStatus()) {
                case "todo" -> tasksList.add(currentTodo);
                case "inProgress" -> inProgressList.add(currentTodo);
                case "completed" -> completedList.add(currentTodo);
            }
        }



        System.out.println("tasks lists: " + tasksList.size());
    }

    @FXML
    protected void onAddButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(AddTodoController.class.getResource("addtodo-view.fxml"));
        Stage addTodoListStage = new Stage();
        addTodoListStage.setTitle("Add Todo");
        Scene scene = new Scene(loader.load(), 400, 400);
        addTodoListStage.setScene(scene);
        addTodoListStage.show();

        AddTodoController controller = loader.getController();
        controller.setLists(tasksList, inProgressList, completedList);
    }

    public void updateList() throws SQLException {
        System.out.println("updating todolist");
        loadTodos(); // just reload

        tasksListView.refresh();
        inProgressListView.refresh();
        completedListView.refresh();
    }

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }
}
