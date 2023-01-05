package nl.avans.drivioapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.registerUserBtn.setOnClickListener {
            insertUserToDatabase()
        }

        return binding.root
    }

    private fun insertUserToDatabase() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        if (validateInput(email, password)) {
            val user = User(0, email, password)
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "New User Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerFragment_to_userListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        return (email.isNotEmpty() && password.isNotEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}