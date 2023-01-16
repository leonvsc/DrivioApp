package nl.avans.drivioapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import nl.avans.drivioapp.R
import nl.avans.drivioapp.databinding.FragmentLoginBinding
import nl.avans.drivioapp.viewModel.UserViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.login.setOnClickListener {
            val email = binding.emailLogin.text.toString()
            val password = binding.passwordLogin.text.toString()

            userViewModel.getLoginDetails(email, password)
                .observe(viewLifecycleOwner, Observer { user ->
                    if (user == null) {
                        Toast.makeText(
                            requireContext(),
                            "User credentials incorrect!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Welcome ${user.email}",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_loginFragment_to_loggedInFragment)
                        parentFragmentManager.commit {
                            replace<LoggedInFragment>(R.id.fragmentContainerView)
                            setReorderingAllowed(true)
                            addToBackStack(null)
                        }
                    }
                })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resetLogin.setOnClickListener {
            reset()
        }

    }

    private fun reset() {
        binding.emailLogin.setText("")
        binding.passwordLogin.setText("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


