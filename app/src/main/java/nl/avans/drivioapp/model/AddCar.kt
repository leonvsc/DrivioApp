package nl.avans.drivioapp.model

import com.squareup.moshi.Json

data class AddCar(
    @Json(name = "fastChargeSpeed")
    val fastChargeSpeed: Int,

    @Json(name = "carRange")
    val carRange: Int,

    @Json(name = "chargeConnection")
    val chargeConnection: String,

    @Json(name = "buildYear")
    val buildYear: Int,

    @Json(name = "numberPlate")
    val numberPlate: String,

    @Json(name = "carId")
    val carId: Int,

    @Json(name = "chargeSpeed")
    val chargeSpeed: Int,

    @Json(name = "carType")
    val carType: String,

    @Json(name = "fuelType")
    val fuelType: String,

    @Json(name = "model")
    val model: String,

    @Json(name = "whPerKm")
    val whPerKm: Int,

    @Json(name = "gearBox")
    val gearBox: String,

    @Json(name = "brand")
    val brand: String,

    )
