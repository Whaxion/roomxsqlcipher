package be.whaxion.roomxsqlcipher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.whaxion.roomxsqlcipher.R
import be.whaxion.roomxsqlcipher.database.entities.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoHolder>() {
    private var todos : List<Todo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return TodoHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val currentTodo = todos[position]
        holder.titleView.text = currentTodo.title
        holder.contentView.text = currentTodo.content
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun setTodos(new_todos : List<Todo>){
        todos = new_todos
        notifyDataSetChanged()
    }

    class TodoHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val titleView = itemView.findViewById<TextView>(R.id.todo_title)
        val contentView = itemView.findViewById<TextView>(R.id.todo_content)
    }
}