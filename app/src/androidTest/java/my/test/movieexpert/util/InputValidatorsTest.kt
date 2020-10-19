package my.test.movieexpert.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import my.test.movieexpert.R
import my.test.movieexpert.ui.utilities.InputValidators
import my.test.movieexpert.ui.utilities.USER_PASSWORD_MIN_CHARACTERS
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputValidatorsTest {
    private lateinit var context: Context
    private lateinit var inputValidators: InputValidators

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        inputValidators = InputValidators(context)
    }

    /**
     *  START OF emailValidation
     */

    @Test
    fun emailIsEmpty_returnsError() {
        val email = ""

        val response = inputValidators.validateEmail(email)

        assertThat(response).isEqualTo(context.resources.getString(R.string.error_message_field_is_empty))
    }

    @Test
    fun emailIsBadFormat_returnsError() {
        val email = "12312@@olt.c"

        val response = inputValidators.validateEmail(email)

        assertThat(response).isEqualTo(context.resources.getString(R.string.error_message_invalid_email))
    }

    @Test
    fun emailIsValid_returnsNull() {
        val email = "theusername0@gmail.com"

        val response = inputValidators.validateEmail(email)

        assertThat(response).isNull()
    }

    /**
     *  END OF emailValidation
     */

    /**
     *  START OF passwordValidation
     */

    @Test
    fun passwordIsEmpty_returnsError() {
        val password = ""

        val response = inputValidators.validatePassword(password)

        assertThat(response).isEqualTo(context.resources.getString(R.string.error_message_field_is_empty))
    }

    @Test
    fun passwordIsTooShort_returnsError() {
        val password = "1234567"

        val response = inputValidators.validatePassword(password)

        assertThat(response).isEqualTo(context.resources.getString(R.string.error_message_password_is_too_short, USER_PASSWORD_MIN_CHARACTERS))
    }

    @Test
    fun passwordIsValid_returnsNull() {
        val password = "12345678"

        val response = inputValidators.validatePassword(password)

        assertThat(response).isNull()
    }

    /**
     *  END OF passwordValidation
     */

    /**
     *  START OF confirmPasswordValidation
     */

    @Test
    fun confirmPasswordIsEmpty_returnsError() {
        val confirmPassword = ""

        val response = inputValidators.validateConfirmPassword(confirmPassword)

        assertThat(response).isEqualTo(context.resources.getString(R.string.error_message_field_is_empty))
    }

    @Test
    fun passwordsMismatch_returnsError() {
        val password = "12345"
        val confirmPassword = "12346"

        val response = inputValidators.validateConfirmPassword(confirmPassword, password)

        assertThat(response).isEqualTo(context.resources.getString(R.string.error_message_passwords_mismatch))
    }

    @Test
    fun confirmPasswordIsValid_returnsNull() {
        val password = "12345"
        val confirmPassword = "12345"

        val response = inputValidators.validateConfirmPassword(confirmPassword, password)

        assertThat(response).isNull()
    }

    /**
     *  END OF confirmPasswordValidation
     */

}