package com.example.testeapp.viewmodel

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.testeapp.AdapterTodo
import com.example.testeapp.R
import com.example.testeapp.TodoData


class MainViewModel : ViewModel() {
    var lista: ArrayList<TodoData> = ArrayList()


    fun add() {
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
    }

    fun dialog(context: Context, v: RecyclerView) {
        val dialog = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add, null)
        dialog.setView(view)
        val tarefa = view.findViewById<EditText>(R.id.edt_tarefa_todo)
        val desc = view.findViewById<EditText>(R.id.edt_desc_todo)
        dialog.setPositiveButton("Alterar") { _: DialogInterface, _: Int ->
            var add = TodoData(tarefa.text.toString(), desc.text.toString())
            lista.add(add)
            v.adapter = AdapterTodo(lista)
        }
        dialog.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

}