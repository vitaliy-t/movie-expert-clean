package my.test.movieexpert.ui.profilescreen.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import my.test.movieexpert.R
import my.test.movieexpert.databinding.FragmentProfileBinding
import my.test.movieexpert.ui.profilescreen.model.state.profile.EmailVerificationState
import my.test.movieexpert.ui.profilescreen.model.state.profile.LoggedInState
import my.test.movieexpert.ui.profilescreen.viewModel.ProfileViewModel
import my.test.movieexpert.util.alertDialogError
import my.test.movieexpert.util.alertDialogSuccess
import my.test.movieexpert.util.proceedToLoginScreen

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = profileViewModel

        profileViewModel.emailVerificationState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is EmailVerificationState.Sent -> {
                    alertDialogSuccess(requireContext(), requireContext().getString(R.string.profile_message_email_verification_was_sent))

                }
                is EmailVerificationState.Error -> {
                    alertDialogError(requireContext(), state.errorMessage)
                }
            }
        })

        profileViewModel.loggedInState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is LoggedInState.LoggedOut -> {
                    proceedToLoginScreen(requireActivity())
                }
            }
        })

        return binding.root
    }
}