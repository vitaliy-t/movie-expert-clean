package my.test.movieexpert.ui.utilities

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {

    @BindingAdapter("inputValidation")
    @JvmStatic
    fun TextInputLayout.validateInput(field: LiveData<String?>) {
        this.error = field.value
    }


}