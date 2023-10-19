package com.example.adrile

import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.adrile.ui.dashboard.DashboardFragment
import com.example.adrile.ui.home.HomeFragment
import com.example.adrile.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val textSwitcher: TextSwitcher by lazy {
        findViewById(R.id.textSwitcher)
    }

    private val texts = listOf("Adrile","Boutique")  // Agrega tus textos aquí
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encuentra elementos de la interfaz de usuario
        val header = findViewById<LinearLayout>(R.id.header)
        val mainContent = findViewById<FrameLayout>(R.id.mainContent)
        val footer = findViewById<LinearLayout>(R.id.footer)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        textSwitcher.setFactory {
            val textView = TextView(this)
            textView.textSize = 32f
            textView.maxLines = 1
            textView.ellipsize = android.text.TextUtils.TruncateAt.MARQUEE
            textView.marqueeRepeatLimit = -1  // Valor infinito
            textView.isFocusable = true
            textView.isFocusableInTouchMode = true
            textView.gravity = android.view.Gravity.CENTER
            textView.setTextColor(ContextCompat.getColor(this, R.color.white))
            textView
        }

        // Carga las animaciones desde los recursos
        val inAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        textSwitcher.inAnimation = inAnimation

        // Este es el ajuste para la animación de salida
        val outAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_out_left)
        textSwitcher.outAnimation = outAnimation

        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                textSwitcher.setText(texts[currentIndex])
                currentIndex = (currentIndex + 1) % texts.size
                handler.postDelayed(this, 3000)  // Cambia de texto cada 3 segundos (ajusta según tus necesidades)
            }
        }
        handler.postDelayed(runnable, 0)  // Inicia la marquesina

        // Configura eventos o realiza acciones según sea necesario
        header.setOnClickListener {
            // Hacer algo cuando se hace clic en el encabezado
        }

        mainContent.setOnClickListener {
            // Hacer algo cuando se hace clic en el encabezado
        }

        footer.setOnClickListener {
            // Hacer algo cuando se hace clic en el encabezado
        }
        // Agrega más lógica según tus necesidades
        // Mostrar el fragmento de inicio por defecto
        val initialFragment = HomeFragment() // Reemplaza con el nombre de tu fragmento

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContent, initialFragment)
            .commit()


        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Mostrar la sección de inicio
                    // Puedes reemplazar esto con la lógica de tu aplicación
                    // por ejemplo, cambiar el contenido del FrameLayout (mainContent)

                    val fragment = HomeFragment() // Reemplaza con el nombre de tu fragmento

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContent, fragment)
                        .commit()

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    // Mostrar la sección de dashboard
                    val fragment = DashboardFragment() // Reemplaza con el nombre de tu fragmento

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContent, fragment)
                        .commit()

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    // Mostrar la sección de notificaciones

                    val fragment = NotificationsFragment() // Reemplaza con el nombre de tu fragmento

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContent, fragment)
                        .commit()
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }
}