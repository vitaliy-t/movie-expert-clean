package my.test.movieexpert.ui.loginscreen.model.form

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import my.test.movieexpert.ui.utilities.InputValidators
import javax.inject.Inject

data class SignUpForm @Inject constructor(
    private val inputValidators: InputValidators,
    val email: MutableLiveData<String>,
    val password: MutableLiveData<String>,
    val confirmPassword: MutableLiveData<String>
) {

    val emailError = MediatorLiveData<String?>().apply {
        value = ""
        addSource(email) {
            value = inputValidators.validateEmail(it)
        }
    }

    val passwordError = MediatorLiveData<String?>().apply {
        value = ""
        addSource(password) {
            value = inputValidators.validatePassword(it)
        }
    }

    val confirmPasswordError = MediatorLiveData<String?>().apply {
        value = ""
        addSource(confirmPassword) {
            value = inputValidators.validateConfirmPassword(password = password.value, confirmPassword = it)
        }
    }
}