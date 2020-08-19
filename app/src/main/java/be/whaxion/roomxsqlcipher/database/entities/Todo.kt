package be.whaxion.roomxsqlcipher.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,

    @ColumnInfo(name="title") var title : String,
    @ColumnInfo(name = "content") var content : String
)