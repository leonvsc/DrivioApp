package nl.avans.drivioapp.model

import com.squareup.moshi.Json

data class AddCar (
    @Json(name="fastChargeSpeed")
    val fastChargeSpeed: Int? = null,

    @Json(name="carRange")
    val carRange: Int? = null,

    @Json(name="chargeConnection")
    val chargeConnection: String? = null,

    @Json(name="buildYear")
    val buildYear: Int? = null,

    @Json(name="numberPlate")
    val numberPlate: String? = null,

    @Json(name="carId")
    val carId: Int? = null,

    @Json(name="chargeSpeed")
    val chargeSpeed: Int? = null,

    @Json(name="carType")
    val carType: String? = null,

    @Json(name="fuelType")
    val fuelType: String? = null,

    @Json(name="model")
    val model: String? = null,

    @Json(name="whPerKm")
    val whPerKm: Any? = null,

    @Json(name="gearBox")
    val gearBox: String? = null,

    @Json(name="brand")
    val brand: String? = null,

    @Json(name="user")
    val user: User? = null
)

data class Passwords(

    @Json(name="password")
    val password: String? = null,

    @Json(name="passwordId")
    val passwordId: Int? = null
)

data class User(

    @Json(name="firstName")
    val firstName: String? = null,

    @Json(name="lastName")
    val lastName: String? = null,

    @Json(name="city")
    val city: String? = null,

    @Json(name="phone")
    val phone: Int? = null,

    @Json(name="passwords")
    val passwords: Passwords? = null,

    @Json(name="userId")
    val userId: Int? = null,

    @Json(name="email")
    val email: String? = null
        )