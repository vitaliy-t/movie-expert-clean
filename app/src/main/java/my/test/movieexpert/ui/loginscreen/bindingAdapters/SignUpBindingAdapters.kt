package my.test.movieexpert.ui.loginscreen.bindingAdapters

import android.graphics.Color
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import my.test.movieexpert.R
import my.test.movieexpert.ui.loginscreen.model.state.SignUpState

object SignUpBindingAdapters {

    @BindingAdapter("setButtonState")
    @JvmStatic
    fun Button.setButtonState(signUpState: SignUpState?) {

        when (signUpState) {
            is SignUpState.Loading -> this.showProgress {
                buttonTextRes = R.string.state_loading
                progressColor = Color.WHITE
            }
            else -> this.hideProgress(this.context.getString(R.string.sign_up_button_submit))
        }
    }

    @BindingAdapter(value = ["emailError", "passwordError", "confirmPasswordError"])
    @JvmStatic
    fun Button.setEnabled(email: LiveData<String?>, password: LiveData<String?>, confirmPassword: LiveData<String?>) {
        if (email.value == null && password.value == null && confirmPassword.value == null) {
            this.isEnabled = true
            this.setBackgroundColor(this.context.getColor(R.color.colorPrimaryDark))
        } else {
            this.isEnabled = false
            this.setBackgroundColor(Color.GRAY)
        }
    }
}