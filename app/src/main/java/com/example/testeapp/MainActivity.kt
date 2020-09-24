package com.example.testeapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var lista: ArrayList<TodoData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lista = ArrayList()

        var tarefa1 = TodoData("Estudar SQLite", "Aprender como implementar o SQLite")
        var tarefa2 =
            TodoData("Autenticação GitHub", "Descobrir como funciona a autenticação com GitHub")
        var tarefa3 = TodoData(
            "Criar Floating Action Buttons",
            "Fazer o layout compatível (Bonito) com os Floating Buttons"
        )

        lista.add(tarefa1)
        lista.add(tarefa2)
        lista.add(tarefa3)

        rv_main.layoutManager = LinearLayoutManager(this)


        actionBtnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val view = LayoutInflater.from(applicationContext).inflate(R.layout.dialog_add, null)
            dialog.setView(view)
            val tarefa = view.findViewById<EditText>(R.id.edt_tarefa_todo)
            val desc = view.findViewById<EditText>(R.id.edt_desc_todo)
            dialog.setPositiveButton("Adicionar") { _: DialogInterface, _: Int ->
                var add = TodoData(tarefa.text.toString(), desc.text.toString())
                lista.add(add)
                rv_main.adapter = AdapterTodo(lista)
            }
            dialog.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(applicationContext, "Cancelado", Toast.LENGTH_SHORT).show()
            }
            dialog.show()
        }

    }

}