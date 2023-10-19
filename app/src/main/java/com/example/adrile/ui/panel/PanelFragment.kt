package com.example.adrile.ui.panel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class PanelFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflar el diseño del fragmento
        val view = inflater.inflate(com.example.adrile.R.layout.fragment_panel, container, false)

        // Obtener una referencia al TextView
        val linkTextView = view.findViewById<TextView>(com.example.adrile.R.id.linkTextView)

        // Establecer un OnClickListener para abrir el enlace
        linkTextView.setOnClickListener {
            // URL que deseas abrir en el navegador
            val url = "https://www.flaticon.es/iconos-gratis/inventario"

            // Crear un intent para abrir el navegador web
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            // Iniciar la actividad del navegador
            startActivity(intent)
        }

        return view
    }

}