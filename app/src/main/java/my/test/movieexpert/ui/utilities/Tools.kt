package my.test.movieexpert.ui.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
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