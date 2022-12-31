package nl.avans.drivioapp.model

import com.squareup.moshi.Json

data class Advertisement(
    @Json(name = "advertisementId")
    val advertisementId: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "price")
    val price: Double?,
    @Json(name = "startDate")
    val startDate: String?,
    @Json(name = "endDate")
    val endDate: String?,
    @Json(name="user")
    val user: User?
)
{
    constructor(advertisementId: Int?) : this(advertisementId, null, null, null, null, null, null)
}
