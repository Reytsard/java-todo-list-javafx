package com.rrdm.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class TodoDAO {
    private static TodoDatabase todoDatabase;


    public static ArrayList<Todo> getTodos() throws SQLException {
        return todoDatabase.getTodos();
    }

    public static synchronized void addTodo(Todo todo) throws SQLException {
        todoDatabase.addTodo(todo);
    }

    public static synchronized void updateTodo(Todo todo) {
        try {
            todoDatabase.updateTodo(todo);
        } catch (SQLException e) {
            System.out.println("failed to update todo");
        }
    }

    public static synchronized void deleteTodo(Todo todo) throws SQLException {
        todoDatabase.deleteTodo(todo);
    }

    public static void startConnection() throws SQLException {
        todoDatabase = new TodoDatabase();
    }
}
