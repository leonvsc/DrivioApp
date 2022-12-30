package nl.avans.drivioapp.model

data class Reservation(
    val reservationId: Int?,
    val startDate: String,
    val endDate: String,
    val availabilityStatus: Boolean,
    val user: User,
    val advertisement: Advertisement
)
