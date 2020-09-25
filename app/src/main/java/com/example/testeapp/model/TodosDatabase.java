package com.example.testeapp.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TodosRoom.class}, version = 1)
public abstract class TodosDatabase extends RoomDatabase {

    private static TodosDatabase instance;

    public abstract TodosDao todosDao();

    public static synchronized TodosDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TodosDatabase.class, "todos_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
