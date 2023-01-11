package nl.avans.drivioapp.repository

import nl.avans.drivioapp.model.FuelCar
import nl.avans.drivioapp.service.DrivioApi
import nl.avans.drivioapp.service.FuelCarService
import retrofit2.Response

class FuelCarRepository : FuelCarService {
    override suspend fun getFuelCars(): List<FuelCar> {
        return DrivioApi.fuelCarService.getFuelCars()
    }

    override suspend fun getFuelCarById(carId: Int): Response<FuelCar> {
        return DrivioApi.fuelCarService.getFuelCarById(carId)
    }

    override suspend fun postFuelCarWithResponse(fuelCar: FuelCar): Response<Unit> {
        return DrivioApi.fuelCarService.postFuelCarWithResponse(fuelCar)
    }
}