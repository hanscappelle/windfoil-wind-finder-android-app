package be.hcpl.android.speedrecords.domain.usecase

import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.repository.ConfigRepository
import be.hcpl.android.speedrecords.domain.repository.LocationRepository

class RenameLocationUseCase(
    private val configRepository: ConfigRepository,
    private val locationRepository: LocationRepository,
) {

    operator fun invoke(
        oldName: String,
        newName: String,
    ): Result {
        return when (val result = locationRepository.renameLocation(oldName, newName)) {
            is LocationRepository.Result.Success,
            is LocationRepository.Result.Failed,
            is LocationRepository.Result.Data,
                -> Result.Failed()

            is LocationRepository.Result.Renamed -> {
                // also update in cached data
                configRepository.updateLocationName(result.oldLocation, result.newLocation)
                // return result
                Result.Success(result.oldLocation, result.newLocation)
            }
        }
    }

    sealed class Result {
        data class Success(val oldLocation: LocationData, val newLocation: LocationData) : Result()
        data class Failed(val message: String? = null) : Result()
    }
}
