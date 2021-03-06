package com.example.testeapp.view


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeapp.R
import com.example.testeapp.model.TodosRoom
import com.example.testeapp.viewmodel.TodosViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterTodo.onLongClickListener,
    AdapterTodo.onClickListener {

    private lateinit var mTodosViewModel: TodosViewModel
    private var listTodos: List<TodosRoom?>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = AdapterTodo(this, this)

        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.setHasFixedSize(true)
        rv_main.adapter = adapter

        mTodosViewModel = ViewModelProvider(this).get(TodosViewModel::class.java)

        //obersavando alterações na lista e atualizando no adapter da RecyclerView
        mTodosViewModel.allTodo.observe(this,
            Observer<List<TodosRoom?>?> { todosRooms ->
                adapter.setTodos(todosRooms)
                if (todosRooms.isEmpty()) {
                    actionBtnDel.visibility = View.INVISIBLE
                } else {
                    actionBtnDel.visibility = View.VISIBLE
                }
                listTodos = todosRooms
            })

        actionBtnDel.setOnClickListener {

            //deixar todos os elementos possíveis de exclusão no click e vice versa
            for (item in listTodos!!) {
                var del = !item!!.isDel
                var updt = TodosRoom(item.tarefa, item.desc, item.isDone, del)
                updt.id = item.id
                mTodosViewModel.update(updt)
            }

        }

        actionBtnDel.setOnLongClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Deseja Excluir todas as Atividades?")
            dialog.setPositiveButton("Sim") { _: DialogInterface, _: Int ->
                mTodosViewModel.deleteAllTodo()
                Toast.makeText(this, "Todas as Atividades foram Excluídas", Toast.LENGTH_SHORT)
                    .show()
            }
            dialog.setNegativeButton(R.string.cancelarDialog) { dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(this, R.string.cancelarToast, Toast.LENGTH_SHORT).show()
            }
            dialog.show()
            true
        }

        actionBtnAdd.setOnClickListener {
            dialogAdd()
        }

    }

    //adicionando tarefa no banco
    private fun insertTask(task: String, desc: String) {
        val tarefa: String = task
        val desc: String = desc
        val done = false
        val del = false

        val addTask = TodosRoom(tarefa, desc, done, del)

        for (item in listTodos!!) {
            if (item?.isDel == true) {
                var del = !item!!.isDel
                var upt = TodosRoom(item.tarefa, item.desc, item.isDone, del)
                upt.id = item.id
                mTodosViewModel.update(upt)
            }
        }

        mTodosViewModel.insert(addTask)

    }

    //chamando dialog para adicionar Tarefa
    private fun dialogAdd() {
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
                Toast.makeText(this, getString(R.string.preencherDialog), Toast.LENGTH_LONG).show()
            }

        }
        dialog.setNegativeButton(getString(R.string.cancelarDialog)) { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(this, R.string.cancelarToast, Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

    //função click longo RecyclerView para alterar item selecionado
    override fun onLongItemClick(item: TodosRoom, position: Int) {
        val dialog = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null)
        dialog.setTitle("Alterar Tarefa")
        dialog.setView(view)
        val tarefa = view.findViewById<EditText>(R.id.edt_tarefa_todo)
        val desc = view.findViewById<EditText>(R.id.edt_desc_todo)
        tarefa.setText(item.tarefa)
        desc.setText(item.desc)
        dialog.setPositiveButton(this.getString(R.string.alterarDialog)) { _: DialogInterface, _: Int ->
            if (tarefa.text.isNotEmpty() && desc.text.isNotEmpty()) {
                val teste =
                    TodosRoom(tarefa.text.toString(), desc.text.toString(), item.isDone, item.isDel)
                teste.id = item.id
                // mTodosViewModel.delete(item)
                mTodosViewModel.update(teste)

            } else {
                Toast.makeText(this, getString(R.string.preencherDialog), Toast.LENGTH_LONG).show()
            }

        }
        dialog.setNegativeButton(R.string.cancelarDialog) { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(this, getString(R.string.cancelarToast), Toast.LENGTH_SHORT).show()
        }
        dialog.show()

    }

    //função click RecyclerView para deletar o item selecionado e atualizar check
    override fun onItemClick(item: TodosRoom, position: Int) {

        //fazendo update para alterar o icone checked no click, para marcar a tarefa como concluída
        var newDone = item.isDone
        var updateCheck = TodosRoom(item.tarefa, item.desc, !newDone, item.isDel)
        updateCheck.id = item.id
        mTodosViewModel.update(updateCheck)

        //caso isDel for true, deletar o item selecionado
        if (item.isDel) {
            //repete o código acima para não mudar o check mp click
            var newDone = item.isDone
            var updateCheck = TodosRoom(item.tarefa, item.desc, newDone, item.isDel)
            updateCheck.id = item.id
            mTodosViewModel.update(updateCheck)


            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Deseja Excluir a Atividade: ${item.tarefa}?")
            dialog.setPositiveButton("Sim") { _: DialogInterface, _: Int ->
                mTodosViewModel.delete(item)
                Toast.makeText(applicationContext, "Tarefa Excluída", Toast.LENGTH_LONG).show()
            }
            dialog.setNegativeButton(R.string.cancelarDialog) { _: DialogInterface, i: Int ->
                Toast.makeText(this, getString(R.string.cancelarToast), Toast.LENGTH_SHORT).show()
            }
            dialog.show()

        }

    }


}