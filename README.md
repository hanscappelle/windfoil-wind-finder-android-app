# Wind Forecast App

An overview of wind speed forecasts for given (favorite) locations 
for the coming 10 days (configurable). Plus a detail view of the hourly 
forecast for each given location.

![app feature image](release/NIacapvf6iTa_1024_500.png)

Available from Google Play: https://play.google.com/store/apps/details?id=be.hcpl.android.speedrecords
The weather model and some of the settings are configurable to show only the information you need. 

<img width="320" src="release/Screenshot_20250920_105428.png"/> <img width="320" src="release/Screenshot_20250920_110446.png"/>

## How to Use

### Adding locations

You can add locations by sharing text in format: `lat, lng, locationName` 
with locationName being optional. The separator is important: `, ` For example: 

```
50.85045, 4.34878, Brussels BE
```

If you use Google Maps for example to pick a location you can drop a pin
anywhere and it should give you a share option to share these coordinates. 

Note that if the pin resolves as a known location it tries to share as a 
Google maps link instead which won't work. Alternative option is to share
from any text based app where you enter in the above format. 

Video showing this procedure:

![video](release/screen-20250906-175206.mp4)

### Exploring Forecast

For each added location the daily forecast for the coming 10 days is shown 
on the first screen. I will make the number of days a configurable option.
Showing values are:

```
day & date | min & max wind speed | min & max temp 
```

When picking a day it will navigate to a detail screen. Format used there is:

```
hour in day | wind speed & gusts | wind direction | cloud cover & precipitation | air temperature 
```

From this screen you can always narrow down the number of hours that should show.
If you later want to bring back some hours you can use the refresh icon on top screen.

### Changing Configuration & Data Refresh

On top of the main screen you can see what the current configuration is. 
What weather model is fetched, what threshold is used to mark wind and the
number of days in the future data was retrieved.

You can change these values by tapping on them and you'll see them increment up
to the top value and then decrease to the lower limit set in app. Once you've
reached the desired value you'll have to pull down on the list of locations 
to refresh with these new settings. 

This manual refresh was added to reduce the number of requests to the public
weather API. And it will also help people with bad connection that need to 
fetch their data up front and not change it on every action in the app. 

## Version History

### 1.2

- added many more weather models
- added indication of model resolution and origin
- restored some dutch literals
- fixed pull to refresh after moving to grid

### 1.1

- on detail in landscape show 2 columns of data at once
- for landscape allow comparing weather models

### 1.0

- simplify versioning
- optimize landscape layout removing graphic header

### 0.12.0

- layout fix for dutch version with 100% cloud coverage
- fixed after renaming cached data was no longer found

### 0.11.0

- new app icon
- graphics in header

### 0.10.0

- better marking of high wind speed values
- improve dark theme (revert to black)

### 0.9.1

- bugfix loading detail view from cached data

### 0.9.0

- coloured theme added, created with https://material-foundation.github.io/material-theme-builder/
- more layout optimalisations
- cache last weather information details
- do not trigger full data refresh on rename

### 0.8.0

- Dutch translations

### 0.7.0

- added about info to app
- layout improvements
- rotate arrow for wind direction

### 0.6.0

- set number of days to fetch in the future
- configured threshold for marking values
- moved settings to a table on top
- changed icons on top to info dialogs

### 0.5.0

- layout improvements
- render current settings on overview screen
- allow for changing data source, 5 models: 
  - ECMWF("ecmwf_ifs025", WeatherModel.IFS_AIFS),
  - NOAA("gfs_seamless", WeatherModel.GFS_HRRR),
  - DWD("icon_seamless", WeatherModel.ICON),
  - METEO_FRANCE("meteofrance_seamless", WeatherModel.ARPEGE_AROME),
  - KNMI("knmi_seamless", WeatherModel.HARMONIE),

### 0.4.0

- show error when parsing location failed
- added support for fahrenheit
- also show name of day in detail view

### 0.3.0

- icons for weather indication
- show wind direction with icon
- added pull to refresh
- show error on problems

### 0.2.0

- improved app icon
- layout improvements
- bugfix dropping hours from detail
- bugfix navigation on rotation
- fix system bar colors

### 0.1.1

- fixed back handling
- add selected date on top for detail view
- fix adding locations in other formats
- added confirmation before restoring all hours

### 0.1.0

Initial app release

## TODO

### Features & Bugs 

- (feature) allow for easy location input from in app using location from FAB
- (opt) config for how many days in the past
- (feature) Implement notifications
- (bug) on missing data in detail no clear indication on how to navigate back
- (feature) show date of when data was fetched to indicate a refresh is needed
- (bug) on detail view indicate what model is shown (currently always MAIN)

# Resources

For release sign with v1 key
And bundle as APK for now

## Dependencies

retrofit for network calls: https://square.github.io/retrofit/

see also: https://medium.com/@pritam.karmahapatra/retrofit-in-android-with-kotlin-9af9f66a54a8

weather forecast api: https://open-meteo.com/en/docs/ecmwf-api

weather icons from https://openweathermap.org

google play store art created with: https://hotpot.ai/design/google-play-feature-graphic?id=34

logo with: https://hotpot.ai/logo-generator/create and https://romannurik.github.io/AndroidAssetStudio/

pull to refresh implementation: https://developer.android.com/develop/ui/compose/components/pull-to-refresh

## General Info

wind speed at 10 vs 100m; altitude that is, see more info at https://www.nature.com/articles/s41598-025-88295-8

info on using dialogs in compose https://stackoverflow.com/questions/68852110/show-custom-alert-dialog-in-jetpack-compose

Some Data sources
- "ecmwf" European Centre for Medium-Range Weather Forecasts, global
- "noaa" The National Oceanic and Atmospheric Administration, global except some smaller places

## WMO Weather Code

General information on these codes: 
https://www.nodc.noaa.gov/archive/arc0021/0002199/1.1/data/0-data/HTML/WMO-CODE/WMO4677.HTM

Example of mapping these codes to descriptions and images:
https://gist.github.com/stellasphere/9490c195ed2b53c707087c8c2db4ec0c

## Available "free" wind API

An overview of the weather API that I checked. 

### open-meteo.com

https://open-meteo.com/en/docs/ecmwf-api

Similar providers listed as stormglass.io api but opensource and free.

example request:

https://api.open-meteo.com/v1/forecast?latitude=50.85045&longitude=4.34878&hourly=temperature_2m,apparent_temperature,precipitation,rain,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m,wind_gusts_10m,wind_speed_100m,wind_direction_100m,surface_temperature&models=ecmwf_ifs025&wind_speed_unit=kn&start_date=2025-08-27&end_date=2025-09-10

simplified:

https://api.open-meteo.com/v1/forecast?latitude=50.85045&longitude=4.34878&hourly=temperature_2m,precipitation,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m,wind_gusts_10m&models=ecmwf_ifs025&wind_speed_unit=kn&start_date=2025-08-27&end_date=2025-09-10

execute for example response or see [example json](attachment:release/example-response.json)

### openweathermap.org

https://openweathermap.org/api

Also free, but subscription based, first 1k calls per day for free + 0.0014 EUR per next call.

### weatherapi.com

https://www.weatherapi.com/pricing.aspx

See link for pricing info.

### meteoblue.com

https://content.meteoblue.com/en/business-solutions/weather-apis/forecast-api

### stormglass.io

https://docs.stormglass.io/#/authentication

10 requests per day are free (too limited for actual use, even personal only). 
Pricing plans are per month and quite expensive, starting at 19 EUR/m for 500 requests.
