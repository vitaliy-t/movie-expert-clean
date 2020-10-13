package my.test.movieexpert.domain.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import my.test.movieexpert.data.localdatasource.LocalDataSource
import my.test.movieexpert.data.localdatasource.PopularMovieLocalDataSource
import my.test.movieexpert.data.remotedatasource.PopularMovieRemoteDataSource
import my.test.movieexpert.data.remotedatasource.RemoteDataSource

@Module
@InstallIn(ApplicationComponent::class)
interface DataSourceModule {
    @Binds
    fun bindPopularMovieRemoteDataSource(repository: PopularMovieRemoteDataSource): RemoteDataSource

    @Binds
    fun bindPopularMovieLocalDataSource(localDataSource: PopularMovieLocalDataSource): LocalDataSource
}