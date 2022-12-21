package nl.avans.drivioapp.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "userId")
    val userId: Int?,
//    @Json(name = "firstName")
//    val firstName: String,
//    @Json(name = "lastName")
//    val lastName: String,
//    @Json(name = "city")
//    val city: String,
//    @Json(name = "phone")
//    val phone: Long,
//    @Json(name = "email")
//    val email: String,
//    @Json(name = "passwords")
//    val passwords: String
)
