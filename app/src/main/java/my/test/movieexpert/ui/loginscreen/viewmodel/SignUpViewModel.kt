package my.test.movieexpert.ui.loginscreen.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import my.test.movieexpert.ui.loginscreen.model.form.SignUpForm
import my.test.movieexpert.ui.loginscreen.model.state.SignUpState

class SignUpViewModel @ViewModelInject constructor(
    val signUpForm: SignUpForm
) : ViewModel() {
    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState>
        get() = _signUpState

    fun userSignUp() {
        _signUpState.value = SignUpState.Loading

        Firebase.auth.createUserWithEmailAndPassword(signUpForm.email.value!!, signUpForm.confirmPassword.value!!)
            .addOnCompleteListener {
                _signUpState.value =
                    if (it.isSuccessful)
                        SignUpState.SignedUp
                    else
                        SignUpState.Error(it.exception!!.localizedMessage!!)
            }
    }

}