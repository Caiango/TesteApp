package com.example.testeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var MainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainViewModel = MainViewModel()


        MainViewModel.add()

        rv_main.layoutManager = LinearLayoutManager(this)


        actionBtnAdd.setOnClickListener {

            MainViewModel.dialog(this, rv_main)
        }

    }

}