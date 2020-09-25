package com.example.testeapp.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeapp.R
import com.example.testeapp.viewmodel.MainViewModel
import com.example.testeapp.viewmodel.TodosViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var mMainViewModel: MainViewModel

    private lateinit var mTodosViewModel: TodosViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mTodosViewModel = ViewModelProvider(this).get(TodosViewModel::class.java)

        try {


            mTodosViewModel!!.allTodo.observe(this, {
                //Update Recycler view
                Toast.makeText(applicationContext, "OnChanged", Toast.LENGTH_LONG).show()
            })
        }catch (e: Exception){
            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_LONG).show()
            Log.e("Errooo", e.toString())
        }

        mMainViewModel.add(rv_main)

        actionBtnDel.setOnClickListener{
            mMainViewModel.iconDel(rv_main)
        }

        rv_main.layoutManager = LinearLayoutManager(this)

        actionBtnAdd.setOnClickListener {

            mMainViewModel.dialog(this, rv_main)
        }

    }


}