package com.cubidevs.bookproject.ui.register

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cubidevs.bookproject.databinding.ActivityRegisterBinding
import com.cubidevs.bookproject.server.Role
import com.cubidevs.bookproject.server.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
        auth = Firebase.auth

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

                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Register", "createUserWithEmail:success")
                                createUser(auth.currentUser?.uid, email)
                                onBackPressed()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Register", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext, task.exception?.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else
                    Toast.makeText(
                        applicationContext,
                        "Las contraseñas deben ser iguales",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    private fun createUser(uid: String?, email: String) {
        val db = Firebase.firestore
        val user = User(uid = uid, email = email, role = Role.VENDEDOR)
        uid?.let { uid->
            db.collection("users").document(uid).set(user)
                .addOnSuccessListener {
                    Toast.makeText(baseContext,"Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
                }
        }
    }

}
