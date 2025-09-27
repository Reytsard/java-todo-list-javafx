package com.rrdm.todo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            TodoDAO.startConnection();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("todolist-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setTitle("Todo!");
            stage.setScene(scene);
            stage.show();
            TodoListController controller = fxmlLoader.getController();
            controller.setTodos(TodoDAO.getTodos());
        } catch (SQLException e) {
            System.out.println("Database was not setup. " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
