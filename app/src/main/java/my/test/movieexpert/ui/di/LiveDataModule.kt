package my.test.movieexpert.ui.di

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


// TODO: Understand how the hell this works in current scenario.
//      Keep in mind:
//          SignInViewModel
//          SignUpViewModel
//          ForgotPasswordViewModel
//      All 3 are using this, yet the data that is being entered in each field is different. => Confusing...


@InstallIn(ActivityComponent::class)
@Module
object LiveDataModule {

    @Provides
    fun provideMutableLiveDataOfString(): MutableLiveData<String> = MutableLiveData<String>()
}