package my.test.movieexpert.ui.profilescreen.model.state.profile

sealed class LoggedInState {
    object LoggedIn : LoggedInState()
    object LoggedOut : LoggedInState()
}