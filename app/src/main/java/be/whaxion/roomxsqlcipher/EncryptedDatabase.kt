package be.whaxion.roomxsqlcipher

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.whaxion.roomxsqlcipher.database.daos.TodoDao
import be.whaxion.roomxsqlcipher.database.entities.Todo
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [Todo::class], version = 20200819)
abstract class EncryptedDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao

    companion object {
        private const val DB_NAME = "roomXSqlCipher.db"

        fun getInstance(password: CharArray, context: Context):
            EncryptedDatabase = buildDatabase(password, context)

        private fun buildDatabase(password: CharArray, context: Context) : EncryptedDatabase {
            val supportFactory = SupportFactory(SQLiteDatabase.getBytes(password))
            return Room
                .databaseBuilder(context, EncryptedDatabase::class.java, DB_NAME)
                .openHelperFactory(supportFactory)
                .build()
        }
    }
}