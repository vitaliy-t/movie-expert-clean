package my.test.movieexpert.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import my.test.movieexpert.domain.repository.MoviesRepository
import my.test.movieexpert.domain.repository.MoviesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface DomainModule {

    @Binds
    @Singleton
    fun bindMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}