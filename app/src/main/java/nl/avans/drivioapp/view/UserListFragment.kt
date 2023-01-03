package nl.avans.drivioapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import nl.avans.drivioapp.databinding.FragmentUserListBinding
import nl.avans.drivioapp.view.adapter.UserListAdapter
import nl.avans.drivioapp.viewModel.UserViewModel

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        val adapter = UserListAdapter()
        val recyclerView = binding.userListRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getAllUsers.observe(viewLifecycleOwner, Observer { userList ->   // userList was it, heb get gewoon veranderd, vergeet niet dit is eigenlijk de query die we hadden binnen de userDao
            adapter.setData(userList)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val action = UserListFragmentDirections.actionUserListFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


