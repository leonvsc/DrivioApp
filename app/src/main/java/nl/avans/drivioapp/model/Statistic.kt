package nl.avans.drivioapp.model

import com.squareup.moshi.Json

class Statistic(

    @Json(name="statisticId")
    val statisticId: Int?,

    @Json(name="name")
    val name: String,

    @Json(name="value")
    val value: String,

    @Json(name="user")
    val user: User
)