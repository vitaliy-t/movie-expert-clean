package my.test.movieexpert.ui.di

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object LiveDataModule {

    @Provides
    fun provideMutableLiveDataOfString(): MutableLiveData<String> = MutableLiveData<String>()
}