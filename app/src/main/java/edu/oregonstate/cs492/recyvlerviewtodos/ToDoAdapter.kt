package edu.oregonstate.cs492.recyvlerviewtodos

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter : RecyclerView.Adapter<> {
    val toDos: MutableList<ToDo> = mutableListOf()

    fun addToDo(toDo: ToDo) {
        toDos.add(0, toDo)
        notifyItemInserted(0)
    }

    override fun getItemCount() = toDos.size

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