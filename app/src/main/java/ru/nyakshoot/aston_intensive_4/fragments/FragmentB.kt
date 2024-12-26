package ru.nyakshoot.aston_intensive_4.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.nyakshoot.aston_intensive_4.R

class FragmentB : Fragment(R.layout.fragment_b) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnToC).setOnClickListener {
            val fragmentC = FragmentC().apply {
                arguments = Bundle().apply {
                    putString("message", "Hello Fragment C")
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragmentC)
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnBack).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}