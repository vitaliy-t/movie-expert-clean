package my.test.movieexpert.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import my.test.movieexpert.R
import my.test.movieexpert.ui.loginscreen.view.LoginScreenActivity
import my.test.movieexpert.ui.profilescreen.view.ProfileScreenActivity

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
    currentFocus?.clearFocus()
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun proceedToLoginScreen(activity: Activity) {
    activity.startActivity((Intent(activity, LoginScreenActivity::class.java)))
    activity.finish()
}

fun proceedToProfileScreen(activity: Activity) {
    activity.startActivity(Intent(activity, ProfileScreenActivity::class.java))
    activity.finish()
}

fun alertDialogSuccess(context: Context, messageBody: String, messageTitle: String = context.getString(R.string.alert_dialog_default_title_success)) {
    MaterialAlertDialogBuilder(context)
        .setTitle(messageTitle)
        .setMessage(messageBody)
        .setIcon(ContextCompat.getDrawable(context, R.drawable.ic_checkmark))
        .setCancelable(false)
        .setPositiveButton(context.getString(R.string.alert_dialog_default_button_ok)) { dialog, _ ->
            dialog.dismiss()
        }.show()
}

fun alertDialogError(context: Context, messageBody: String, messageTitle: String = context.getString(R.string.alert_dialog_default_title_failure)) {
    MaterialAlertDialogBuilder(context)
        .setTitle(messageTitle)
        .setMessage(messageBody)
        .setIcon(ContextCompat.getDrawable(context, R.drawable.ic_error))
        .setCancelable(false)
        .setPositiveButton(context.getString(R.string.alert_dialog_default_button_ok)) { dialog, _ ->
            dialog.dismiss()
        }.show()
}