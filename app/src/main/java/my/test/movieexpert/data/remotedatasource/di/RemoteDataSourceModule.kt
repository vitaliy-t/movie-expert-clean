package my.test.movieexpert.data.remotedatasource.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import my.test.movieexpert.data.remotedatasource.api.LatestMovieApi
import my.test.movieexpert.data.remotedatasource.api.PopularMovieApi
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun providePopularMovieApi(): PopularMovieApi = PopularMovieApi.getInstance()

    @Singleton
    @Provides
    fun provideLatestMovieApi(): LatestMovieApi = LatestMovieApi.getInstance()
}