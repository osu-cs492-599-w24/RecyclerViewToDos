package edu.oregonstate.cs492.recyvlerviewtodos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coordinator: CoordinatorLayout = findViewById(R.id.coordinator)
        val todoEntryET: EditText = findViewById(R.id.et_todo_entry)
        val addTodoBtn: Button = findViewById(R.id.btn_add_todo)
        val todoListRV: RecyclerView = findViewById(R.id.rv_todo_list)

        todoListRV.layoutManager = LinearLayoutManager(this)
        todoListRV.setHasFixedSize(true)

        val adapter = ToDoAdapter()
        todoListRV.adapter = adapter

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val deletedToDo = adapter.deleteToDo(position)
                val snackbar = Snackbar.make(
                    coordinator,
                    "Deleted: ${deletedToDo.text}",
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction("UNDO") {
                    adapter.addToDo(deletedToDo, position)
                }
                snackbar.show()
            }
        }

        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(todoListRV)

        addTodoBtn.setOnClickListener {
            val newTodo = todoEntryET.text.toString()
            if (!TextUtils.isEmpty(newTodo)) {
                todoListRV.scrollToPosition(0)
                adapter.addToDo(ToDo(newTodo))
                todoEntryET.setText("")
            }
        }
    }
}