package my.test.movieexpert.ui.loginscreen.model.state

sealed class ForgotPasswordState {
    object Loading : ForgotPasswordState()
    object Sent : ForgotPasswordState()
    data class Error(val errorMessage: String) : ForgotPasswordState()
}