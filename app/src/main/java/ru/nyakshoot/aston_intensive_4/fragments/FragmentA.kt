package ru.nyakshoot.aston_intensive_4.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.nyakshoot.aston_intensive_4.R

class FragmentA : Fragment(R.layout.fragment_a) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnToB).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentB())
                .addToBackStack(null)
                .commit()
        }
    }
}