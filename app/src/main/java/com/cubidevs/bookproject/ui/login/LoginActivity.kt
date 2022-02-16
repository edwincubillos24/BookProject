package com.cubidevs.bookproject.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cubidevs.bookproject.ui.main.MainActivity
import com.cubidevs.bookproject.ui.register.RegisterActivity
import com.cubidevs.bookproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        auth = Firebase.auth

        var emailReceived: String? = ""
        var passwordReceived: String? = ""

       /* val credentials = intent.extras
        if (credentials != null) {
            emailReceived = credentials.getString("email")
            passwordReceived = credentials.getString("password")
        }*/

        loginBinding.registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        with(loginBinding) {
            signInButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}