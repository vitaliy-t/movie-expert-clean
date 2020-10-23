package my.test.movieexpert.ui.profilescreen.bindingadapters

import android.graphics.Color
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import my.test.movieexpert.R
import my.test.movieexpert.ui.profilescreen.model.state.profile.EmailVerificationState

object ProfileBindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(uri: Uri?) {
        uri?.let {
            Glide.with(context)
                .load(uri)
                .into(this)
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(uri: String?) {
        if (!uri.isNullOrEmpty()) {
            Glide.with(context)
                .load(uri)
                .into(this)
        }
    }

    @BindingAdapter("isEmailConfirmed")
    @JvmStatic
    fun TextView.setEmailConfirmed(state: Boolean) {
        this.text = if (state) {
            null
        } else {
            context.getString(R.string.profile_message_email_is_not_confirmed)
        }
    }

    @BindingAdapter("setButtonState")
    @JvmStatic
    fun Button.setButtonState(emailVerificationState: EmailVerificationState?) {

        when (emailVerificationState) {
            is EmailVerificationState.Loading -> this.showProgress {
                buttonTextRes = R.string.state_loading
                progressColor = Color.BLACK
            }
            else -> this.hideProgress(this.context.getString(R.string.profile_button_verify_email))
        }
    }
}