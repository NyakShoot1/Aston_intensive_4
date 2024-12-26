package ru.nyakshoot.aston_intensive_4.presentation.user_list

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.nyakshoot.aston_intensive_4.R
import ru.nyakshoot.aston_intensive_4.data.User
import ru.nyakshoot.aston_intensive_4.presentation.user_edit.EditUserFragment

class UserListFragment : Fragment() {
    private lateinit var viewModel: UserListViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[UserListViewModel::class.java]

        adapter = UserAdapter { user ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, EditUserFragment.newInstance(user))
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@UserListFragment.adapter
        }

        viewModel.users.observe(viewLifecycleOwner) { users ->
            adapter.submitList(users)
        }

        parentFragmentManager.setFragmentResultListener(
            "userUpdate",
            viewLifecycleOwner
        ) { _, bundle ->
            val updatedUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("user", User::class.java)
            } else {
                bundle.getParcelable("user")
            }
            updatedUser?.let {
                viewModel.updateUser(it)
            }
        }
    }
}