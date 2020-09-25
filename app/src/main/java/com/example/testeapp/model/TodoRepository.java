package com.example.testeapp.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoRepository {
    private TodosDao todoDao;
    private LiveData<List<TodosRoom>> allTodos;

    public TodoRepository(Application application) {
        TodosDatabase database = TodosDatabase.getInstance(application);
        todoDao = database.todosDao();
        allTodos = todoDao.getAllTodos();
    }

    public void insert(TodosRoom todo) {
        new InsertTodoAsyncTask(todoDao).execute(todo);
    }

    public void update(TodosRoom todo) {
        new UpdateTodoAsyncTask(todoDao).execute(todo);
    }

    public void delete(TodosRoom todo) {
        new DeleteTodoAsyncTask(todoDao).execute(todo);
    }

    public void deleteAllTodos(){
        new DeleteAllTodoAsyncTask(todoDao).execute();
    }

    public LiveData<List<TodosRoom>> getAllTodos() {
        return allTodos;
    }

    private static class InsertTodoAsyncTask extends AsyncTask<TodosRoom, Void, Void> {
        private TodosDao todosDao;

        private InsertTodoAsyncTask(TodosDao todosDao) {
            this.todosDao = todosDao;
        }

        @Override
        protected Void doInBackground(TodosRoom... todosRooms) {
            todosDao.insert(todosRooms[0]);
            return null;
        }
    }

    private static class UpdateTodoAsyncTask extends AsyncTask<TodosRoom, Void, Void> {
        private TodosDao todosDao;

        private UpdateTodoAsyncTask(TodosDao todosDao) {
            this.todosDao = todosDao;
        }

        @Override
        protected Void doInBackground(TodosRoom... todosRooms) {
            todosDao.update(todosRooms[0]);
            return null;
        }
    }

    private static class DeleteTodoAsyncTask extends AsyncTask<TodosRoom, Void, Void> {
        private TodosDao todosDao;

        private DeleteTodoAsyncTask(TodosDao todosDao) {
            this.todosDao = todosDao;
        }

        @Override
        protected Void doInBackground(TodosRoom... todosRooms) {
            todosDao.delete(todosRooms[0]);
            return null;
        }
    }

    private static class DeleteAllTodoAsyncTask extends AsyncTask<Void, Void, Void> {
        private TodosDao todosDao;

        private DeleteAllTodoAsyncTask(TodosDao todosDao) {
            this.todosDao = todosDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todosDao.deleteAllTodos();
            return null;
        }
    }
}
