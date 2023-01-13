package nl.avans.drivioapp.model

import com.squareup.moshi.Json

data class ElectricCar(
    @Json(name = "carId")
    val carId: Int?,

    @Json(name = "fastChargeSpeed")
    val fastChargeSpeed: Int?,

    @Json(name = "carRange")
    val carRange: Int?,

    @Json(name = "chargeConnection")
    val chargeConnection: String?,

    @Json(name = "buildYear")
    val buildYear: Int?,

    @Json(name = "numberPlate")
    val numberPlate: String?,

    @Json(name = "chargeSpeed")
    val chargeSpeed: Int?,

    @Json(name = "carType")
    val carType: String?,

    @Json(name = "fuelType")
    val fuelType: String?,

    @Json(name = "model")
    val model: String?,

    @Json(name = "whPerKm")
    val whPerKm: Any?,

    @Json(name = "gearBox")
    val gearBox: String?,

    @Json(name = "brand")
    val brand: String?,

    @Json(name = "user")
    val user: User1?,

    @Json(name = "latitude")
    val latitude: Double?,

    @Json(name = "longitude")
    val longitude: Double?
) {
    constructor(carId: Int?) : this(
        carId,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}