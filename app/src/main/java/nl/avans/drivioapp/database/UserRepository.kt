package nl.avans.drivioapp.database

import androidx.lifecycle.LiveData
import nl.avans.drivioapp.model.User

class UserRepository(private val userDao: UserDao) {

    val getAllUsers: LiveData<List<User>> = userDao.getAllUsers()
//    val getUsers: List<User> = userDao.getUsers()
    val readUserCredentialData: LiveData<User>? = null

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    fun getLoginDetails(email: String, password: String): LiveData<User> {
        return userDao.getUserCredentialData(email, password)
    }

    fun getUserCredential(email: String, password: String): User {
        return userDao.getUserCredential(email, password)
    }
}


