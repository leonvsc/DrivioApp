package nl.avans.drivioapp.repository

import nl.avans.drivioapp.model.ElectricCar
import nl.avans.drivioapp.service.DrivioApi
import nl.avans.drivioapp.service.ElectricCarService
import retrofit2.Response

class ElectricCarRepository : ElectricCarService {

    override suspend fun getElectricCars(): List<ElectricCar> {
        return DrivioApi.electricCarService.getElectricCars()
    }

    override suspend fun getElectricCarById(carId: Int): Response<ElectricCar> {
        return DrivioApi.electricCarService.getElectricCarById(carId)
    }

    override suspend fun postElectricCarWithResponse(electricCar: ElectricCar): Response<Unit> {
        return DrivioApi.electricCarService.postElectricCarWithResponse(electricCar)
    }
}