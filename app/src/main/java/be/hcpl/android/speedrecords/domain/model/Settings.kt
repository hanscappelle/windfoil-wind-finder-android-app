package be.hcpl.android.speedrecords.domain.model

enum class UnitType {
    Celsius,
    Fahrenheit,
}

enum class ModelType {
    MAIN,
    ALT,
}

enum class DataSource(val type: String, val model: WeatherModel) {
    ECMWF("ecmwf_ifs025", WeatherModel.IFS_AIFS),
    NOAA("gfs_seamless", WeatherModel.GFS_HRRR),
    DWD("icon_seamless", WeatherModel.ICON),
    METEO_FRANCE("meteofrance_seamless", WeatherModel.ARPEGE_AROME),
    KNMI("knmi_seamless", WeatherModel.HARMONIE_NL),
    DMI("dmi_seamless", WeatherModel.HARMONIE_DK),
    UKMO("ukmo_seamless", WeatherModel.UKMO),
    KMA("kma_seamless", WeatherModel.KMA),
    JMA("jma_seamless", WeatherModel.MSM_GSM),
    ICON_CH("meteoswiss_icon_ch1", WeatherModel.ICON_CH),
    MET_NORDIC("metno_seamless", WeatherModel.MET_NORDIC),
    GEM("gem_seamless", WeatherModel.GEM),
    ACCESS_G("bom_access_global", WeatherModel.ACCESS_G),
    GFS_GRAPES("cma_grapes_global", WeatherModel.GFS_GRAPES),
    ARPAE_AROME("italia_meteo_arpae_icon_2i", WeatherModel.ARPAE_AROME),
}

enum class WeatherModel(val origin: String, val resolutionMin: Int, val resolutionMax: Int) {
    IFS_AIFS("EU", 25, 25),
    GFS_HRRR("US", 3, 25),
    ICON("Germany", 2, 11),
    ARPEGE_AROME("France", 1, 25),
    HARMONIE_NL("Netherlands", 2, 2),
    HARMONIE_DK("Denmark", 2, 2),
    UKMO("UK", 2, 10),
    KMA("Korea", 2, 13),
    MSM_GSM("Japan", 5, 55),
    ICON_CH("Switzerland", 1, 2),
    MET_NORDIC("Norway", 1, 1),
    GEM("Canadian", 2, 3),
    ACCESS_G("Australia", 15, 15),
    GFS_GRAPES("China", 15, 15),
    ARPAE_AROME("Italy", 2, 2),
}

const val DEFAULT_THRESHOLD = 10
const val RANGE_MIN_THRESHOLD = 10
const val RANGE_MAX_THRESHOLD = 25
const val DEFAULT_FORECAST_DAYS = 7
const val RANGE_MIN_FORECAST_DAYS = 3
const val RANGE_MAX_FORECAST_DAYS = 15