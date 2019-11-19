package co.icanteach.projectx.data

import co.icanteach.projectx.data.feed.response.PopularTVShowItemResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface InterviewRestInterface {

    @GET("api/characters")
    fun fetchMovies(): Observable<List<PopularTVShowItemResponse>>
}