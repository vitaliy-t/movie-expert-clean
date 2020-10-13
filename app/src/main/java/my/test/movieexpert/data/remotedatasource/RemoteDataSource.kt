package my.test.movieexpert.data.remotedatasource

import my.test.movieexpert.domain.entity.PopularMovie
import my.test.movieexpert.domain.state.DataState

interface RemoteDataSource {
    suspend fun fetchPopularMovies(page: Int = 1): DataState<PopularMovie>
}