package nl.avans.drivioapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.avans.drivioapp.database.UserDatabase
import nl.avans.drivioapp.database.UserRepository
import nl.avans.drivioapp.model.User

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val getAllUsers: LiveData<List<User>>
    private var readUserCredentialData: LiveData<User>? = null
    private val repository: UserRepository


    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllUsers = repository.getAllUsers
        readUserCredentialData = repository.readUserCredentialData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }

    fun getLoginDetails(email: String, password: String): LiveData<User> {
        return repository.getLoginDetails(email, password)
    }
}









