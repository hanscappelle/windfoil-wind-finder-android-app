package be.hcpl.android.speedrecords.domain.usecase

import android.net.Uri
import be.hcpl.android.speedrecords.domain.repository.LocationRepository
import be.hcpl.android.speedrecords.domain.usecase.ShowLocationUseCase.Result.Success

class ShowLocationUseCase(
    private val locationRepository: LocationRepository,
) {

    operator fun invoke(name: String): Result {
        return when (val result = locationRepository.locationByName(name)) {
            is LocationRepository.Result.Success -> result.location.let { matchedLocation ->
                Success(Uri.parse("https://maps.google.com/maps?q=loc:" + matchedLocation.lat + "," + matchedLocation.lng + " (" + matchedLocation.name + ")"))
            }

            is LocationRepository.Result.Data,
            is LocationRepository.Result.Failed,
            is LocationRepository.Result.Renamed,
                -> handleError()
        }
    }

    private fun handleError() = Result.Failed()

    sealed class Result {
        data class Success(val uri: Uri) : Result()
        data class Failed(val message: String? = null) : Result()

    }
}