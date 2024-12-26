package ru.nyakshoot.aston_intensive_4.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.nyakshoot.aston_intensive_4.R

class FragmentD : Fragment(R.layout.fragment_d) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnToB).setOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentB())
                .addToBackStack(null)
                .commit()
        }
    }
}