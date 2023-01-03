package nl.avans.drivioapp.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import nl.avans.drivioapp.view.ProfileFragmentDirections

class ProfileViewModel : ViewModel() {

    fun setLoginButton(view: View) {
        val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
        Navigation.findNavController(view).navigate(action)
    }

    fun setRegisterButton(view: View) {
        val action = ProfileFragmentDirections.actionProfileFragmentToRegisterFragment()
        Navigation.findNavController(view).navigate(action)
    }

    fun setUserListButton(view: View) {
        val action = ProfileFragmentDirections.actionProfileFragmentToUserListFragment()
        Navigation.findNavController(view).navigate(action)
    }
}


