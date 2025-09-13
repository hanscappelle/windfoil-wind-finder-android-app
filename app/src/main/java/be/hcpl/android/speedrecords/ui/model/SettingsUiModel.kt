package be.hcpl.android.speedrecords.ui.model

data class SettingsUiModel(
    val unit: UnitType = UnitType.Celsius,
    val source: DataSource = DataSource.ECMWF,
    val model: WeatherModel = WeatherModel.IFS_AIFS,
)

enum class UnitType{
    Celsius,
    Fahrenheit,
}

enum class DataSource{
    ECMWF,
    NOAA,
    DWD,
    METEO_FRANCE,
    KNMI,
    METEO_SWISS,
}

enum class WeatherModel{
    IFS_AIFS,
    GFS_HRRR,
    ICON,
    ARPEGE_AROME,
    HARMONIE,
    ICON_CH,
}
