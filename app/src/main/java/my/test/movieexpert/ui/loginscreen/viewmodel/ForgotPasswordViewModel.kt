package my.test.movieexpert.ui.loginscreen.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import my.test.movieexpert.ui.loginscreen.model.form.ForgotPasswordForm
import my.test.movieexpert.ui.loginscreen.model.state.ForgotPasswordState

class ForgotPasswordViewModel @ViewModelInject constructor(
    val forgotPasswordForm: ForgotPasswordForm
) : ViewModel() {
    private val _forgotPasswordState = MutableLiveData<ForgotPasswordState>()
    val forgotPasswordState: LiveData<ForgotPasswordState>
        get() = _forgotPasswordState

    fun userForgotPassword() {
        _forgotPasswordState.value = ForgotPasswordState.Loading

        Firebase.auth.sendPasswordResetEmail(forgotPasswordForm.email.value!!)
            .addOnCompleteListener {
                _forgotPasswordState.value =
                    if (it.isSuccessful)
                        ForgotPasswordState.Sent
                    else
                        ForgotPasswordState.Error(it.exception!!.localizedMessage!!)
            }
    }
}