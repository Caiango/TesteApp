package com.example.testeapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todos_table")
public class TodosRoom {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String tarefa;

    private String desc;

    private boolean done;

    public TodosRoom(String tarefa, String desc, boolean done) {
        this.tarefa = tarefa;
        this.desc = desc;
        this.done = done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isDone() {
        return done;
    }
}
