package nl.avans.drivioapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import nl.avans.drivioapp.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_credentials ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if given user already exists within the DB, set strategy
    suspend fun addUser(user: User)
}
