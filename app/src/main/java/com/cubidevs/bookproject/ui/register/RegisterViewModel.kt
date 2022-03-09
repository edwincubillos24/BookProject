package com.cubidevs.bookproject.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cubidevs.bookproject.server.serverrepository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val usersRepository = UsersRepository()

    private val errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsgDone: LiveData<String?> = errorMsg

    private val registerSucess: MutableLiveData<String?> = MutableLiveData()
    val registerSucessDone: LiveData<String?> = registerSucess

    fun registerUser(email: String, password: String) {

        GlobalScope.launch(Dispatchers.IO) {
            when (val result = usersRepository.registerUser(email, password)) {
                "The email address is already in use by another account." -> errorMsg.postValue("Ya existe una cuenta con ese correo electrónico")
                "The given password is invalid. [ Password should be at least 6 characters ]" -> errorMsg.postValue("La contraseña debe tener mínimo 6 digitos")
                "The email address is badly formatted." -> errorMsg.postValue("El formato de email es incorrecto")
                "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> errorMsg.postValue("No tiene conexión a internet")
                else -> registerSucess.postValue(result)
            }
        }
    }

    fun createUser(uid: String?, toString: String) {


    }
}
