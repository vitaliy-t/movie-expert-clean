package my.test.movieexpert.ui.utilities

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import my.test.movieexpert.R
import javax.inject.Inject

class AlertDialog @Inject constructor() {
    fun success(context: Context, messageBody: String, messageTitle: String = context.getString(R.string.alert_dialog_default_title_success)) {
        MaterialAlertDialogBuilder(context)
            .setTitle(messageTitle)
            .setMessage(messageBody)
            .setIcon(ContextCompat.getDrawable(context, R.drawable.ic_success))
            .setCancelable(false)
            .setPositiveButton(context.getString(R.string.alert_dialog_default_button_ok)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun error(context: Context, messageBody: String, messageTitle: String = context.getString(R.string.alert_dialog_default_title_failure)) {
        MaterialAlertDialogBuilder(context)
            .setTitle(messageTitle)
            .setMessage(messageBody)
            .setIcon(ContextCompat.getDrawable(context, R.drawable.ic_error))
            .setCancelable(false)
            .setPositiveButton(context.getString(R.string.alert_dialog_default_button_ok)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun warning(context: Context, messageBody: String?, messageTitle: String? = context.getString(R.string.alert_dialog_default_title_warning)) {
        MaterialAlertDialogBuilder(context)
            .setTitle(messageTitle)
            .setMessage(messageBody)
            .setIcon(ContextCompat.getDrawable(context, R.drawable.ic_warning))
            .setCancelable(false)
            .setPositiveButton(context.resources.getString(R.string.alert_dialog_default_button_ok)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}