package com.example.adrile.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.adrile.ImageAdapter
import com.example.adrile.R

class HomeFragment : Fragment() {

    private lateinit var imageAdapter: ImageAdapter
    private val imageList: MutableList<Int> = mutableListOf()
    private val mainHandler = Handler(Looper.getMainLooper())
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            val viewPager = view?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.let {
                val currentPos = it.currentItem
                val nextPos = (currentPos + 1) % imageList.size
                it.setCurrentItem(nextPos, true)
            }
            // Programa la siguiente transición
            mainHandler.postDelayed(this, 3000) // Cambia de imagen cada 3 segundos
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)

        // Agrega tus imágenes a la lista (duplica la lista para lograr el carrusel infinito)
        imageList.addAll(
            listOf(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3
            )
        )

        // Duplica la lista para que sea infinita
        imageList.addAll(imageList)

        imageAdapter = ImageAdapter(imageList)
        viewPager.adapter = imageAdapter

        // Configura un callback para detectar cuando se llega al final del carrusel
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // Si se llega al final, cambia a la posición correspondiente al principio
                if (position == imageList.size - 1) {
                    viewPager.setCurrentItem(imageList.size / 1, false)
                }
            }
        })

        // Muestra el elemento en el medio de la lista (para iniciar en el medio)
        viewPager.setCurrentItem(imageList.size / 1, false)

        // Inicia el desplazamiento automático
        mainHandler.postDelayed(autoScrollRunnable, 3000) // Inicia el desplazamiento después de 3 segundos

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Detiene el desplazamiento automático al destruir la vista
        mainHandler.removeCallbacks(autoScrollRunnable)
    }
}
