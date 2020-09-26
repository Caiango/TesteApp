package com.example.testeapp.view

//import com.example.testeapp.viewmodel.MainViewModel
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeapp.R
import com.example.testeapp.model.TodosRoom
import com.example.testeapp.viewmodel.TodosViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterTodo.onLongClickListener {
    var adapter = AdapterTodo(this)

    private lateinit var mTodosViewModel: TodosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = AdapterTodo(this)

        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.setHasFixedSize(true)
        rv_main.adapter = adapter

        mTodosViewModel = ViewModelProvider(this).get(TodosViewModel::class.java)

        mTodosViewModel.allTodo.observe(this,
            Observer<List<TodosRoom?>?> { todosRooms -> adapter.setTodos(todosRooms) })

        actionBtnDel.setOnClickListener {
            Toast.makeText(this, "Excluir Algo", Toast.LENGTH_LONG).show()
        }

        actionBtnAdd.setOnClickListener {
            dialogAdd()
        }


    }

    //adicionando tarefa no banco
    fun insertTask(task: String, desc: String) {
        var tarefa: String = task
        var desc: String = desc
        var done = false
        var del = false

        var addTask = TodosRoom(tarefa, desc, done, del)
        mTodosViewModel.insert(addTask)
    }

    //chamando dialog para adicionar Tarefa
    fun dialogAdd() {
        val dialog = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null)
        dialog.setTitle("Adicionar Tarefa")
        dialog.setView(view)
        val tarefa = view.findViewById<EditText>(R.id.edt_tarefa_todo)
        val desc = view.findViewById<EditText>(R.id.edt_desc_todo)
        dialog.setPositiveButton("Adicionar") { _: DialogInterface, _: Int ->
            if (tarefa.text.isNotEmpty() && desc.text.isNotEmpty()) {
                insertTask(tarefa.text.trim().toString(), desc.text.trim().toString())
                Toast.makeText(this, "Tarefa Criada", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Preencha os valores", Toast.LENGTH_LONG).show()
            }

        }
        dialog.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

    override fun onLongItemClick(item: TodosRoom, position: Int) {
        val dialog = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null)
        dialog.setTitle("Alterar Tarefa")
        dialog.setView(view)
        val tarefa = view.findViewById<EditText>(R.id.edt_tarefa_todo)
        val desc = view.findViewById<EditText>(R.id.edt_desc_todo)
        dialog.setPositiveButton(this.getString(R.string.change)) { _: DialogInterface, _: Int ->
            if (tarefa.text.isNotEmpty() && desc.text.isNotEmpty()) {
                var teste = TodosRoom(tarefa.text.toString(), desc.text.toString(), item.isDone, item.isDel)
                teste.id = item.id
                // mTodosViewModel.delete(item)
                mTodosViewModel.update(teste)

            } else {
                Toast.makeText(this, "Preencha os valores", Toast.LENGTH_LONG).show()
            }

        }
        dialog.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
        }
        dialog.show()

    }

}