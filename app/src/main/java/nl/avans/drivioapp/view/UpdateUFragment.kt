package nl.avans.drivioapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentUpdateUBinding
import nl.avans.drivioapp.model.User
import nl.avans.drivioapp.viewModel.UserViewModel

class UpdateUFragment : Fragment() {

    private val args by navArgs<UpdateUFragmentArgs>()
    private lateinit var userViewModel: UserViewModel

    private var _binding: FragmentUpdateUBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateUBinding.inflate(inflater, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // args to get the current user
        binding.updateEmail.setText(args.currentUser.email)
        binding.updatePassword.setText(args.currentUser.password)

        binding.updateUserBtn.setOnClickListener {
            updateUser()
        }

        return binding.root
    }

    private fun updateUser() {
        val email = binding.updateEmail.text.toString()
        val password = binding.updatePassword.text.toString()

        if (validateInput(email, password)) {
            // make current user object
            val updatedUser = User(args.currentUser.id, email, password)
            // update current user object
            userViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "User Updated Successfully", Toast.LENGTH_SHORT).show()
            // navigate back
            findNavController().navigate(R.id.action_updateUFragment_to_userListFragment)
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