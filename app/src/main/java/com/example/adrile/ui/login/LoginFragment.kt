package com.example.adrile.ui.login

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import android.widget.ToggleButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.adrile.MainActivity
import com.example.adrile.PanelActivity
import com.example.adrile.R
import org.json.JSONException
import org.json.JSONObject

class LoginFragment : Fragment() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnVisible: ToggleButton
    private lateinit var btnLogin: Button
    private lateinit var cRemember: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        txtUser = view.findViewById(R.id.username)
        txtPassword = view.findViewById(R.id.password)
        btnLogin = view.findViewById(R.id.login)
        btnVisible = view.findViewById(R.id.btnVisible)
        cRemember = view.findViewById(R.id.checkBoxRememberMe)

        btnVisible.setOnClickListener {
            if (btnVisible.isChecked) {
                txtPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                txtPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            txtPassword.setSelection(txtPassword.text.length)
        }


        btnLogin.setOnClickListener {
            //val usuario = txtUser.text.toString()
            //val contraseña = txtPassword.text.toString()

            //login(usuario, contraseña)
            val intent = Intent(requireContext(), PanelActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }

    private fun login(usuario: String, contraseña: String) {
        val url = "https://adrile.pythonanywhere.com/api/login/"

        val jsonObject = JSONObject()
        jsonObject.put("username", usuario)
        jsonObject.put("password", contraseña)

        val request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
            { response ->
                try {
                    val token = response.getString("token")
                    val mensaje = response.getString("message")
                    if (token.isNotEmpty()) {
                        val intent = Intent(requireContext(), PanelActivity::class.java)
                        intent.putExtra("token", token)
                        val sharedPreferences =
                            requireActivity().getSharedPreferences("tokenSesion", Context.MODE_PRIVATE)
                        val rememberMeChecked = cRemember.isChecked
                        val editor = sharedPreferences.edit()
                        editor.putString("token", token)
                        editor.putBoolean("remember_me", rememberMeChecked)
                        editor.apply()
                        startActivity(intent)
                        requireActivity().finish()
                    }
                    Toast.makeText(requireContext(), "Mensaje: $mensaje", Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Error de inicio de sesión", Toast.LENGTH_SHORT).show()
                }
            },
            { _ ->
                Log.e("MainActivity", "Error Inicio de Sesión")
                Toast.makeText(requireContext(), "Error inicio de Sesion", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(requireContext()).add(request)
    }
}