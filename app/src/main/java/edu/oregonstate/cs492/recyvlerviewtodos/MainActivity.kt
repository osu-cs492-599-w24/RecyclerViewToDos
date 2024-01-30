package edu.oregonstate.cs492.recyvlerviewtodos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoListTV: TextView = findViewById(R.id.tv_todo_list)
        val todoEntryET: EditText = findViewById(R.id.et_todo_entry)
        val addTodoBtn: Button = findViewById(R.id.btn_add_todo)

        val todoList = mutableListOf<String>()

        addTodoBtn.setOnClickListener {
            val newTodo = todoEntryET.text.toString()
            if (!TextUtils.isEmpty(newTodo)) {
                todoList.add(0, newTodo)
                todoListTV.text = todoList.joinToString(separator = "\n\n☐  ", prefix = "☐  ")
                todoEntryET.setText("")
            }
        }
    }
}