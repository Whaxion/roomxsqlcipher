package be.whaxion.roomxsqlcipher.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import be.whaxion.roomxsqlcipher.database.entities.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM  todos")
    fun getAll() : LiveData<List<Todo>>

    @Query("SELECT * FROM todos WHERE title LIKE :title")
    fun findByTitle(title: String) : LiveData<Todo>

    @Insert
    fun insertAll(vararg todos: Todo)

    @Delete
    fun delete(todo: Todo)

    @Update
    fun updateTodo(vararg todos: Todo)
}