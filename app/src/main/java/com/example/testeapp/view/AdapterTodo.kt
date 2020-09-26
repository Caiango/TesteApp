package com.example.testeapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testeapp.R
import com.example.testeapp.model.TodosRoom


class AdapterTodo(var clickListener: onLongClickListener) :
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

        //click longo responsável por alterar o valor da view selecionada
//        holder.lay.setOnLongClickListener {
//            dialogUpdate(it.context, holder)
//            true
//        }

        var done = data.isDone
        //click responsável por setta visibilidade ao check da task
        holder.lay.setOnClickListener {
            //se del for falso
            if (data.isDel == false) {
                //done vai receber o oposto
                done = !done
                //fazer update para isDone = false

                holder.check.visibility = if (done) View.VISIBLE else View.INVISIBLE
            } else {
                Toast.makeText(
                    it.context,
                    "Remover elemento na posição" + position,
                    Toast.LENGTH_LONG
                ).show()
            }

        }

        // del visibility, se for verdadeiro fica visívivel...
        holder.del.visibility = if (data.isDel) View.VISIBLE else View.INVISIBLE

        holder.initialize(dataList[position], clickListener)


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

        fun initialize(item: TodosRoom, action: onLongClickListener) {

            itemView.setOnLongClickListener {
                action.onLongItemClick(item, adapterPosition)
                true
            }
        }
    }

    fun setTodos(todos: List<TodosRoom?>) {
        dataList = todos as List<TodosRoom>
        notifyDataSetChanged()
    }

    interface onLongClickListener {
        fun onLongItemClick(item: TodosRoom, position: Int) {

        }
    }

}