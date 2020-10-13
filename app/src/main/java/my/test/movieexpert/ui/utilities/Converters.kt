@file:JvmName("Converters")

package my.test.movieexpert.ui.utilities

import android.view.View

fun booleanToVisibility(bool: Boolean): Int {
    return if (bool) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}