package co.icanteach.projectx.data.feed.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PopularTVShowItemResponse(
    @SerializedName("char_id")
    val char_id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("occupation")
    @Expose
    val occupation: ArrayList<String>?,
    @SerializedName("img")
    val img: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("appearance")
    @Expose
    val appearance: ArrayList<Int>?,
    @SerializedName("portrayed")
    val portrayed: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("better_call_saul_appearance")
    @Expose
    val better_call_saul_appearance: ArrayList<Int>?

)