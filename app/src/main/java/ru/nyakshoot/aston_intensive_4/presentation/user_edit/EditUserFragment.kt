package ru.nyakshoot.aston_intensive_4.presentation.user_edit

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.nyakshoot.aston_intensive_4.R
import ru.nyakshoot.aston_intensive_4.data.User

class EditUserFragment : Fragment() {
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("user", User::class.java)
        } else {
            arguments?.getParcelable("user")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_edit_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstNameEdit = view.findViewById<EditText>(R.id.firstNameEdit)
        val lastNameEdit = view.findViewById<EditText>(R.id.lastNameEdit)
        val phoneEdit = view.findViewById<EditText>(R.id.phoneEdit)
        val saveButton = view.findViewById<Button>(R.id.saveButton)

        user?.let { user ->
            firstNameEdit.setText(user.firstName)
            lastNameEdit.setText(user.lastName)
            phoneEdit.setText(user.phoneNumber)
        }

        saveButton.setOnClickListener {
            user?.let { currentUser ->
                val updatedUser = currentUser.copy(
                    firstName = firstNameEdit.text.toString(),
                    lastName = lastNameEdit.text.toString(),
                    phoneNumber = phoneEdit.text.toString()
                )

                parentFragmentManager.setFragmentResult(
                    "userUpdate",
                    bundleOf("user" to updatedUser)
                )
                parentFragmentManager.popBackStack()
            }
        }
    }

    companion object {
        fun newInstance(user: User) = EditUserFragment().apply {
            arguments = Bundle().apply {
                putParcelable("user", user)
            }
        }
    }
}