package co.icanteach.projectx.ui.populartvshows

import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem

class PopularTVShowsFeedItemViewState(private val tvShow: PopularTvShowItem) {

    fun getImageUrl() = tvShow.img
    fun getTvShowName() = tvShow.name
    fun getOccupation() = tvShow.occupation
    fun getStatus() = tvShow.status
    fun getNickname() = tvShow.nickname
    fun getAppearence() = tvShow.appearance

}