package my.test.movieexpert.ui.loginscreen.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import my.test.movieexpert.ui.loginscreen.model.form.SignInForm
import my.test.movieexpert.ui.loginscreen.model.state.SignInState

class SignInViewModel @ViewModelInject constructor(
    val signInForm: SignInForm
) : ViewModel() {
    private val _signInState = MutableLiveData<SignInState>()
    val signInState: LiveData<SignInState>
        get() = _signInState

    fun userSignIn() {
        _signInState.value = SignInState.Loading

        Firebase.auth.signInWithEmailAndPassword(signInForm.email.value!!, signInForm.password.value!!)
            .addOnCompleteListener {
                _signInState.value =
                    if (it.isSuccessful)
                        SignInState.SignedIn
                    else
                        SignInState.Error(it.exception!!.localizedMessage!!)
            }
    }
}