package my.test.movieexpert.ui.splashscreen.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SplashViewModel @ViewModelInject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    fun isUserLoggedIn() = firebaseAuth.currentUser != null

}