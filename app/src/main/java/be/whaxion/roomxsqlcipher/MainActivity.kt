package be.whaxion.roomxsqlcipher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import be.whaxion.roomxsqlcipher.adapters.TodoAdapter
import be.whaxion.roomxsqlcipher.database.entities.Todo
import be.whaxion.roomxsqlcipher.database.repositories.TodoRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoRepo = TodoRepository("test123", this)

        todo_recycler_view.layoutManager = LinearLayoutManager(this)
        todo_recycler_view.setHasFixedSize(true)

        val adapter = TodoAdapter()
        todo_recycler_view.adapter = adapter

        todoRepo.allTodos.observe(this, Observer<List<Todo>>() {
            adapter.setTodos(it)
        })

        Handler().postDelayed({
            todoRepo.insert(Todo(title = "Lorem ipsum", content = "Lorem ipsum dolor sit amet."))
        }, 5000)
    }
}