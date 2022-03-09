package com.cubidevs.bookproject.ui.register

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cubidevs.bookproject.databinding.ActivityRegisterBinding
import com.cubidevs.bookproject.server.Role
import com.cubidevs.bookproject.server.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        registerViewModel.errorMsgDone.observe(this) { result ->
            onErrorMsgDoneSubscribe(result)
        }

        registerViewModel.registerSucessDone.observe(this){ result->
            onRegisterSucessDoneSubscribe(result)
        }

        with(registerBinding) {
            registerButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val repPassword = repPasswordEditText.text.toString()

                if (password == repPassword) {
                    /*    val intent= Intent(this@RegisterActivity, LoginActivity::class.java)
                        intent.putExtra("email", email)
                        intent.putExtra("password", password)
                        startActivity(intent)*/

                    registerViewModel.registerUser(email, password)
                } else
                    Toast.makeText(
                        applicationContext,
                        "Las contraseñas deben ser iguales",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    private fun onRegisterSucessDoneSubscribe(uid: String?) {
        uid?.let { Log.d("uid", it) }
        registerViewModel.createUser(uid, registerBinding.emailEditText.text.toString())
    }

    private fun onErrorMsgDoneSubscribe(result: String?) {
        Toast.makeText(baseContext, result, Toast.LENGTH_SHORT).show()
    }

    private fun createUser(uid: String?, email: String) {
      //  registerViewModel.createUser(uid)
        val db = Firebase.firestore
        val user = User(uid = uid, email = email, role = Role.VENDEDOR)
        uid?.let { uid ->
            db.collection("users").document(uid).set(user)
                .addOnSuccessListener {
                    Toast.makeText(baseContext, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
