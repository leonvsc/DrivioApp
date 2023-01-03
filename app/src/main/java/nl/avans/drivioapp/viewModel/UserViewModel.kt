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

// Let op! deze viewmodel is anders dan een gewone viewmodel want het bevat een application reference
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val getAllUsers: LiveData<List<User>>
    private val repository: UserRepository

    // always first executed when UserViewModel is called!
    // binnen de viewmodel initialiseer je de database en de repository
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllUsers = repository.getAllUsers
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}
