package nl.avans.drivioapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import nl.avans.drivioapp.R
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
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        val adapter = UserListAdapter()
        val recyclerView = binding.userListRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Instantiate view model
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getAllUsers.observe(viewLifecycleOwner, Observer { userList ->
            adapter.setData(userList)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // No longer in use
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_delete, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.delete_icon -> {
                        deleteAllUsers()
                        return true
                    }
                }
                return false
            }

        }, viewLifecycleOwner)

        binding.eraseListBtn.setOnClickListener {
            deleteAllUsers()
        }

        binding.floatingActionButton.setOnClickListener {
            val action = UserListFragmentDirections.actionUserListFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.floatingActionButton2.setOnClickListener {
            val action = UserListFragmentDirections.actionUserListFragmentToProfileFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun deleteAllUsers() {
        val alert = AlertDialog.Builder(requireContext())
        alert.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteAllUsers()
            Toast.makeText(
                requireContext(),
                "All Users Successful Removed!",
                Toast.LENGTH_SHORT
            ).show()
        }
        alert.setNegativeButton("No") { _, _ -> }
        alert.setTitle("Delete all users?")
        alert.setMessage("Are you sure you want to delete all users from the list?")
        alert.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


