package be.hcpl.android.speedrecords.domain.usecase

import be.hcpl.android.speedrecords.domain.LocationRepository
import be.hcpl.android.speedrecords.domain.usecase.CreateLocationUseCase.Result.Failed
import be.hcpl.android.speedrecords.domain.usecase.CreateLocationUseCase.Result.Success

class CreateLocationUseCase(
    private val locationRepository: LocationRepository,
) {

    // extract interface once needed

    operator fun invoke(locationData: String?): Result {
        val result = locationRepository.addNewLocation(locationData)
        return when (result) {
            is LocationRepository.Result.Success -> Success
            is LocationRepository.Result.Failed -> Failed(result.message)
            is LocationRepository.Result.Data,
            is LocationRepository.Result.Renamed,
                -> Failed()
        }
    }

    sealed class Result {
        data object Success : Result()
        data class Failed(val message: String? = null) : Result()
    }
}