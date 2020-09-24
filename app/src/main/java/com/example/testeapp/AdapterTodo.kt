package com.example.testeapp

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class AdapterTodo(val dataList: ArrayList<TodoData>) :
    RecyclerView.Adapter<AdapterTodo.HolderData>() {

    interface OnItemLongClickListener {
        fun onItemLongClicked(position: Int): Boolean
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
        holder.txTarefa.setOnLongClickListener {
            dialog(it.context, holder)
            true
        }

        holder.txDesc.setOnLongClickListener {
            dialog(it.context, holder)
            true
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class HolderData(v: View) : RecyclerView.ViewHolder(v) {
        val txTarefa = v.findViewById<TextView>(R.id.rv_tarefa)
        val txDesc = v.findViewById<TextView>(R.id.rv_desc)
    }

    fun dialog(context: Context, holder: HolderData) {
        val dialog = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add, null)
        dialog.setView(view)
        val tarefa = view.findViewById<EditText>(R.id.edt_tarefa_todo)
        val desc = view.findViewById<EditText>(R.id.edt_desc_todo)
        dialog.setPositiveButton("Alterar") { _: DialogInterface, _: Int ->
            holder.txTarefa.text = tarefa.text
            holder.txDesc.text = desc.text
        }
        dialog.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, i: Int ->
            Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

}