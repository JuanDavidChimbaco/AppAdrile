package com.example.adrile.ui.salidas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.adrile.ProductListAdapter
import com.example.adrile.R
import com.example.adrile.databinding.FragmentSalidasBinding
import org.json.JSONArray

class SalidasFragment : Fragment() {

    private lateinit var binding: FragmentSalidasBinding
    private lateinit var productListView: ListView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSalidasBinding.inflate(inflater, container, false)
        val root = binding.root

        // Obtener una referencia al ListView
        productListView = root.findViewById(R.id.productListView)

        // Llamar a una función para cargar y mostrar los productos desde la API
        cargarProductosDesdeAPI()

        return root
    }

    private fun cargarProductosDesdeAPI() {
        // Aquí debes usar Volley para realizar una solicitud a la API y obtener la lista de productos.
        // Puedes usar JsonObjectRequest o JsonArrayRequest según la estructura de la respuesta de la API.

        // Ejemplo de solicitud JsonArrayRequest:
        val url = "URL_DE_TU_API_AQUI"
        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                // La solicitud fue exitosa, procesa la respuesta y muestra los datos en el ListView
                val products = parseProductsFromResponse(response)
                val adapter = ProductListAdapter(requireContext(), products)
                productListView.adapter = adapter
            },
            { error ->
                // La solicitud falló, maneja el error apropiadamente.
            })

        // Agregar la solicitud a la cola de Volley (debes tener una instancia de RequestQueue)
        Volley.newRequestQueue(requireContext()).add(request)
    }

    private fun parseProductsFromResponse(response: JSONArray): List<Product> {
        // Aquí debes analizar la respuesta JSON y crear una lista de objetos Product.
        // Recorre el JSONArray y crea objetos Product con los datos obtenidos.

        val products = mutableListOf<Product>()
        for (i in 0 until response.length()) {
            val productData = response.getJSONObject(i)
            val product = Product(
                productData.getString("id"),
                productData.getString("nombre"),
                // Agrega más campos según la estructura de tu respuesta JSON
            )
            products.add(product)
        }
        return products
    }

}