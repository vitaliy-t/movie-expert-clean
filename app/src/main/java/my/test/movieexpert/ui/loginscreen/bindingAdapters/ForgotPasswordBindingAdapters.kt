package my.test.movieexpert.ui.loginscreen.bindingAdapters

import android.graphics.Color
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import my.test.movieexpert.R
import my.test.movieexpert.ui.loginscreen.model.state.ForgotPasswordState

object ForgotPasswordBindingAdapters {

    @BindingAdapter(value = ["emailError"])
    @JvmStatic
    fun Button.setEnabled(email: LiveData<String?>) {
        if (email.value == null) {
            this.isEnabled = true
            this.setBackgroundColor(this.context.getColor(R.color.colorPrimaryDark))
        } else {
            this.isEnabled = false
            this.setBackgroundColor(Color.GRAY)
        }
    }

    @BindingAdapter("setButtonState")
    @JvmStatic
    fun Button.setButtonState(forgotPasswordState: ForgotPasswordState?) {

        when (forgotPasswordState) {
            is ForgotPasswordState.Loading -> this.showProgress {
                buttonTextRes = R.string.state_loading
                progressColor = Color.WHITE
            }
            else -> this.hideProgress(this.context.getString(R.string.forgot_password_button_submit))
        }
    }

}