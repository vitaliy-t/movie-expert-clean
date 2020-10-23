package my.test.movieexpert.ui.splashscreen.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import my.test.movieexpert.domain.usecase.MainUseCase

class SplashViewModel @ViewModelInject constructor(
    private val mainRepository: MainUseCase
) : ViewModel() {

    fun isUserLoggedIn() = mainRepository.isUserLoggedIn()

}