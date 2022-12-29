package nl.avans.drivioapp.model

import com.squareup.moshi.Json

data class HydrogenCar(

	@Json(name="carRange")
	val carRange: Int,

	@Json(name="tankSpeed")
	val tankSpeed: Int,

	@Json(name="buildYear")
	val buildYear: Int,

	@Json(name="kgPer100Km")
	val kgPer100Km: Any,

	@Json(name="numberPlate")
	val numberPlate: String,

	@Json(name="tankSize")
	val tankSize: Int,

	@Json(name="carType")
	val carType: String,

	@Json(name="fuelType")
	val fuelType: String,

	@Json(name="model")
	val model: String,

	@Json(name="gearBox")
	val gearBox: String,

	@Json(name="brand")
	val brand: String,

	@Json(name="user")
	val userElectricCar: UserHydrogenCar
)

data class UserHydrogenCar(

	@Json(name="userId")
	val userId: Int? = null
)