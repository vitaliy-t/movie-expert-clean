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
import my.test.movieexpert.databinding.FragmentForgotPasswordBinding
import my.test.movieexpert.ui.loginscreen.model.state.ForgotPasswordState
import my.test.movieexpert.ui.loginscreen.viewmodel.ForgotPasswordViewModel
import my.test.movieexpert.util.alertDialogSuccess
import my.test.movieexpert.util.hideKeyboard

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private val forgotPasswordViewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = forgotPasswordViewModel

        forgotPasswordViewModel.forgotPasswordState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is ForgotPasswordState.Sent -> {
                    alertDialogSuccess(requireContext(), getString(R.string.forgot_password_message_success))
                    this.findNavController().navigate(R.id.action_forgotPasswordFragment_to_signInFragment)
                }
            }
        })

        binding.forgotPasswordInputLayoutEmail.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) hideKeyboard() }

        return binding.root
    }
}