package co.icanteach.projectx.domain

import co.icanteach.projectx.common.Mapper
import co.icanteach.projectx.data.feed.response.PopularTVShowItemResponse
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import javax.inject.Inject

class PopularTvShowMapper @Inject constructor() :
    Mapper<List<PopularTVShowItemResponse>, List<PopularTvShowItem>> {
    override fun mapFrom(type: List<PopularTVShowItemResponse>): List<PopularTvShowItem> {
        return type.map { itemResponse ->
            PopularTvShowItem(
                name = itemResponse.name,
                img = itemResponse.img,
                status = itemResponse.status,
                appearance = itemResponse.appearance,
                better_call_saul_appearance = itemResponse.better_call_saul_appearance,
                birthday = itemResponse.birthday,
                category = itemResponse.category,
                char_id = itemResponse.char_id,
                nickname = itemResponse.nickname,
                occupation = itemResponse.occupation,
                portrayed = itemResponse.portrayed
            )
        }
    }
}