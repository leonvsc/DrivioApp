package nl.avans.drivioapp.model

import com.squareup.moshi.Json

data class Reservation(
    @Json(name = "reservationId")
    val reservationId: Int?,
    @Json(name = "startDate")
    val startDate: String,
    @Json(name = "endDate")
    val endDate: String,
    @Json(name = "availabilityStatus")
    val availabilityStatus: Boolean,
    @Json(name = "user")
    val user: User,
    @Json(name = "advertisement")
    val advertisement: Advertisement
)
