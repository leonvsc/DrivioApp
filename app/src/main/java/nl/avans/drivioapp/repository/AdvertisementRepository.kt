package nl.avans.drivioapp.repository

import nl.avans.drivioapp.model.Advertisement
import nl.avans.drivioapp.service.AdvertisementService
import nl.avans.drivioapp.service.DrivioApi
import retrofit2.Response

class AdvertisementRepository : AdvertisementService {

    override suspend fun getAdvertisements(): List<Advertisement> {
        return DrivioApi.advertisementService.getAdvertisements()
    }

    override suspend fun getAdvertisementById(advertisementId: Int): Response<Advertisement> {
        return DrivioApi.advertisementService.getAdvertisementById(advertisementId)
    }

    override suspend fun postAdvertisementWithResponse(advertisement: Advertisement): Response<Unit> {
        return DrivioApi.advertisementService.postAdvertisementWithResponse(advertisement)
    }
}