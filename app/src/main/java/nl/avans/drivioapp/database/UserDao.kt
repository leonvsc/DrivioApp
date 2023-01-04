package nl.avans.drivioapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

import nl.avans.drivioapp.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // if given user already exists within the DB, set strategy
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_credentials")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_credentials ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>
}

