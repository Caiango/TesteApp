package com.example.testeapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testeapp.model.TodoRepository;
import com.example.testeapp.model.TodosRoom;

import java.util.List;

public class TodosViewModel extends AndroidViewModel {

    private TodoRepository repository;
    private LiveData<List<TodosRoom>> allTodo;

    public TodosViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoRepository(application);
        allTodo = repository.getAllTodos();
    }

    public void insert(TodosRoom todo) {
        repository.insert(todo);
    }

    public void update(TodosRoom todo) {
        repository.update(todo);
    }

    public void delete(TodosRoom todo) {
        repository.delete(todo);
    }

    public void deleteAllTodo() {
        repository.deleteAllTodos();
    }

    public LiveData<List<TodosRoom>> getAllTodo() {
        return allTodo;
    }


}
