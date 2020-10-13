package my.test.movieexpert.ui.utilities

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import my.test.movieexpert.R
import javax.inject.Inject

class InputValidators @Inject constructor(@ApplicationContext val context: Context) {

    fun validateEmail(email: String?): String? {
        return when {
            email.isNullOrEmpty() -> context.getString(R.string.error_message_field_is_empty)
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> context.getString(R.string.error_message_invalid_email)
            else -> null
        }
    }

    fun validatePassword(password: String?): String? {
        return when {
            password.isNullOrEmpty() -> context.getString(R.string.error_message_field_is_empty)
            password.length < USER_PASSWORD_MIN_CHARACTERS -> context.getString(R.string.error_message_password_is_too_short, USER_PASSWORD_MIN_CHARACTERS)
            else -> null
        }
    }

    fun validateConfirmPassword(confirmPassword: String?, password: String? = null): String? {
        return when {
            confirmPassword.isNullOrEmpty() -> context.getString(R.string.error_message_field_is_empty)
            password != confirmPassword -> context.getString(R.string.error_message_passwords_mismatch)
            else -> null
        }
    }
}





