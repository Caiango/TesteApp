package com.example.testeapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.testeapp.R;
import com.example.testeapp.model.TodosRoom;
import com.example.testeapp.viewmodel.TodosViewModel;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private TodosViewModel mTodoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RecyclerView recyclerView = findViewById(R.id.rvteste);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //final AdapterTodo adapter = new AdapterTodo(this);
        //recyclerView.setAdapter(adapter);

        mTodoViewModel = ViewModelProviders.of(this).get(TodosViewModel.class);
//
//        mTodoViewModel.getAllTodo().observe(this, new Observer<List<TodosRoom>>() {
//            @Override
//            public void onChanged(List<TodosRoom> todosRooms) {
//                adapter.setTodos(todosRooms);
//            }
//        });

    }
}