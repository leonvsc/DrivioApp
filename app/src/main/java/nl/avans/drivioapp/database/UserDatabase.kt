package nl.avans.drivioapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nl.avans.drivioapp.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao


    // singleton pattern. Only one instance of the companion object should exist
    companion object {
        // @Volatile = variable should not be modified by different threads
        @Volatile private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            // if database already exist.. return existing DB.
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            // else build new Database instance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
