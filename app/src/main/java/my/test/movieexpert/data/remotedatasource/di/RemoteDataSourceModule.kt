package my.test.movieexpert.data.remotedatasource.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import my.test.movieexpert.data.remotedatasource.api.PopularMovieApi
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun bindPopularMovieApi(): PopularMovieApi = PopularMovieApi.getInstance()


}