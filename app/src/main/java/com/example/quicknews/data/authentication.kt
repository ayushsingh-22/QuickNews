package com.example.quicknews.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class Authmodel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance();

    private val _authstate = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authstate

    init {
        checkAuthState()
    }

    fun checkAuthState() {

        if (auth.currentUser == null) {
            _authstate.value = AuthState.Authenticated
        } else {
            _authstate.value = AuthState.Unauthenticated
        }

    }

    fun login(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            _authstate.value = AuthState.Error("Email and password cannot be empty")
            return
        }
        _authstate.value = AuthState.loded
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authstate.value = AuthState.Authenticated
                } else {
                    _authstate.value = AuthState.Error("Authentication failed")
                }
            }
    }

    fun signup(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            _authstate.value = AuthState.Error("Email and password cannot be empty")
            return
        }
        _authstate.value = AuthState.loded
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authstate.value = AuthState.Authenticated
                } else {
                    _authstate.value = AuthState.Error("Authentication failed")
                }
            }
    }

    fun singout(){
        auth.signOut()
        _authstate.value = AuthState.Unauthenticated
    }
}

sealed class AuthState(){
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object loded : AuthState()
    data class Error(val message: String) : AuthState()
}