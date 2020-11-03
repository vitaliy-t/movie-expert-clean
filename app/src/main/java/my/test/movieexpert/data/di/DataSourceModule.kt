package my.test.movieexpert.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import my.test.movieexpert.data.DataSource
import my.test.movieexpert.data.localdatasource.LocalDataSource
import my.test.movieexpert.data.remotedatasource.RemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface DataSourceModule {
    @Binds
    @Local
    @Singleton
    fun bindLocalDataSource(localDataSource: LocalDataSource): DataSource

    @Binds
    @Remote
    @Singleton
    fun bindRemoteDataSource(remoteDataSource: RemoteDataSource): DataSource
}