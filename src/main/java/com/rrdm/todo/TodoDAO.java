package com.rrdm.todo;

import java.util.ArrayList;

public class TodoDAO {
    private static ArrayList<Todo> todos;

    public TodoDAO() {
        todos = new ArrayList<>(3);
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public synchronized void addTodo(Todo todo) {
        todos.add(todo);
    }

    public synchronized void updateTodo(Todo todo) {

    }

    public synchronized void updateTodo(int index, Todo todo) {
        todos.set(index, todo);
    }

    public synchronized Todo deleteTodo(Todo todo) {
        for (int i = 0; i < todos.size() - 1; i++) {
            Todo currentTodo = todos.get(i);
            if (todo.getTitle().compareTo(currentTodo.getTitle()) == 0 &&
                    todo.getDesc().compareToIgnoreCase(currentTodo.getDesc()) == 0) {
                todos.remove(currentTodo);
                return currentTodo;
            }
        }
        return null;
    }
}
