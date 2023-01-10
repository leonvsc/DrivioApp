package nl.avans.drivioapp.model

import com.squareup.moshi.Json

data class FuelCar(

    @Json(name = "carType")
    val carType: String,

    @Json(name = "carRange")
    val carRange: Int,

    @Json(name = "fuelType")
    val fuelType: String,

    @Json(name = "literPer100Km")
    val literPer100Km: Double,

    @Json(name = "model")
    val model: String,

    @Json(name = "buildYear")
    val buildYear: Int,

    @Json(name = "gearBox")
    val gearBox: String,

    @Json(name = "brand")
    val brand: String,

    @Json(name = "numberPlate")
    val numberPlate: String,

    @Json(name = "tankSize")
    val tankSize: Int,

    @Json(name = "user")
    val user: User,

    @Json(name = "latitude")
    val latitude: Double,

    @Json(name = "longitude")
    val longitude: Double
)
