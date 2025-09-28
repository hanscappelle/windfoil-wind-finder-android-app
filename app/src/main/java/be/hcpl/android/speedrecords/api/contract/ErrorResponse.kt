package be.hcpl.android.speedrecords.api.contract

import androidx.annotation.Keep

@Keep
class ErrorResponse(
    val error: Boolean?,
    val reason: String?,
)
