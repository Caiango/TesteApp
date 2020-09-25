package com.example.testeapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodosDao {

    @Insert
    void insert(TodosRoom todo);

    @Update
    void update(TodosRoom todo);

    @Delete
    void delete(TodosRoom todo);

    @Query("DELETE FROM todos_table")
    void deleteAllTodos();

    @Query("SELECT * FROM todos_table")
    LiveData<List<TodosRoom>> getAllTodos();
}
