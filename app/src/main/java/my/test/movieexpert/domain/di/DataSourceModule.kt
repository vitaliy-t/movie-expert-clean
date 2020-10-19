package my.test.movieexpert.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import my.test.movieexpert.data.DataSource
import my.test.movieexpert.data.localdatasource.LocalDataSource
import my.test.movieexpert.data.remotedatasource.RemoteDataSource

@Module
@InstallIn(ApplicationComponent::class)
interface DataSourceModule {
    @Binds
    @Local
    fun bindLocalDataSource(localDataSource: LocalDataSource): DataSource

    @Binds
    @Remote
    fun bindRemoteDataSource(remoteDataSource: RemoteDataSource): DataSource
}