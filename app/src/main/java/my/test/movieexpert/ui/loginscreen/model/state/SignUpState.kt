package my.test.movieexpert.ui.loginscreen.model.state

sealed class SignUpState {
    object Loading : SignUpState()
    object SignedUp : SignUpState()
    data class Error(val errorMessage: String) : SignUpState()
}