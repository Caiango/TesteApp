package com.example.testeapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeapp.R
import com.example.testeapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rv_lay.*

class MainActivity : AppCompatActivity() {

    private lateinit var mMainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mMainViewModel.add()

        actionBtnDel.setOnClickListener{
            mMainViewModel.iconDel(rv_main)
        }

        rv_main.layoutManager = LinearLayoutManager(this)

        actionBtnAdd.setOnClickListener {

            mMainViewModel.dialog(this, rv_main)
        }

    }

}