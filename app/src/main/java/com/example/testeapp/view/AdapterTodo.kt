package com.example.testeapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testeapp.R
import com.example.testeapp.model.TodosRoom


class AdapterTodo(var longClickListener: onLongClickListener, var clickListener: onClickListener) :
    RecyclerView.Adapter<AdapterTodo.HolderData>() {

    var dataList: List<TodosRoom> = ArrayList()

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
        holder.txDesc.text = data.desc

        // img_del visibility, se isDel for verdadeiro fica visívivel...
        holder.del.visibility = if (data.isDel) View.VISIBLE else View.INVISIBLE
        holder.check.visibility = if (data.isDone) View.VISIBLE else View.INVISIBLE

        //chamando funções de click
        holder.initializeLong(dataList[position], longClickListener)
        holder.initializeClick(dataList[position], clickListener)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class HolderData(v: View) : RecyclerView.ViewHolder(v) {
        val txTarefa = v.findViewById<TextView>(R.id.rv_tarefa)
        val txDesc = v.findViewById<TextView>(R.id.rv_desc)
        val check = v.findViewById<ImageView>(R.id.img_check)
        val del = v.findViewById<ImageView>(R.id.img_del)

        fun initializeLong(item: TodosRoom, action: onLongClickListener) {

            itemView.setOnLongClickListener {
                action.onLongItemClick(item, adapterPosition)
                true
            }

        }

        fun initializeClick(item: TodosRoom, action: onClickListener) {

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    //setar lista no adapter
    fun setTodos(todos: List<TodosRoom?>) {
        dataList = todos as List<TodosRoom>
        notifyDataSetChanged()
    }

    interface onLongClickListener {
        fun onLongItemClick(item: TodosRoom, position: Int) {

        }
    }

    interface onClickListener {
        fun onItemClick(item: TodosRoom, position: Int) {

        }
    }


}