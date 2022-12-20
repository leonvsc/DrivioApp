package nl.avans.drivioapp.model

import com.squareup.moshi.Json
import java.time.LocalDate

data class Advertisement(
    @Json(name = "advertisementId")
    val advertisementId: Int?,
    @Json(name = "title")
    val title: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "price")
    val price: Double,
    @Json(name = "startDate")
    val startDate: LocalDate,
    @Json(name = "endDate")
    val endDate: LocalDate,
    @Json(name="userId")
    val userId: Int?
)
