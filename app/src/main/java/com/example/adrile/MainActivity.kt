package com.example.adrile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.adrile.ui.dashboard.DashboardFragment
import com.example.adrile.ui.home.HomeFragment
import com.example.adrile.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encuentra elementos de la interfaz de usuario
        val header = findViewById<LinearLayout>(R.id.header)
        val mainContent = findViewById<FrameLayout>(R.id.mainContent)
        val footer = findViewById<LinearLayout>(R.id.footer)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        // Configura eventos o realiza acciones según sea necesario
        header.setOnClickListener {
            // Hacer algo cuando se hace clic en el encabezado
        }

        // Agrega más lógica según tus necesidades


        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Mostrar la sección de inicio
                    // Puedes reemplazar esto con la lógica de tu aplicación
                    // por ejemplo, cambiar el contenido del FrameLayout (mainContent)

                    val fragment = HomeFragment() // Reemplaza con el nombre de tu fragmento

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContent, fragment)
                        .commit()

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    // Mostrar la sección de dashboard
                    val fragment = DashboardFragment() // Reemplaza con el nombre de tu fragmento

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContent, fragment)
                        .commit()

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    // Mostrar la sección de notificaciones

                    val fragment = NotificationsFragment() // Reemplaza con el nombre de tu fragmento

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContent, fragment)
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }
}