package my.test.movieexpert.ui.loginscreen.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import my.test.movieexpert.R
import my.test.movieexpert.databinding.FragmentSignUpBinding
import my.test.movieexpert.ui.loginscreen.model.state.SignUpState
import my.test.movieexpert.ui.loginscreen.viewmodel.SignUpViewModel
import my.test.movieexpert.ui.utilities.AlertDialog
import my.test.movieexpert.ui.utilities.USER_PASSWORD_MIN_CHARACTERS
import my.test.movieexpert.ui.utilities.hideKeyboard
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    @Inject
    lateinit var alertDialog: AlertDialog

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = signUpViewModel

        signUpViewModel.signUpState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is SignUpState.SignedUp -> {
                    alertDialog.success(requireContext(), requireContext().getString(R.string.sign_up_message_success))
                    this.findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                }
            }
        })

        binding.signUpInputLayoutPassword.helperText = requireContext().getString(R.string.input_helper_min_password_length, USER_PASSWORD_MIN_CHARACTERS)

        binding.signUpInputLayoutEmail.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) hideKeyboard() }
        binding.signUpInputLayoutPassword.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) hideKeyboard() }
        binding.signUpInputLayoutConfirmPassword.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) hideKeyboard() }

        return binding.root
    }
}