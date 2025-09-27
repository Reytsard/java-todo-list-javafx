package com.rrdm.todo;

import java.sql.*;
import java.util.ArrayList;

public class TodoDatabase {
    private Connection conn;

    public TodoDatabase() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:todos.db");
        initTable();
    }

    public void initTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS todo(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR(256) NOT NULL," +
                "description TEXT," +
                "priority VARCHAR(5) NOT NULL," +
                "STATUS VARCHAR(10) NOT NULL" +
                ");";

        Statement stmt = conn.createStatement();
        stmt.execute(query);
    }

    public void addTodo(Todo todo) throws SQLException {
        String query = "INSERT INTO TODO VALUES (?,?,?,?,?);";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, todo.getId());
        ps.setString(2, todo.getTitle());
        ps.setString(3, todo.getDesc());
        ps.setString(4, todo.getPriority().toString());
        ps.setString(5, todo.getStatus());

        ps.execute();
    }

    public void updateTodo(Todo todo) throws SQLException {
        String query = "UPDATE TODO SET title=?,description=?,priority=?,status=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, todo.getTitle());
        ps.setString(2, todo.getDesc());
        ps.setString(3, todo.getPriority().toString());
        ps.setString(4, todo.getStatus());
        ps.setInt(5, todo.getId());

        ps.execute();
        System.out.println("id: "+todo.getId()+ " has been updated") ;
    }

    public ArrayList<Todo> getTodos() throws SQLException {

        ArrayList<Todo> todos = new ArrayList<>();

        String query = "SELECT * FROM todo;";
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery(query);

        while(results.next()){
            Todo todo = new Todo(
                    results.getInt(1),
                    results.getString(2),
                    results.getString(3),
                    results.getString(4),
                    results.getString(5)
                    );
            System.out.println(todo);
            todos.add(todo);
        }

        return todos;
    }

    public void deleteTodo(Todo todo) throws SQLException {
        String query = "DELETE FROM TODO WHERE ID=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,todo.getId());
        ps.execute();
    }
}
