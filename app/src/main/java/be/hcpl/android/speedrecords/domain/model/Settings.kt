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
    METEO_SWISS("meteoswiss_icon_ch1", WeatherModel.ICON_CH),
}

enum class WeatherModel{
    IFS_AIFS,
    GFS_HRRR,
    ICON,
    ARPEGE_AROME,
    HARMONIE,
    ICON_CH,
}