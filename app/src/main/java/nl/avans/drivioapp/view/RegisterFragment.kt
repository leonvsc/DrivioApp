package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentRegisterBinding
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.viewModel.UserViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.registerUserBtn.setOnClickListener {
            insertUserToDatabase()
        }

        binding.resetRegister.setOnClickListener {
            reset()
        }

        return binding.root
    }

    private fun insertUserToDatabase() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        // if email field and password field not empty..
        if (validateInput(email, password)) {
            // make new user object
            val user = User(0, email, password)
            // add user to the database
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "New User Successfully added", Toast.LENGTH_SHORT)
                .show()
            // navigate to user list fragment
            findNavController().navigate(R.id.action_registerFragment_to_userListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        return (email.isNotEmpty() && password.isNotEmpty())
    }

    private fun reset() {
        binding.editEmail.setText("")
        binding.editPassword.setText("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
