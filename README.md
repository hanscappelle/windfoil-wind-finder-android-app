# Wind Forecast App

An overview of wind forecast for given (favorites) location for the coming 10 days
plus a detail view of the hourly forecast for each given location

# Resources

## Dependencies

retrofit for network calls
https://square.github.io/retrofit/
https://medium.com/@pritam.karmahapatra/retrofit-in-android-with-kotlin-9af9f66a54a8

## General Info

wind speed at 10 vs 100m; altitude that is, see more info at https://www.nature.com/articles/s41598-025-88295-8

lat lng for testing; Brussels: lat=50.85045&lng=4.34878

info on using dialogs in compose https://stackoverflow.com/questions/68852110/show-custom-alert-dialog-in-jetpack-compose

## wmo weather code

https://www.nodc.noaa.gov/archive/arc0021/0002199/1.1/data/0-data/HTML/WMO-CODE/WMO4677.HTM

example of mapping these codes to descriptions and images
https://gist.github.com/stellasphere/9490c195ed2b53c707087c8c2db4ec0c

## Available "free" wind API

### open-meteo.com

similar providers listed as stormglass.io api
opensource and free

documentation: https://open-meteo.com/en/docs/ecmwf-api

example request

https://api.open-meteo.com/v1/forecast?latitude=50.85045&longitude=4.34878&hourly=temperature_2m,apparent_temperature,precipitation,rain,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m,wind_gusts_10m,wind_speed_100m,wind_direction_100m,surface_temperature&models=ecmwf_ifs025&wind_speed_unit=kn&start_date=2025-08-27&end_date=2025-09-10

simplified

https://api.open-meteo.com/v1/forecast?latitude=50.85045&longitude=4.34878&hourly=temperature_2m,precipitation,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m,wind_gusts_10m&models=ecmwf_ifs025&wind_speed_unit=kn&start_date=2025-08-27&end_date=2025-09-10

execute for example response

### openweathermap.org

https://openweathermap.org/api

subscription based, 1k calls per day for free + 0.0014 EUR per next call

### weatherapi.com

https://www.weatherapi.com/pricing.aspx

### meteoblue.com

https://content.meteoblue.com/en/business-solutions/weather-apis/forecast-api

### stormglass.io

10 requests per day are free, that is too limited for actual use, even personal only
plus pricing plans are per month and quite expensive, starting at 19 EUR/m for 500 requests
documentation: https://docs.stormglass.io/#/authentication
available params: 

```
"You need to specifiy at least one parameter: 
airTemperature, airTemperature1000hpa, airTemperature100m, airTemperature200hpa, airTemperature500hpa, airTemperature800hpa, airTemperature80m, 
cloudCover, currentDirection, currentSpeed, dewPointTemperature, graupel, gust, humidity, iceCover, precipitation, pressure, 
rain, seaIceThickness, seaLevel, secondarySwellDirection, secondarySwellHeight, secondarySwellPeriod, snow, snowAlbedo, snowDepth, 
swellDirection, swellHeight, swellPeriod, visibility, 
waterTemperature, waveDirection, waveHeight, wavePeriod, 
windDirection, windDirection1000hpa, windDirection100m, windDirection200hpa, windDirection20m, windDirection30m, windDirection40m, windDirection500hpa, windDirection50m, windDirection800hpa, windDirection80m, 
windSpeed, windSpeed1000hpa, windSpeed100m, windSpeed200hpa, windSpeed20m, windSpeed30m, windSpeed40m, windSpeed500hpa, windSpeed50m, windSpeed800hpa, windSpeed80m, windWaveDirection, windWaveHeight, windWavePeriod"
```

The ones I need
```
airTemperature,waterTemperature,cloudCover,precipitation,swellDirection,windSpeed,windDirection,gust
```

Curl request (w/o api key)
```
curl --location 'https://api.stormglass.io/v2/weather/point?lat=50.85045&lng=4.34878&params=airTemperature%2CwaterTemperature%2CcloudCover%2Crain%2CswellDirection%2CwindSpeed%2CwindDirection' \
--header 'Authorization: API_KEY_HERE'
```

note that not all sources have all attributes

https://docs.stormglass.io/#/sources?id=attributes-per-source

Some more info on output

```
"windSpeed": {
                "dwd": 4.48,
                "ecmwf": 4.64,
                "ecmwf:aifs": 4.79,
                "noaa": 6.82,
                "sg": 4.64
            }
```
these are all different data sources, 
- "sg" is stormglass itself
- "ecmwf" European Centre for Medium-Range Weather Forecasts, global
- "noaa" The National Oceanic and Atmospheric Administration, global except some smaller places

