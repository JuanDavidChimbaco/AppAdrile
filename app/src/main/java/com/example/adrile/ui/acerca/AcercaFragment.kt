package com.example.adrile.ui.acerca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adrile.R

/**
 * A simple [Fragment] subclass.
 * Use the [AcercaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AcercaFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acerca, container, false)
    }

}