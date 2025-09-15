package be.hcpl.android.speedrecords.domain.model

enum class UnitType{
    Celsius,
    Fahrenheit,
}

enum class DataSource(val type: String, val model: WeatherModel){
    ECMWF("ecmwf_ifs025", WeatherModel.IFS_AIFS),
    NOAA("gfs_seamless", WeatherModel.GFS_HRRR),
    DWD("icon_seamless", WeatherModel.ICON),
    METEO_FRANCE("meteofrance_seamless", WeatherModel.ARPEGE_AROME),
    KNMI("knmi_seamless", WeatherModel.HARMONIE),
}

enum class WeatherModel{
    IFS_AIFS,
    GFS_HRRR,
    ICON,
    ARPEGE_AROME,
    HARMONIE,
}

const val DEFAULT_THRESHOLD = 10
const val RANGE_MIN_THRESHOLD = 10
const val RANGE_MAX_THRESHOLD = 25
const val DEFAULT_FORECAST_DAYS = 7
const val RANGE_MIN_FORECAST_DAYS = 3
const val RANGE_MAX_FORECAST_DAYS = 15