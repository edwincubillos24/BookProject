package com.cubidevs.bookproject.server.serverrepository

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UsersRepository {

    private val auth: FirebaseAuth = Firebase.auth

    suspend fun registerUser(email: String, password: String): String? {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user?.uid
        } catch (e: FirebaseAuthException) {
            Log.d("errorRegister", e.localizedMessage)
            e.localizedMessage
        } catch (e: FirebaseNetworkException){
            e.localizedMessage
        }
    }
}
