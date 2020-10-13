package my.test.movieexpert.ui.profilescreen.model.state.profile

sealed class EmailVerificationState {
    object Loading : EmailVerificationState()
    object Sent : EmailVerificationState()
    data class Error(val errorMessage: String) : EmailVerificationState()
}