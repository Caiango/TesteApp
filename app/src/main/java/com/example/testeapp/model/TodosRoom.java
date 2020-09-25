package com.example.testeapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todos_table")
public class TodosRoom {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String tarefa;

    private String desc;

    public TodosRoom(String tarefa, String desc) {
        this.tarefa = tarefa;
        this.desc = desc;
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
}
