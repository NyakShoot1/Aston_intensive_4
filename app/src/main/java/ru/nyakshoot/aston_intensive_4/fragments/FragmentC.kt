package ru.nyakshoot.aston_intensive_4.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.nyakshoot.aston_intensive_4.R

class FragmentC : Fragment(R.layout.fragment_c) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val message = arguments?.getString("message") ?: ""
        view.findViewById<TextView>(R.id.tvMessage).text = message

        view.findViewById<Button>(R.id.btnToD).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentD())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnToA).setOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}