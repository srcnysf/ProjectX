package co.icanteach.projectx.data.feed

import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.ui.applyLoading
import co.icanteach.projectx.data.feed.response.PopularTVShowItemResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MoviesRepository @Inject constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource) {

    fun fetchMovies(): Observable<Resource<List<PopularTVShowItemResponse>>> =
        moviesRemoteDataSource
            .fetchMovies()
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())
}