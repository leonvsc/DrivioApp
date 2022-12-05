package nl.avans.drivioapp.model

import java.time.LocalDate

data class Advertisement(
    val advertisementId: Int,
    val title: String,
    val description: String,
    val price: Double,
    val startDate: LocalDate,
    val endDate: LocalDate
)
