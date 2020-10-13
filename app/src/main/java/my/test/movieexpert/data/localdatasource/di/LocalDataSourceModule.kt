package my.test.movieexpert.data.localdatasource.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import my.test.movieexpert.data.localdatasource.dao.PopularMovieDao
import my.test.movieexpert.data.localdatasource.database.MainDatabase
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object LocalDataSourceModule {
    @Singleton
    @Provides
    fun bindMainDatabase(@ApplicationContext context: Context): MainDatabase = MainDatabase.getInstance(context)

    @Provides
    fun bindPopularMovieDao(mainDatabase: MainDatabase): PopularMovieDao = mainDatabase.popularMovieDao()


}