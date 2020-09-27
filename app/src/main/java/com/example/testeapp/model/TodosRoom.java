package com.example.testeapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "todos_table")
public class TodosRoom {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String tarefa;

    private String desc;

    private boolean done;

    private boolean del;

    public TodosRoom(String tarefa, String desc, boolean done, boolean del) {
        this.tarefa = tarefa;
        this.desc = desc;
        this.done = done;
        this.del = del;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDel(boolean del) {
        this.del = del;
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

    public boolean isDel() {
        return del;
    }

}
