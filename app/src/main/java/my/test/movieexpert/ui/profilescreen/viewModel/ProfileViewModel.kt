package my.test.movieexpert.ui.profilescreen.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import my.test.movieexpert.domain.usecase.MainUseCase
import my.test.movieexpert.ui.profilescreen.model.state.profile.EmailVerificationState
import my.test.movieexpert.ui.profilescreen.model.state.profile.LoggedInState

class ProfileViewModel @ViewModelInject constructor(
    private val mainRepository: MainUseCase
) : ViewModel() {
    val user: FirebaseUser = mainRepository.getCurrentFirebaseUser()!!
    val isEmailVerified = user.isEmailVerified  // TODO: Find more elegant way to deal with this; Currently needs to be stored separately in order to be used in DataBinding

    private val _loggedInState = MutableLiveData<LoggedInState>()
    val loggedInState: LiveData<LoggedInState>
        get() = _loggedInState

    private val _emailVerificationState = MutableLiveData<EmailVerificationState>()
    val emailVerificationState: LiveData<EmailVerificationState>
        get() = _emailVerificationState

    init {
        _loggedInState.value = LoggedInState.LoggedIn
    }

    fun sendUserVerificationEmail() {
        _emailVerificationState.value = EmailVerificationState.Loading

        user.sendEmailVerification()
            .addOnCompleteListener {
                _emailVerificationState.value =
                    if (it.isSuccessful)
                        EmailVerificationState.Sent
                    else
                        EmailVerificationState.Error(it.exception!!.localizedMessage!!)
            }
    }

    fun userLogout() {
        mainRepository.userLogout()
        _loggedInState.value = LoggedInState.LoggedOut

    }
}