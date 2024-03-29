package nl.avans.drivioapp.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DrivioApi {

    private val BASE_URL = "https://drivio.nl/api/v1/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val advertisementService: AdvertisementService by lazy {
        retrofit.create(AdvertisementService::class.java)
    }

    val reservationService: ReservationService by lazy {
        retrofit.create(ReservationService::class.java)
    }
    val electricCarService: ElectricCarService by lazy {
        retrofit.create(ElectricCarService::class.java)
    }
    val fuelCarService: FuelCarService by lazy {
        retrofit.create(FuelCarService::class.java)
    }
    val hydrogenCarService: HydrogenCarService by lazy {
        retrofit.create(HydrogenCarService::class.java)
    }
    val statisticsService: StatisticsService by lazy {
        retrofit.create(StatisticsService::class.java)
    }
}