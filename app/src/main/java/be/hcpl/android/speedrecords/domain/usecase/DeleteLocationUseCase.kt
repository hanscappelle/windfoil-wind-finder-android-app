package be.hcpl.android.speedrecords.domain.usecase

import be.hcpl.android.speedrecords.domain.repository.ConfigRepository
import be.hcpl.android.speedrecords.domain.repository.LocationRepository
import be.hcpl.android.speedrecords.domain.usecase.DeleteLocationUseCase.Result.Failed
import be.hcpl.android.speedrecords.domain.usecase.DeleteLocationUseCase.Result.Success

class DeleteLocationUseCase(
    private val locationRepository: LocationRepository,
    private val configRepository: ConfigRepository,
) {

    operator fun invoke(name: String): Result {
        return when (val result = locationRepository.locationByName(name)) {
            is LocationRepository.Result.Success -> {
                result.location.let { matchedLocation ->
                    when (locationRepository.dropLocation(matchedLocation)) {
                        is LocationRepository.Result.Success -> {
                            dropDataForLocation(matchedLocation.name)
                            Success
                        }

                        is LocationRepository.Result.Data,
                        is LocationRepository.Result.Failed,
                        is LocationRepository.Result.Renamed,
                            -> handleError()

                    }
                }
            }

            is LocationRepository.Result.Failed,
            is LocationRepository.Result.Data,
            is LocationRepository.Result.Renamed,
                -> {
                // when location not found drop also cached data or it will stick
                dropDataForLocation(name)
                handleError()
            }
        }
    }

    private fun dropDataForLocation(name: String) {
        configRepository.dropFromCache(name)
    }

    private fun handleError() = Failed("location not found")

    sealed class Result {
        data object Success : Result()
        data class Failed(val message: String? = null) : Result()
    }
}