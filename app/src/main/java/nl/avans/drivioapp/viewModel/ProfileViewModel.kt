package nl.avans.drivioapp.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import nl.avans.drivioapp.view.ProfileFragmentDirections

class ProfileViewModel : ViewModel() {

    fun setLoginButton(view: View) {
        val action = ProfileFragmentDirections.actionProfileFragment2ToLoginFragment()
        Navigation.findNavController(view).navigate(action)
    }

    fun setRegisterButton(view: View) {
        val action = ProfileFragmentDirections.actionProfileFragment2ToRegisterFragment()
        Navigation.findNavController(view).navigate(action)
    }
}



