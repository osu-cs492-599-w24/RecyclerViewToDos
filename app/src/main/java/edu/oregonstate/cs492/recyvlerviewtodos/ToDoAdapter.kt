package edu.oregonstate.cs492.recyvlerviewtodos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    val toDos: MutableList<ToDo> = mutableListOf()

    fun addToDo(toDo: ToDo, position: Int = 0) {
        toDos.add(position, toDo)
        notifyItemInserted(position)
    }

    fun deleteToDo(position: Int): ToDo {
        val toDo = toDos.removeAt(position)
        notifyItemRemoved(position)
        return toDo
    }

    override fun getItemCount() = toDos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_item, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(toDos[position])
    }

    class ToDoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val checkBox: CheckBox = view.findViewById(R.id.todo_checkbox)
        private val todoTV: TextView = view.findViewById(R.id.tv_todo_text)
        private var currentToDo: ToDo? = null

        init {
            checkBox.setOnCheckedChangeListener {
                _, isChecked -> currentToDo?.completed = isChecked
            }
        }

        fun bind(toDo: ToDo) {
            currentToDo = toDo
            checkBox.isChecked = toDo.completed
            todoTV.text = toDo.text
        }
    }
}