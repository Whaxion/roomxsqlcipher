package be.whaxion.roomxsqlcipher.database.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import be.whaxion.roomxsqlcipher.EncryptedDatabase
import be.whaxion.roomxsqlcipher.database.daos.TodoDao
import be.whaxion.roomxsqlcipher.database.entities.Todo

class TodoRepository(password: String, context: Context) {
    private val todoDao : TodoDao
    val allTodos :  LiveData<List<Todo>>

    init {
        val database = EncryptedDatabase.getInstance(password.toCharArray(), context)
        todoDao = database.todoDao()
        allTodos = todoDao.getAll()
    }

    fun insert(todo : Todo){
        insertAll(todo)
    }

    fun insertAll(vararg todos: Todo){
        InsertTodoAsyncTask(todoDao).execute(*todos)
    }

    private class InsertTodoAsyncTask(private val todoDao: TodoDao) : AsyncTask<Todo, Void, Void>() {
        override fun doInBackground(vararg todos: Todo): Void? {
            todoDao.insertAll(*todos)

            return null
        }

    }
}