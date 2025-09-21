package be.hcpl.android.speedrecords.domain.usecase

import android.net.Uri
import be.hcpl.android.speedrecords.domain.LocationRepository
import be.hcpl.android.speedrecords.domain.usecase.ShowLocationUseCase.Result.Success

class ShowLocationUseCase(
    private val locationRepository: LocationRepository,
) {

    operator fun invoke(name: String): Result {
        return when (val result = locationRepository.locationByName(name)) {
            is LocationRepository.Result.Data -> {
                result.locations.firstOrNull()?.let { matchedLocation ->
                    Success(Uri.parse("https://maps.google.com/maps?q=loc:" + matchedLocation.lat + "," + matchedLocation.lng + " (" + matchedLocation.name + ")"))
                } ?: handleError()
            }

            is LocationRepository.Result.Failed,
            is LocationRepository.Result.Renamed,
            is LocationRepository.Result.Success,
                -> handleError()
        }
    }

    private fun handleError() = Result.Failed()

    sealed class Result {
        data class Success(val uri: Uri) : Result()
        data class Failed(val message: String? = null) : Result()

    }
}