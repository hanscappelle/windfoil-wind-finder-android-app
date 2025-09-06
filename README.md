# Wind Forecast App

An overview of wind speed forecasts for given (favorites) locations 
for the coming 10 days (configurable). Plus a detail view of the hourly 
forecast for each given location.

![screenshot](release/Screenshot_20250905_230546.png)
![screenshot](release/Screenshot_20250905_230537.png)

## How to Use

### Adding locations

You can add locations by sharing text in format: `lat,lng,locationName` 
with locationName being optional. For example: 

```
50.85045, 4.34878, Brussels BE
```

If you use Google Maps for example to pick a location you can drop a pin
anywhere and it should give you a share option to share these coordinates. 

Note that if the pin resolves as a known location it tries to share as a 
Google maps link instead which won't work. Alternative option is to share
from any text based app where you enter in the above format. 

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

# Version History

## 0.1.0

Initial app release

# TODO

## Features

- allow for easy location input when adding locations, pick from map in app
- add information about data source for example
- improve UI (more graphics and colours)
- show weather as an icon, see wmo weather code
- allow for custom colours, hours, warnings, thresholds, ...
- add about information
- explain how to add locations, also in app
- allow for different units
- on removing ignored hours add a confirmation dialog
- update store listing
- app icon

## Bugs

- fix system bar colors lost
- fix missing translations
- on rotation detail is shown again (intent data)
- back navigation isn't working as expected
- depending on system language locations can be shared as 12,1212121, 12,121212 breaking
- delete locations isn't always working

# Resources

## Dependencies

retrofit for network calls: https://square.github.io/retrofit/

also: https://medium.com/@pritam.karmahapatra/retrofit-in-android-with-kotlin-9af9f66a54a8

weather forecast api: https://open-meteo.com/en/docs/ecmwf-api

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
