package com.example.adrile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ProductListAdapter(context: Context, private val products: List<Product>) : BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return products.size
    }

    override fun getItem(position: Int): Any {
        return products[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.product_list_item, parent, false)
            holder = ViewHolder()
            holder.productName = view.findViewById(R.id.productNameTextView)
            // Agrega más vistas según la estructura de tu elemento de lista
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val product = products[position]
        holder.productName.text = product.nombre
        // Configura más vistas según la estructura de tu elemento de lista

        return view
    }

    private class ViewHolder {
        lateinit var productName: TextView
        // Agrega más vistas según la estructura de tu elemento de lista
    }
}
