package com.example.testeapp.view

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testeapp.R
import com.example.testeapp.model.TodoData


class AdapterTodo(val dataList: ArrayList<TodoData>) :
    RecyclerView.Adapter<AdapterTodo.HolderData>() {

    companion object {
        var pos = 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HolderData {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_lay, parent, false)
        return HolderData(v)
    }

    override fun onBindViewHolder(holder: HolderData, position: Int) {
        val data = dataList[position]
        holder.txTarefa.text = data.tarefa
        holder.txDesc.text = (data.desc)

        //click longo responsável por alterar o valor da view selecionada
        holder.lay.setOnLongClickListener {
            dialog(it.context, holder)
            true
        }

        //click responsável por setta visibilidade ao check da task
        holder.lay.setOnClickListener {
            //se del for falso
            if (data.del == false) {
                //done vai receber o oposto
                data.done = !data.done
                holder.check.visibility = if (data.done) View.VISIBLE else View.INVISIBLE
            } else {
                pos = position
                Toast.makeText(
                    it.context,
                    "Remover elemento na posição" + position,
                    Toast.LENGTH_LONG
                ).show()
            }

        }

        // del visibility, se for verdadeiro fica visívivel...
        holder.del.visibility = if (data.del) View.VISIBLE else View.INVISIBLE


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class HolderData(v: View) : RecyclerView.ViewHolder(v) {
        val txTarefa = v.findViewById<TextView>(R.id.rv_tarefa)
        val txDesc = v.findViewById<TextView>(R.id.rv_desc)
        val lay = v.findViewById<ConstraintLayout>(R.id.rv_const)
        val check = v.findViewById<ImageView>(R.id.img_check)
        val del = v.findViewById<ImageView>(R.id.img_del)
    }

    //função responsável por chamar dialog de alteração
    fun dialog(context: Context, holder: HolderData) {
        val dialog = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add, null)
        dialog.setTitle("Alterar Tarefa")
        dialog.setView(view)
        val tarefa = view.findViewById<EditText>(R.id.edt_tarefa_todo)
        val desc = view.findViewById<EditText>(R.id.edt_desc_todo)
        dialog.setPositiveButton(context.getString(R.string.change)) { _: DialogInterface, _: Int ->
            if (tarefa.text.isNotEmpty() && desc.text.isNotEmpty()) {
                holder.txTarefa.text = tarefa.text
                holder.txDesc.text = desc.text
            } else {
                Toast.makeText(context, "Preencha os valores", Toast.LENGTH_LONG).show()
            }

        }
        dialog.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

}