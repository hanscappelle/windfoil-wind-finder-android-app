# Wind Forecast App





# Resources

## Available "free" wind API

### stormglass.io

10 requests per day are free
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

Example request (with above params)

```
{
    "hours": [
        {
            "airTemperature": {
                "dwd": 16.53,
                "ecmwf": 15.89,
                "ecmwf:aifs": 16.75,
                "noaa": 16.75,
                "sg": 15.89
            },
            "cloudCover": {
                "dwd": 94.0,
                "noaa": 47.7,
                "sg": 94.0
            },
            "time": "2025-09-03T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.0,
                "sg": 16.0
            },
            "windDirection": {
                "dwd": 187.97,
                "ecmwf": 191.64,
                "ecmwf:aifs": 191.04,
                "noaa": 196.49,
                "sg": 191.64
            },
            "windSpeed": {
                "dwd": 4.48,
                "ecmwf": 4.64,
                "ecmwf:aifs": 4.79,
                "noaa": 6.82,
                "sg": 4.64
            }
        },
        {
            "airTemperature": {
                "dwd": 16.54,
                "ecmwf": 15.87,
                "ecmwf:aifs": 16.81,
                "noaa": 16.5,
                "sg": 15.87
            },
            "cloudCover": {
                "dwd": 92.43,
                "noaa": 57.63,
                "sg": 92.43
            },
            "time": "2025-09-03T01:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.78,
                "sg": 15.78
            },
            "windDirection": {
                "dwd": 196.5,
                "ecmwf": 195.05,
                "ecmwf:aifs": 191.22,
                "noaa": 198.42,
                "sg": 195.05
            },
            "windSpeed": {
                "dwd": 4.87,
                "ecmwf": 4.74,
                "ecmwf:aifs": 5.22,
                "noaa": 6.88,
                "sg": 4.74
            }
        },
        {
            "airTemperature": {
                "dwd": 16.73,
                "ecmwf": 15.86,
                "ecmwf:aifs": 16.88,
                "noaa": 16.26,
                "sg": 15.86
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 67.57,
                "sg": 100.0
            },
            "time": "2025-09-03T02:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.57,
                "sg": 15.57
            },
            "windDirection": {
                "dwd": 197.63,
                "ecmwf": 198.47,
                "ecmwf:aifs": 191.4,
                "noaa": 200.35,
                "sg": 198.47
            },
            "windSpeed": {
                "dwd": 4.9,
                "ecmwf": 4.85,
                "ecmwf:aifs": 5.65,
                "noaa": 6.93,
                "sg": 4.85
            }
        },
        {
            "airTemperature": {
                "dwd": 16.82,
                "ecmwf": 15.85,
                "ecmwf:aifs": 16.95,
                "noaa": 16.01,
                "sg": 15.85
            },
            "cloudCover": {
                "dwd": 95.73,
                "noaa": 77.5,
                "sg": 95.73
            },
            "time": "2025-09-03T03:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.35,
                "sg": 15.35
            },
            "windDirection": {
                "dwd": 203.59,
                "ecmwf": 201.88,
                "ecmwf:aifs": 191.58,
                "noaa": 202.28,
                "sg": 201.88
            },
            "windSpeed": {
                "dwd": 5.2,
                "ecmwf": 4.95,
                "ecmwf:aifs": 6.08,
                "noaa": 6.99,
                "sg": 4.95
            }
        },
        {
            "airTemperature": {
                "dwd": 16.71,
                "ecmwf": 15.74,
                "ecmwf:aifs": 17.02,
                "noaa": 16.28,
                "sg": 15.74
            },
            "cloudCover": {
                "dwd": 97.75,
                "noaa": 85.0,
                "sg": 97.75
            },
            "time": "2025-09-03T04:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.79,
                "sg": 15.79
            },
            "windDirection": {
                "dwd": 198.87,
                "ecmwf": 198.21,
                "ecmwf:aifs": 191.77,
                "noaa": 199.86,
                "sg": 198.21
            },
            "windSpeed": {
                "dwd": 5.09,
                "ecmwf": 5.35,
                "ecmwf:aifs": 6.51,
                "noaa": 6.9,
                "sg": 5.35
            }
        },
        {
            "airTemperature": {
                "dwd": 16.6,
                "ecmwf": 15.63,
                "ecmwf:aifs": 17.09,
                "noaa": 16.55,
                "sg": 15.63
            },
            "cloudCover": {
                "dwd": 99.44,
                "noaa": 92.5,
                "sg": 99.44
            },
            "time": "2025-09-03T05:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.23,
                "sg": 16.23
            },
            "windDirection": {
                "dwd": 197.42,
                "ecmwf": 194.54,
                "ecmwf:aifs": 191.95,
                "noaa": 197.44,
                "sg": 194.54
            },
            "windSpeed": {
                "dwd": 5.61,
                "ecmwf": 5.76,
                "ecmwf:aifs": 6.94,
                "noaa": 6.8,
                "sg": 5.76
            }
        },
        {
            "airTemperature": {
                "dwd": 16.64,
                "ecmwf": 15.52,
                "ecmwf:aifs": 17.15,
                "noaa": 16.82,
                "sg": 15.52
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-03T06:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.67,
                "sg": 16.67
            },
            "windDirection": {
                "dwd": 191.14,
                "ecmwf": 190.87,
                "ecmwf:aifs": 192.13,
                "noaa": 195.02,
                "sg": 190.87
            },
            "windSpeed": {
                "dwd": 6.14,
                "ecmwf": 6.16,
                "ecmwf:aifs": 7.37,
                "noaa": 6.71,
                "sg": 6.16
            }
        },
        {
            "airTemperature": {
                "dwd": 16.98,
                "ecmwf": 15.74,
                "ecmwf:aifs": 17.94,
                "noaa": 17.98,
                "sg": 15.74
            },
            "cloudCover": {
                "dwd": 91.75,
                "noaa": 89.57,
                "sg": 91.75
            },
            "time": "2025-09-03T07:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.42,
                "sg": 18.42
            },
            "windDirection": {
                "dwd": 188.22,
                "ecmwf": 189.4,
                "ecmwf:aifs": 193.0,
                "noaa": 198.01,
                "sg": 189.4
            },
            "windSpeed": {
                "dwd": 6.89,
                "ecmwf": 6.56,
                "ecmwf:aifs": 7.91,
                "noaa": 7.62,
                "sg": 6.56
            }
        },
        {
            "airTemperature": {
                "dwd": 17.7,
                "ecmwf": 15.97,
                "ecmwf:aifs": 18.72,
                "noaa": 19.14,
                "sg": 15.97
            },
            "cloudCover": {
                "dwd": 90.65,
                "noaa": 79.13,
                "sg": 90.65
            },
            "time": "2025-09-03T08:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.16,
                "sg": 20.16
            },
            "windDirection": {
                "dwd": 186.54,
                "ecmwf": 187.92,
                "ecmwf:aifs": 193.87,
                "noaa": 200.99,
                "sg": 187.92
            },
            "windSpeed": {
                "dwd": 7.08,
                "ecmwf": 6.95,
                "ecmwf:aifs": 8.45,
                "noaa": 8.52,
                "sg": 6.95
            }
        },
        {
            "airTemperature": {
                "dwd": 19.05,
                "ecmwf": 16.19,
                "ecmwf:aifs": 19.5,
                "noaa": 20.3,
                "sg": 16.19
            },
            "cloudCover": {
                "dwd": 95.78,
                "noaa": 68.7,
                "sg": 95.78
            },
            "time": "2025-09-03T09:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.91,
                "sg": 21.91
            },
            "windDirection": {
                "dwd": 209.04,
                "ecmwf": 186.45,
                "ecmwf:aifs": 194.74,
                "noaa": 203.98,
                "sg": 186.45
            },
            "windSpeed": {
                "dwd": 9.08,
                "ecmwf": 7.35,
                "ecmwf:aifs": 8.99,
                "noaa": 9.43,
                "sg": 7.35
            }
        },
        {
            "airTemperature": {
                "dwd": 20.37,
                "ecmwf": 17.04,
                "ecmwf:aifs": 20.29,
                "noaa": 21.55,
                "sg": 17.04
            },
            "cloudCover": {
                "dwd": 79.06,
                "noaa": 58.43,
                "sg": 79.06
            },
            "time": "2025-09-03T10:00:00+00:00",
            "waterTemperature": {
                "noaa": 23.59,
                "sg": 23.59
            },
            "windDirection": {
                "dwd": 190.43,
                "ecmwf": 189.69,
                "ecmwf:aifs": 195.6,
                "noaa": 201.39,
                "sg": 189.69
            },
            "windSpeed": {
                "dwd": 7.26,
                "ecmwf": 7.9,
                "ecmwf:aifs": 9.54,
                "noaa": 9.71,
                "sg": 7.9
            }
        },
        {
            "airTemperature": {
                "dwd": 20.83,
                "ecmwf": 17.89,
                "ecmwf:aifs": 21.07,
                "noaa": 22.81,
                "sg": 17.89
            },
            "cloudCover": {
                "dwd": 89.72,
                "noaa": 48.17,
                "sg": 89.72
            },
            "time": "2025-09-03T11:00:00+00:00",
            "waterTemperature": {
                "noaa": 25.28,
                "sg": 25.28
            },
            "windDirection": {
                "dwd": 196.46,
                "ecmwf": 192.94,
                "ecmwf:aifs": 196.47,
                "noaa": 198.81,
                "sg": 192.94
            },
            "windSpeed": {
                "dwd": 8.01,
                "ecmwf": 8.44,
                "ecmwf:aifs": 10.08,
                "noaa": 9.99,
                "sg": 8.44
            }
        },
        {
            "airTemperature": {
                "dwd": 21.63,
                "ecmwf": 18.75,
                "ecmwf:aifs": 21.85,
                "noaa": 24.06,
                "sg": 18.75
            },
            "cloudCover": {
                "dwd": 98.66,
                "noaa": 37.9,
                "sg": 98.66
            },
            "time": "2025-09-03T12:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.96,
                "sg": 26.96
            },
            "windDirection": {
                "dwd": 199.87,
                "ecmwf": 196.18,
                "ecmwf:aifs": 197.34,
                "noaa": 196.22,
                "sg": 196.18
            },
            "windSpeed": {
                "dwd": 9.69,
                "ecmwf": 8.99,
                "ecmwf:aifs": 10.62,
                "noaa": 10.27,
                "sg": 8.99
            }
        },
        {
            "airTemperature": {
                "dwd": 21.65,
                "ecmwf": 19.72,
                "ecmwf:aifs": 21.77,
                "noaa": 24.28,
                "sg": 19.72
            },
            "cloudCover": {
                "dwd": 99.34,
                "noaa": 26.93,
                "sg": 99.34
            },
            "time": "2025-09-03T13:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.69,
                "sg": 26.69
            },
            "windDirection": {
                "dwd": 192.81,
                "ecmwf": 197.46,
                "ecmwf:aifs": 198.93,
                "noaa": 199.57,
                "sg": 197.46
            },
            "windSpeed": {
                "dwd": 8.05,
                "ecmwf": 8.82,
                "ecmwf:aifs": 10.31,
                "noaa": 10.24,
                "sg": 8.82
            }
        },
        {
            "airTemperature": {
                "dwd": 20.81,
                "ecmwf": 20.7,
                "ecmwf:aifs": 21.69,
                "noaa": 24.49,
                "sg": 20.7
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 15.97,
                "sg": 100.0
            },
            "time": "2025-09-03T14:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.42,
                "sg": 26.42
            },
            "windDirection": {
                "dwd": 194.93,
                "ecmwf": 198.74,
                "ecmwf:aifs": 200.52,
                "noaa": 202.93,
                "sg": 198.74
            },
            "windSpeed": {
                "dwd": 7.85,
                "ecmwf": 8.66,
                "ecmwf:aifs": 10.01,
                "noaa": 10.2,
                "sg": 8.66
            }
        },
        {
            "airTemperature": {
                "dwd": 21.08,
                "ecmwf": 21.68,
                "ecmwf:aifs": 21.62,
                "noaa": 24.71,
                "sg": 21.68
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 5.0,
                "sg": 100.0
            },
            "time": "2025-09-03T15:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.15,
                "sg": 26.15
            },
            "windDirection": {
                "dwd": 196.04,
                "ecmwf": 200.02,
                "ecmwf:aifs": 202.11,
                "noaa": 206.28,
                "sg": 200.02
            },
            "windSpeed": {
                "dwd": 8.48,
                "ecmwf": 8.49,
                "ecmwf:aifs": 9.7,
                "noaa": 10.17,
                "sg": 8.49
            }
        },
        {
            "airTemperature": {
                "dwd": 21.21,
                "ecmwf": 21.11,
                "ecmwf:aifs": 21.54,
                "noaa": 23.6,
                "sg": 21.11
            },
            "cloudCover": {
                "dwd": 61.04,
                "noaa": 3.33,
                "sg": 61.04
            },
            "time": "2025-09-03T16:00:00+00:00",
            "waterTemperature": {
                "noaa": 24.09,
                "sg": 24.09
            },
            "windDirection": {
                "dwd": 202.53,
                "ecmwf": 200.66,
                "ecmwf:aifs": 203.71,
                "noaa": 206.31,
                "sg": 200.66
            },
            "windSpeed": {
                "dwd": 8.21,
                "ecmwf": 8.19,
                "ecmwf:aifs": 9.4,
                "noaa": 9.81,
                "sg": 8.19
            }
        },
        {
            "airTemperature": {
                "dwd": 21.32,
                "ecmwf": 20.54,
                "ecmwf:aifs": 21.46,
                "noaa": 22.48,
                "sg": 20.54
            },
            "cloudCover": {
                "dwd": 57.13,
                "noaa": 1.67,
                "sg": 57.13
            },
            "time": "2025-09-03T17:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.03,
                "sg": 22.03
            },
            "windDirection": {
                "dwd": 200.58,
                "ecmwf": 201.29,
                "ecmwf:aifs": 205.3,
                "noaa": 206.33,
                "sg": 201.29
            },
            "windSpeed": {
                "dwd": 7.19,
                "ecmwf": 7.88,
                "ecmwf:aifs": 9.09,
                "noaa": 9.44,
                "sg": 7.88
            }
        },
        {
            "airTemperature": {
                "dwd": 20.65,
                "ecmwf": 19.98,
                "ecmwf:aifs": 21.38,
                "noaa": 21.37,
                "sg": 19.98
            },
            "cloudCover": {
                "dwd": 63.1,
                "noaa": 0.0,
                "sg": 63.1
            },
            "time": "2025-09-03T18:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.97,
                "sg": 19.97
            },
            "windDirection": {
                "dwd": 200.18,
                "ecmwf": 201.93,
                "ecmwf:aifs": 206.89,
                "noaa": 206.36,
                "sg": 201.93
            },
            "windSpeed": {
                "dwd": 6.68,
                "ecmwf": 7.58,
                "ecmwf:aifs": 8.79,
                "noaa": 9.08,
                "sg": 7.58
            }
        },
        {
            "airTemperature": {
                "dwd": 19.9,
                "ecmwf": 19.41,
                "ecmwf:aifs": 20.75,
                "noaa": 20.99,
                "sg": 19.41
            },
            "cloudCover": {
                "dwd": 43.5,
                "noaa": 0.0,
                "sg": 43.5
            },
            "time": "2025-09-03T19:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.74,
                "sg": 19.74
            },
            "windDirection": {
                "dwd": 199.55,
                "ecmwf": 207.05,
                "ecmwf:aifs": 210.36,
                "noaa": 210.93,
                "sg": 207.05
            },
            "windSpeed": {
                "dwd": 6.51,
                "ecmwf": 6.84,
                "ecmwf:aifs": 8.21,
                "noaa": 8.63,
                "sg": 6.84
            }
        },
        {
            "airTemperature": {
                "dwd": 19.82,
                "ecmwf": 18.85,
                "ecmwf:aifs": 20.12,
                "noaa": 20.6,
                "sg": 18.85
            },
            "cloudCover": {
                "dwd": 90.5,
                "noaa": 0.0,
                "sg": 90.5
            },
            "time": "2025-09-03T20:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.52,
                "sg": 19.52
            },
            "windDirection": {
                "dwd": 206.43,
                "ecmwf": 212.18,
                "ecmwf:aifs": 213.82,
                "noaa": 215.5,
                "sg": 212.18
            },
            "windSpeed": {
                "dwd": 6.73,
                "ecmwf": 6.1,
                "ecmwf:aifs": 7.62,
                "noaa": 8.19,
                "sg": 6.1
            }
        },
        {
            "airTemperature": {
                "dwd": 19.59,
                "ecmwf": 18.29,
                "ecmwf:aifs": 19.49,
                "noaa": 20.22,
                "sg": 18.29
            },
            "cloudCover": {
                "dwd": 89.83,
                "noaa": 0.0,
                "sg": 89.83
            },
            "time": "2025-09-03T21:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.29,
                "sg": 19.29
            },
            "windDirection": {
                "dwd": 219.93,
                "ecmwf": 217.3,
                "ecmwf:aifs": 217.29,
                "noaa": 220.07,
                "sg": 217.3
            },
            "windSpeed": {
                "dwd": 6.53,
                "ecmwf": 5.36,
                "ecmwf:aifs": 7.04,
                "noaa": 7.74,
                "sg": 5.36
            }
        },
        {
            "airTemperature": {
                "dwd": 18.84,
                "ecmwf": 17.86,
                "ecmwf:aifs": 18.86,
                "noaa": 19.83,
                "sg": 17.86
            },
            "cloudCover": {
                "dwd": 82.73,
                "noaa": 0.23,
                "sg": 82.73
            },
            "time": "2025-09-03T22:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.1,
                "sg": 19.1
            },
            "windDirection": {
                "dwd": 235.52,
                "ecmwf": 217.9,
                "ecmwf:aifs": 220.76,
                "noaa": 226.39,
                "sg": 217.9
            },
            "windSpeed": {
                "dwd": 5.93,
                "ecmwf": 5.04,
                "ecmwf:aifs": 6.45,
                "noaa": 7.37,
                "sg": 5.04
            }
        },
        {
            "airTemperature": {
                "dwd": 18.24,
                "ecmwf": 17.44,
                "ecmwf:aifs": 18.23,
                "noaa": 19.44,
                "sg": 17.44
            },
            "cloudCover": {
                "dwd": 76.72,
                "noaa": 0.47,
                "sg": 76.72
            },
            "time": "2025-09-03T23:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.91,
                "sg": 18.91
            },
            "windDirection": {
                "dwd": 232.96,
                "ecmwf": 218.51,
                "ecmwf:aifs": 224.22,
                "noaa": 232.72,
                "sg": 218.51
            },
            "windSpeed": {
                "dwd": 5.95,
                "ecmwf": 4.71,
                "ecmwf:aifs": 5.87,
                "noaa": 6.99,
                "sg": 4.71
            }
        },
        {
            "airTemperature": {
                "dwd": 17.43,
                "ecmwf": 17.01,
                "ecmwf:aifs": 17.6,
                "noaa": 19.05,
                "sg": 17.01
            },
            "cloudCover": {
                "dwd": 61.57,
                "noaa": 0.7,
                "sg": 61.57
            },
            "time": "2025-09-04T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.72,
                "sg": 18.72
            },
            "windDirection": {
                "dwd": 225.61,
                "ecmwf": 219.11,
                "ecmwf:aifs": 227.69,
                "noaa": 239.04,
                "sg": 219.11
            },
            "windSpeed": {
                "dwd": 5.46,
                "ecmwf": 4.39,
                "ecmwf:aifs": 5.28,
                "noaa": 6.62,
                "sg": 4.39
            }
        },
        {
            "airTemperature": {
                "dwd": 16.45,
                "ecmwf": 16.78,
                "ecmwf:aifs": 17.26,
                "noaa": 17.88,
                "sg": 16.78
            },
            "cloudCover": {
                "dwd": 30.72,
                "noaa": 2.13,
                "sg": 30.72
            },
            "time": "2025-09-04T01:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.33,
                "sg": 17.33
            },
            "windDirection": {
                "dwd": 220.49,
                "ecmwf": 221.35,
                "ecmwf:aifs": 222.91,
                "noaa": 233.23,
                "sg": 221.35
            },
            "windSpeed": {
                "dwd": 4.57,
                "ecmwf": 4.29,
                "ecmwf:aifs": 5.16,
                "noaa": 6.15,
                "sg": 4.29
            }
        },
        {
            "airTemperature": {
                "dwd": 15.45,
                "ecmwf": 16.54,
                "ecmwf:aifs": 16.92,
                "noaa": 16.7,
                "sg": 16.54
            },
            "cloudCover": {
                "dwd": 21.17,
                "noaa": 3.57,
                "sg": 21.17
            },
            "time": "2025-09-04T02:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.95,
                "sg": 15.95
            },
            "windDirection": {
                "dwd": 213.1,
                "ecmwf": 223.59,
                "ecmwf:aifs": 218.13,
                "noaa": 227.43,
                "sg": 223.59
            },
            "windSpeed": {
                "dwd": 4.04,
                "ecmwf": 4.2,
                "ecmwf:aifs": 5.03,
                "noaa": 5.69,
                "sg": 4.2
            }
        },
        {
            "airTemperature": {
                "dwd": 14.78,
                "ecmwf": 16.3,
                "ecmwf:aifs": 16.58,
                "noaa": 15.52,
                "sg": 16.3
            },
            "cloudCover": {
                "dwd": 36.68,
                "noaa": 5.0,
                "sg": 36.68
            },
            "time": "2025-09-04T03:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.57,
                "sg": 14.57
            },
            "windDirection": {
                "dwd": 203.7,
                "ecmwf": 225.83,
                "ecmwf:aifs": 213.35,
                "noaa": 221.62,
                "sg": 225.83
            },
            "windSpeed": {
                "dwd": 3.65,
                "ecmwf": 4.1,
                "ecmwf:aifs": 4.91,
                "noaa": 5.22,
                "sg": 4.1
            }
        },
        {
            "airTemperature": {
                "dwd": 14.51,
                "ecmwf": 15.75,
                "ecmwf:aifs": 16.24,
                "noaa": 15.42,
                "sg": 15.75
            },
            "cloudCover": {
                "dwd": 51.21,
                "noaa": 34.5,
                "sg": 51.21
            },
            "time": "2025-09-04T04:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.67,
                "sg": 14.67
            },
            "windDirection": {
                "dwd": 190.83,
                "ecmwf": 215.94,
                "ecmwf:aifs": 208.56,
                "noaa": 215.65,
                "sg": 215.94
            },
            "windSpeed": {
                "dwd": 3.36,
                "ecmwf": 4.01,
                "ecmwf:aifs": 4.79,
                "noaa": 4.86,
                "sg": 4.01
            }
        },
        {
            "airTemperature": {
                "dwd": 14.51,
                "ecmwf": 15.19,
                "ecmwf:aifs": 15.89,
                "noaa": 15.32,
                "sg": 15.19
            },
            "cloudCover": {
                "dwd": 38.09,
                "noaa": 64.0,
                "sg": 38.09
            },
            "time": "2025-09-04T05:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.76,
                "sg": 14.76
            },
            "windDirection": {
                "dwd": 193.25,
                "ecmwf": 206.05,
                "ecmwf:aifs": 203.78,
                "noaa": 209.69,
                "sg": 206.05
            },
            "windSpeed": {
                "dwd": 3.82,
                "ecmwf": 3.92,
                "ecmwf:aifs": 4.66,
                "noaa": 4.49,
                "sg": 3.92
            }
        },
        {
            "airTemperature": {
                "dwd": 14.52,
                "ecmwf": 14.63,
                "ecmwf:aifs": 15.55,
                "noaa": 15.23,
                "sg": 14.63
            },
            "cloudCover": {
                "dwd": 48.11,
                "noaa": 93.5,
                "sg": 48.11
            },
            "time": "2025-09-04T06:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.85,
                "sg": 14.85
            },
            "windDirection": {
                "dwd": 190.99,
                "ecmwf": 196.16,
                "ecmwf:aifs": 199.0,
                "noaa": 203.72,
                "sg": 196.16
            },
            "windSpeed": {
                "dwd": 3.76,
                "ecmwf": 3.83,
                "ecmwf:aifs": 4.54,
                "noaa": 4.13,
                "sg": 3.83
            }
        },
        {
            "airTemperature": {
                "dwd": 15.27,
                "ecmwf": 14.77,
                "ecmwf:aifs": 16.47,
                "noaa": 16.81,
                "sg": 14.77
            },
            "cloudCover": {
                "dwd": 88.55,
                "noaa": 95.57,
                "sg": 88.55
            },
            "time": "2025-09-04T07:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.39,
                "sg": 17.39
            },
            "windDirection": {
                "dwd": 184.62,
                "ecmwf": 197.37,
                "ecmwf:aifs": 198.75,
                "noaa": 205.69,
                "sg": 197.37
            },
            "windSpeed": {
                "dwd": 4.27,
                "ecmwf": 4.57,
                "ecmwf:aifs": 4.91,
                "noaa": 4.89,
                "sg": 4.57
            }
        },
        {
            "airTemperature": {
                "dwd": 16.35,
                "ecmwf": 14.91,
                "ecmwf:aifs": 17.4,
                "noaa": 18.4,
                "sg": 14.91
            },
            "cloudCover": {
                "dwd": 86.04,
                "noaa": 97.63,
                "sg": 86.04
            },
            "time": "2025-09-04T08:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.93,
                "sg": 19.93
            },
            "windDirection": {
                "dwd": 190.56,
                "ecmwf": 198.58,
                "ecmwf:aifs": 198.49,
                "noaa": 207.65,
                "sg": 198.58
            },
            "windSpeed": {
                "dwd": 4.73,
                "ecmwf": 5.32,
                "ecmwf:aifs": 5.28,
                "noaa": 5.64,
                "sg": 5.32
            }
        },
        {
            "airTemperature": {
                "dwd": 17.49,
                "ecmwf": 15.06,
                "ecmwf:aifs": 18.32,
                "noaa": 19.99,
                "sg": 15.06
            },
            "cloudCover": {
                "dwd": 98.76,
                "noaa": 99.7,
                "sg": 98.76
            },
            "time": "2025-09-04T09:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.47,
                "sg": 22.47
            },
            "windDirection": {
                "dwd": 196.81,
                "ecmwf": 199.79,
                "ecmwf:aifs": 198.24,
                "noaa": 209.62,
                "sg": 199.79
            },
            "windSpeed": {
                "dwd": 5.33,
                "ecmwf": 6.06,
                "ecmwf:aifs": 5.64,
                "noaa": 6.4,
                "sg": 6.06
            }
        },
        {
            "airTemperature": {
                "dwd": 18.73,
                "ecmwf": 16.26,
                "ecmwf:aifs": 19.24,
                "noaa": 19.96,
                "sg": 16.26
            },
            "cloudCover": {
                "dwd": 95.02,
                "noaa": 88.7,
                "sg": 95.02
            },
            "time": "2025-09-04T10:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.76,
                "sg": 21.76
            },
            "windDirection": {
                "dwd": 197.97,
                "ecmwf": 202.02,
                "ecmwf:aifs": 197.99,
                "noaa": 206.86,
                "sg": 202.02
            },
            "windSpeed": {
                "dwd": 6.11,
                "ecmwf": 6.11,
                "ecmwf:aifs": 6.01,
                "noaa": 6.1,
                "sg": 6.11
            }
        },
        {
            "airTemperature": {
                "dwd": 19.24,
                "ecmwf": 17.47,
                "ecmwf:aifs": 20.16,
                "noaa": 19.94,
                "sg": 17.47
            },
            "cloudCover": {
                "dwd": 99.38,
                "noaa": 77.7,
                "sg": 99.38
            },
            "time": "2025-09-04T11:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.05,
                "sg": 21.05
            },
            "windDirection": {
                "dwd": 197.06,
                "ecmwf": 204.24,
                "ecmwf:aifs": 197.73,
                "noaa": 204.11,
                "sg": 204.24
            },
            "windSpeed": {
                "dwd": 5.31,
                "ecmwf": 6.15,
                "ecmwf:aifs": 6.38,
                "noaa": 5.79,
                "sg": 6.15
            }
        },
        {
            "airTemperature": {
                "dwd": 20.05,
                "ecmwf": 18.68,
                "ecmwf:aifs": 21.08,
                "noaa": 19.92,
                "sg": 18.68
            },
            "cloudCover": {
                "dwd": 98.32,
                "noaa": 66.7,
                "sg": 98.32
            },
            "time": "2025-09-04T12:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.34,
                "sg": 20.34
            },
            "windDirection": {
                "dwd": 197.77,
                "ecmwf": 206.47,
                "ecmwf:aifs": 197.48,
                "noaa": 201.35,
                "sg": 206.47
            },
            "windSpeed": {
                "dwd": 5.26,
                "ecmwf": 6.2,
                "ecmwf:aifs": 6.75,
                "noaa": 5.49,
                "sg": 6.2
            }
        },
        {
            "airTemperature": {
                "dwd": 20.88,
                "ecmwf": 18.89,
                "ecmwf:aifs": 20.45,
                "noaa": 20.57,
                "sg": 18.89
            },
            "cloudCover": {
                "dwd": 77.29,
                "noaa": 77.8,
                "sg": 77.29
            },
            "time": "2025-09-04T13:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.86,
                "sg": 20.86
            },
            "windDirection": {
                "dwd": 201.37,
                "ecmwf": 208.6,
                "ecmwf:aifs": 200.56,
                "noaa": 201.37,
                "sg": 208.6
            },
            "windSpeed": {
                "dwd": 5.4,
                "ecmwf": 5.95,
                "ecmwf:aifs": 6.38,
                "noaa": 5.86,
                "sg": 5.95
            }
        },
        {
            "airTemperature": {
                "dwd": 21.09,
                "ecmwf": 19.1,
                "ecmwf:aifs": 19.81,
                "noaa": 21.23,
                "sg": 19.1
            },
            "cloudCover": {
                "dwd": 86.72,
                "noaa": 88.9,
                "sg": 86.72
            },
            "time": "2025-09-04T14:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.38,
                "sg": 21.38
            },
            "windDirection": {
                "dwd": 205.62,
                "ecmwf": 210.73,
                "ecmwf:aifs": 203.64,
                "noaa": 201.39,
                "sg": 210.73
            },
            "windSpeed": {
                "dwd": 5.43,
                "ecmwf": 5.71,
                "ecmwf:aifs": 6.02,
                "noaa": 6.23,
                "sg": 5.71
            }
        },
        {
            "airTemperature": {
                "dwd": 20.01,
                "ecmwf": 19.32,
                "ecmwf:aifs": 19.17,
                "noaa": 21.88,
                "sg": 19.32
            },
            "cloudCover": {
                "dwd": 98.56,
                "noaa": 100.0,
                "sg": 98.56
            },
            "time": "2025-09-04T15:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.9,
                "sg": 21.9
            },
            "windDirection": {
                "dwd": 215.84,
                "ecmwf": 212.86,
                "ecmwf:aifs": 206.72,
                "noaa": 201.41,
                "sg": 212.86
            },
            "windSpeed": {
                "dwd": 4.38,
                "ecmwf": 5.46,
                "ecmwf:aifs": 5.65,
                "noaa": 6.6,
                "sg": 5.46
            }
        },
        {
            "airTemperature": {
                "dwd": 19.1,
                "ecmwf": 18.54,
                "ecmwf:aifs": 18.54,
                "noaa": 20.77,
                "sg": 18.54
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-04T16:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.47,
                "sg": 20.47
            },
            "windDirection": {
                "dwd": 205.77,
                "ecmwf": 217.28,
                "ecmwf:aifs": 209.81,
                "noaa": 204.15,
                "sg": 217.28
            },
            "windSpeed": {
                "dwd": 4.11,
                "ecmwf": 4.71,
                "ecmwf:aifs": 5.28,
                "noaa": 6.07,
                "sg": 4.71
            }
        },
        {
            "airTemperature": {
                "dwd": 18.35,
                "ecmwf": 17.75,
                "ecmwf:aifs": 17.9,
                "noaa": 19.66,
                "sg": 17.75
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-04T17:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.04,
                "sg": 19.04
            },
            "windDirection": {
                "dwd": 209.99,
                "ecmwf": 221.71,
                "ecmwf:aifs": 212.89,
                "noaa": 206.9,
                "sg": 221.71
            },
            "windSpeed": {
                "dwd": 3.97,
                "ecmwf": 3.96,
                "ecmwf:aifs": 4.92,
                "noaa": 5.54,
                "sg": 3.96
            }
        },
        {
            "airTemperature": {
                "dwd": 17.06,
                "ecmwf": 16.97,
                "ecmwf:aifs": 17.27,
                "noaa": 18.55,
                "sg": 16.97
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-04T18:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.61,
                "sg": 17.61
            },
            "windDirection": {
                "dwd": 201.67,
                "ecmwf": 226.13,
                "ecmwf:aifs": 215.97,
                "noaa": 209.64,
                "sg": 226.13
            },
            "windSpeed": {
                "dwd": 3.36,
                "ecmwf": 3.21,
                "ecmwf:aifs": 4.55,
                "noaa": 5.01,
                "sg": 3.21
            }
        },
        {
            "airTemperature": {
                "dwd": 16.3,
                "ecmwf": 16.28,
                "ecmwf:aifs": 16.67,
                "noaa": 17.53,
                "sg": 16.28
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-04T19:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.49,
                "sg": 16.49
            },
            "windDirection": {
                "dwd": 201.46,
                "ecmwf": 227.02,
                "ecmwf:aifs": 218.58,
                "noaa": 218.31,
                "sg": 227.02
            },
            "windSpeed": {
                "dwd": 2.96,
                "ecmwf": 3.34,
                "ecmwf:aifs": 4.58,
                "noaa": 4.78,
                "sg": 3.34
            }
        },
        {
            "airTemperature": {
                "dwd": 16.04,
                "ecmwf": 15.58,
                "ecmwf:aifs": 16.07,
                "noaa": 16.51,
                "sg": 15.58
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-04T20:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.38,
                "sg": 15.38
            },
            "windDirection": {
                "dwd": 210.46,
                "ecmwf": 227.92,
                "ecmwf:aifs": 221.18,
                "noaa": 226.97,
                "sg": 227.92
            },
            "windSpeed": {
                "dwd": 3.39,
                "ecmwf": 3.48,
                "ecmwf:aifs": 4.6,
                "noaa": 4.54,
                "sg": 3.48
            }
        },
        {
            "airTemperature": {
                "dwd": 15.28,
                "ecmwf": 14.88,
                "ecmwf:aifs": 15.48,
                "noaa": 15.48,
                "sg": 14.88
            },
            "cloudCover": {
                "dwd": 40.48,
                "noaa": 100.0,
                "sg": 40.48
            },
            "time": "2025-09-04T21:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.27,
                "sg": 14.27
            },
            "windDirection": {
                "dwd": 212.25,
                "ecmwf": 228.81,
                "ecmwf:aifs": 223.79,
                "noaa": 235.64,
                "sg": 228.81
            },
            "windSpeed": {
                "dwd": 3.54,
                "ecmwf": 3.61,
                "ecmwf:aifs": 4.63,
                "noaa": 4.31,
                "sg": 3.61
            }
        },
        {
            "airTemperature": {
                "dwd": 14.77,
                "ecmwf": 14.39,
                "ecmwf:aifs": 14.88,
                "noaa": 14.98,
                "sg": 14.39
            },
            "cloudCover": {
                "dwd": 48.21,
                "noaa": 66.67,
                "sg": 48.21
            },
            "time": "2025-09-04T22:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.81,
                "sg": 13.81
            },
            "windDirection": {
                "dwd": 218.51,
                "ecmwf": 228.68,
                "ecmwf:aifs": 226.4,
                "noaa": 238.79,
                "sg": 228.68
            },
            "windSpeed": {
                "dwd": 3.58,
                "ecmwf": 3.66,
                "ecmwf:aifs": 4.66,
                "noaa": 4.44,
                "sg": 3.66
            }
        },
        {
            "airTemperature": {
                "dwd": 14.56,
                "ecmwf": 13.9,
                "ecmwf:aifs": 14.29,
                "noaa": 14.48,
                "sg": 13.9
            },
            "cloudCover": {
                "dwd": 52.41,
                "noaa": 33.33,
                "sg": 52.41
            },
            "time": "2025-09-04T23:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.35,
                "sg": 13.35
            },
            "windDirection": {
                "dwd": 214.95,
                "ecmwf": 228.56,
                "ecmwf:aifs": 229.0,
                "noaa": 241.95,
                "sg": 228.56
            },
            "windSpeed": {
                "dwd": 4.17,
                "ecmwf": 3.7,
                "ecmwf:aifs": 4.68,
                "noaa": 4.57,
                "sg": 3.7
            }
        },
        {
            "airTemperature": {
                "dwd": 14.09,
                "ecmwf": 13.41,
                "ecmwf:aifs": 13.69,
                "noaa": 13.98,
                "sg": 13.41
            },
            "cloudCover": {
                "dwd": 60.19,
                "noaa": 0.0,
                "sg": 60.19
            },
            "time": "2025-09-05T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 12.9,
                "sg": 12.9
            },
            "windDirection": {
                "dwd": 222.15,
                "ecmwf": 228.43,
                "ecmwf:aifs": 231.61,
                "noaa": 245.1,
                "sg": 228.43
            },
            "windSpeed": {
                "dwd": 4.62,
                "ecmwf": 3.75,
                "ecmwf:aifs": 4.71,
                "noaa": 4.7,
                "sg": 3.75
            }
        },
        {
            "airTemperature": {
                "dwd": 13.88,
                "ecmwf": 13.12,
                "ecmwf:aifs": 13.56,
                "noaa": 13.53,
                "sg": 13.12
            },
            "cloudCover": {
                "dwd": 55.98,
                "noaa": 0.0,
                "sg": 55.98
            },
            "time": "2025-09-05T01:00:00+00:00",
            "waterTemperature": {
                "noaa": 12.45,
                "sg": 12.45
            },
            "windDirection": {
                "dwd": 226.55,
                "ecmwf": 227.99,
                "ecmwf:aifs": 231.39,
                "noaa": 237.79,
                "sg": 227.99
            },
            "windSpeed": {
                "dwd": 4.64,
                "ecmwf": 3.69,
                "ecmwf:aifs": 4.65,
                "noaa": 4.39,
                "sg": 3.69
            }
        },
        {
            "airTemperature": {
                "dwd": 13.35,
                "ecmwf": 12.82,
                "ecmwf:aifs": 13.43,
                "noaa": 13.08,
                "sg": 12.82
            },
            "cloudCover": {
                "dwd": 64.13,
                "noaa": 0.0,
                "sg": 64.13
            },
            "time": "2025-09-05T02:00:00+00:00",
            "waterTemperature": {
                "noaa": 12.0,
                "sg": 12.0
            },
            "windDirection": {
                "dwd": 227.98,
                "ecmwf": 227.55,
                "ecmwf:aifs": 231.17,
                "noaa": 230.48,
                "sg": 227.55
            },
            "windSpeed": {
                "dwd": 4.15,
                "ecmwf": 3.64,
                "ecmwf:aifs": 4.58,
                "noaa": 4.09,
                "sg": 3.64
            }
        },
        {
            "airTemperature": {
                "dwd": 13.18,
                "ecmwf": 12.53,
                "ecmwf:aifs": 13.31,
                "noaa": 12.63,
                "sg": 12.53
            },
            "cloudCover": {
                "dwd": 72.32,
                "noaa": 0.0,
                "sg": 72.32
            },
            "time": "2025-09-05T03:00:00+00:00",
            "waterTemperature": {
                "noaa": 11.55,
                "sg": 11.55
            },
            "windDirection": {
                "dwd": 230.53,
                "ecmwf": 227.11,
                "ecmwf:aifs": 230.94,
                "noaa": 223.17,
                "sg": 227.11
            },
            "windSpeed": {
                "dwd": 3.89,
                "ecmwf": 3.58,
                "ecmwf:aifs": 4.52,
                "noaa": 3.78,
                "sg": 3.58
            }
        },
        {
            "airTemperature": {
                "dwd": 13.02,
                "ecmwf": 12.37,
                "ecmwf:aifs": 13.18,
                "noaa": 12.62,
                "sg": 12.37
            },
            "cloudCover": {
                "dwd": 75.58,
                "noaa": 0.0,
                "sg": 75.58
            },
            "time": "2025-09-05T04:00:00+00:00",
            "waterTemperature": {
                "noaa": 11.8,
                "sg": 11.8
            },
            "windDirection": {
                "dwd": 229.29,
                "ecmwf": 225.41,
                "ecmwf:aifs": 230.72,
                "noaa": 227.27,
                "sg": 225.41
            },
            "windSpeed": {
                "dwd": 3.35,
                "ecmwf": 3.5,
                "ecmwf:aifs": 4.46,
                "noaa": 3.64,
                "sg": 3.5
            }
        },
        {
            "airTemperature": {
                "dwd": 12.94,
                "ecmwf": 12.2,
                "ecmwf:aifs": 13.05,
                "noaa": 12.61,
                "sg": 12.2
            },
            "cloudCover": {
                "dwd": 79.26,
                "noaa": 0.0,
                "sg": 79.26
            },
            "time": "2025-09-05T05:00:00+00:00",
            "waterTemperature": {
                "noaa": 12.06,
                "sg": 12.06
            },
            "windDirection": {
                "dwd": 221.58,
                "ecmwf": 223.72,
                "ecmwf:aifs": 230.5,
                "noaa": 231.38,
                "sg": 223.72
            },
            "windSpeed": {
                "dwd": 3.08,
                "ecmwf": 3.41,
                "ecmwf:aifs": 4.39,
                "noaa": 3.49,
                "sg": 3.41
            }
        },
        {
            "airTemperature": {
                "dwd": 13.21,
                "ecmwf": 12.04,
                "ecmwf:aifs": 12.92,
                "noaa": 12.6,
                "sg": 12.04
            },
            "cloudCover": {
                "dwd": 78.61,
                "noaa": 0.0,
                "sg": 78.61
            },
            "time": "2025-09-05T06:00:00+00:00",
            "waterTemperature": {
                "noaa": 12.32,
                "sg": 12.32
            },
            "windDirection": {
                "dwd": 216.6,
                "ecmwf": 222.02,
                "ecmwf:aifs": 230.28,
                "noaa": 235.48,
                "sg": 222.02
            },
            "windSpeed": {
                "dwd": 3.13,
                "ecmwf": 3.33,
                "ecmwf:aifs": 4.33,
                "noaa": 3.35,
                "sg": 3.33
            }
        },
        {
            "airTemperature": {
                "dwd": 13.98,
                "ecmwf": 12.19,
                "ecmwf:aifs": 14.07,
                "noaa": 14.63,
                "sg": 12.19
            },
            "cloudCover": {
                "dwd": 78.34,
                "noaa": 0.0,
                "sg": 78.34
            },
            "time": "2025-09-05T07:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.73,
                "sg": 15.73
            },
            "windDirection": {
                "dwd": 229.63,
                "ecmwf": 231.22,
                "ecmwf:aifs": 234.27,
                "noaa": 244.38,
                "sg": 231.22
            },
            "windSpeed": {
                "dwd": 3.96,
                "ecmwf": 3.54,
                "ecmwf:aifs": 4.31,
                "noaa": 3.68,
                "sg": 3.54
            }
        },
        {
            "airTemperature": {
                "dwd": 15.33,
                "ecmwf": 12.35,
                "ecmwf:aifs": 15.23,
                "noaa": 16.66,
                "sg": 12.35
            },
            "cloudCover": {
                "dwd": 58.66,
                "noaa": 0.0,
                "sg": 58.66
            },
            "time": "2025-09-05T08:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.15,
                "sg": 19.15
            },
            "windDirection": {
                "dwd": 241.94,
                "ecmwf": 240.42,
                "ecmwf:aifs": 238.26,
                "noaa": 253.28,
                "sg": 240.42
            },
            "windSpeed": {
                "dwd": 4.21,
                "ecmwf": 3.75,
                "ecmwf:aifs": 4.29,
                "noaa": 4.02,
                "sg": 3.75
            }
        },
        {
            "airTemperature": {
                "dwd": 16.68,
                "ecmwf": 12.5,
                "ecmwf:aifs": 16.39,
                "noaa": 18.69,
                "sg": 12.5
            },
            "cloudCover": {
                "dwd": 56.71,
                "noaa": 0.0,
                "sg": 56.71
            },
            "time": "2025-09-05T09:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.56,
                "sg": 22.56
            },
            "windDirection": {
                "dwd": 246.2,
                "ecmwf": 249.62,
                "ecmwf:aifs": 242.25,
                "noaa": 262.18,
                "sg": 249.62
            },
            "windSpeed": {
                "dwd": 4.46,
                "ecmwf": 3.96,
                "ecmwf:aifs": 4.27,
                "noaa": 4.35,
                "sg": 3.96
            }
        },
        {
            "airTemperature": {
                "dwd": 17.77,
                "ecmwf": 13.93,
                "ecmwf:aifs": 17.54,
                "noaa": 20.11,
                "sg": 13.93
            },
            "cloudCover": {
                "dwd": 51.24,
                "noaa": 7.07,
                "sg": 51.24
            },
            "time": "2025-09-05T10:00:00+00:00",
            "waterTemperature": {
                "noaa": 24.45,
                "sg": 24.45
            },
            "windDirection": {
                "dwd": 248.04,
                "ecmwf": 250.42,
                "ecmwf:aifs": 246.23,
                "noaa": 263.81,
                "sg": 250.42
            },
            "windSpeed": {
                "dwd": 4.51,
                "ecmwf": 3.99,
                "ecmwf:aifs": 4.26,
                "noaa": 4.21,
                "sg": 3.99
            }
        },
        {
            "airTemperature": {
                "dwd": 19.05,
                "ecmwf": 15.36,
                "ecmwf:aifs": 18.7,
                "noaa": 21.52,
                "sg": 15.36
            },
            "cloudCover": {
                "dwd": 46.27,
                "noaa": 14.13,
                "sg": 46.27
            },
            "time": "2025-09-05T11:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.33,
                "sg": 26.33
            },
            "windDirection": {
                "dwd": 252.12,
                "ecmwf": 251.22,
                "ecmwf:aifs": 250.22,
                "noaa": 265.44,
                "sg": 251.22
            },
            "windSpeed": {
                "dwd": 4.47,
                "ecmwf": 4.01,
                "ecmwf:aifs": 4.24,
                "noaa": 4.08,
                "sg": 4.01
            }
        },
        {
            "airTemperature": {
                "dwd": 19.84,
                "ecmwf": 16.79,
                "ecmwf:aifs": 19.85,
                "noaa": 22.94,
                "sg": 16.79
            },
            "cloudCover": {
                "dwd": 26.6,
                "noaa": 21.2,
                "sg": 26.6
            },
            "time": "2025-09-05T12:00:00+00:00",
            "waterTemperature": {
                "noaa": 28.22,
                "sg": 28.22
            },
            "windDirection": {
                "dwd": 254.67,
                "ecmwf": 252.02,
                "ecmwf:aifs": 254.21,
                "noaa": 267.07,
                "sg": 252.02
            },
            "windSpeed": {
                "dwd": 4.67,
                "ecmwf": 4.04,
                "ecmwf:aifs": 4.22,
                "noaa": 3.94,
                "sg": 4.04
            }
        },
        {
            "airTemperature": {
                "dwd": 20.44,
                "ecmwf": 17.56,
                "ecmwf:aifs": 19.58,
                "noaa": 22.87,
                "sg": 17.56
            },
            "cloudCover": {
                "dwd": 46.84,
                "noaa": 47.23,
                "sg": 46.84
            },
            "time": "2025-09-05T13:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.66,
                "sg": 26.66
            },
            "windDirection": {
                "dwd": 258.7,
                "ecmwf": 253.35,
                "ecmwf:aifs": 254.35,
                "noaa": 282.51,
                "sg": 253.35
            },
            "windSpeed": {
                "dwd": 4.33,
                "ecmwf": 3.83,
                "ecmwf:aifs": 3.77,
                "noaa": 4.1,
                "sg": 3.83
            }
        },
        {
            "airTemperature": {
                "dwd": 20.92,
                "ecmwf": 18.32,
                "ecmwf:aifs": 19.3,
                "noaa": 22.81,
                "sg": 18.32
            },
            "cloudCover": {
                "dwd": 62.26,
                "noaa": 73.27,
                "sg": 62.26
            },
            "time": "2025-09-05T14:00:00+00:00",
            "waterTemperature": {
                "noaa": 25.11,
                "sg": 25.11
            },
            "windDirection": {
                "dwd": 262.96,
                "ecmwf": 254.68,
                "ecmwf:aifs": 254.49,
                "noaa": 297.96,
                "sg": 254.68
            },
            "windSpeed": {
                "dwd": 4.24,
                "ecmwf": 3.63,
                "ecmwf:aifs": 3.32,
                "noaa": 4.25,
                "sg": 3.63
            }
        },
        {
            "airTemperature": {
                "dwd": 20.74,
                "ecmwf": 19.09,
                "ecmwf:aifs": 19.02,
                "noaa": 22.74,
                "sg": 19.09
            },
            "cloudCover": {
                "dwd": 35.71,
                "noaa": 99.3,
                "sg": 35.71
            },
            "time": "2025-09-05T15:00:00+00:00",
            "waterTemperature": {
                "noaa": 23.55,
                "sg": 23.55
            },
            "windDirection": {
                "dwd": 266.2,
                "ecmwf": 256.01,
                "ecmwf:aifs": 254.63,
                "noaa": 313.4,
                "sg": 256.01
            },
            "windSpeed": {
                "dwd": 3.59,
                "ecmwf": 3.42,
                "ecmwf:aifs": 2.87,
                "noaa": 4.41,
                "sg": 3.42
            }
        },
        {
            "airTemperature": {
                "dwd": 20.6,
                "ecmwf": 18.64,
                "ecmwf:aifs": 18.74,
                "noaa": 21.4,
                "sg": 18.64
            },
            "cloudCover": {
                "dwd": 35.11,
                "noaa": 73.2,
                "sg": 35.11
            },
            "time": "2025-09-05T16:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.52,
                "sg": 21.52
            },
            "windDirection": {
                "dwd": 267.95,
                "ecmwf": 251.61,
                "ecmwf:aifs": 254.77,
                "noaa": 326.47,
                "sg": 251.61
            },
            "windSpeed": {
                "dwd": 3.21,
                "ecmwf": 2.59,
                "ecmwf:aifs": 2.43,
                "noaa": 4.2,
                "sg": 2.59
            }
        },
        {
            "airTemperature": {
                "dwd": 19.92,
                "ecmwf": 18.2,
                "ecmwf:aifs": 18.47,
                "noaa": 20.06,
                "sg": 18.2
            },
            "cloudCover": {
                "dwd": 25.3,
                "noaa": 47.1,
                "sg": 25.3
            },
            "time": "2025-09-05T17:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.49,
                "sg": 19.49
            },
            "windDirection": {
                "dwd": 279.31,
                "ecmwf": 247.22,
                "ecmwf:aifs": 254.91,
                "noaa": 339.54,
                "sg": 247.22
            },
            "windSpeed": {
                "dwd": 2.15,
                "ecmwf": 1.75,
                "ecmwf:aifs": 1.98,
                "noaa": 3.98,
                "sg": 1.75
            }
        },
        {
            "airTemperature": {
                "dwd": 18.43,
                "ecmwf": 17.75,
                "ecmwf:aifs": 18.19,
                "noaa": 18.72,
                "sg": 17.75
            },
            "cloudCover": {
                "dwd": 56.53,
                "noaa": 21.0,
                "sg": 56.53
            },
            "time": "2025-09-05T18:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.46,
                "sg": 17.46
            },
            "windDirection": {
                "dwd": 277.62,
                "ecmwf": 242.82,
                "ecmwf:aifs": 255.05,
                "noaa": 352.61,
                "sg": 242.82
            },
            "windSpeed": {
                "dwd": 0.91,
                "ecmwf": 0.92,
                "ecmwf:aifs": 1.53,
                "noaa": 3.77,
                "sg": 0.92
            }
        },
        {
            "airTemperature": {
                "dwd": 17.19,
                "ecmwf": 16.73,
                "ecmwf:aifs": 17.06,
                "noaa": 17.11,
                "sg": 16.73
            },
            "cloudCover": {
                "dwd": 83.66,
                "noaa": 15.67,
                "sg": 83.66
            },
            "time": "2025-09-05T19:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.49,
                "sg": 15.49
            },
            "windDirection": {
                "dwd": 286.22,
                "ecmwf": 222.23,
                "ecmwf:aifs": 244.62,
                "noaa": 6.58,
                "sg": 222.23
            },
            "windSpeed": {
                "dwd": 0.73,
                "ecmwf": 0.93,
                "ecmwf:aifs": 1.5,
                "noaa": 3.03,
                "sg": 0.93
            }
        },
        {
            "airTemperature": {
                "dwd": 15.98,
                "ecmwf": 15.71,
                "ecmwf:aifs": 15.93,
                "noaa": 15.49,
                "sg": 15.71
            },
            "cloudCover": {
                "dwd": 44.65,
                "noaa": 10.33,
                "sg": 44.65
            },
            "time": "2025-09-05T20:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.52,
                "sg": 13.52
            },
            "windDirection": {
                "dwd": 345.63,
                "ecmwf": 201.65,
                "ecmwf:aifs": 234.19,
                "noaa": 20.56,
                "sg": 201.65
            },
            "windSpeed": {
                "dwd": 0.83,
                "ecmwf": 0.93,
                "ecmwf:aifs": 1.47,
                "noaa": 2.28,
                "sg": 0.93
            }
        },
        {
            "airTemperature": {
                "dwd": 15.12,
                "ecmwf": 14.68,
                "ecmwf:aifs": 14.8,
                "noaa": 13.88,
                "sg": 14.68
            },
            "cloudCover": {
                "dwd": 49.77,
                "noaa": 5.0,
                "sg": 49.77
            },
            "time": "2025-09-05T21:00:00+00:00",
            "waterTemperature": {
                "noaa": 11.55,
                "sg": 11.55
            },
            "windDirection": {
                "dwd": 50.53,
                "ecmwf": 181.06,
                "ecmwf:aifs": 223.76,
                "noaa": 34.53,
                "sg": 181.06
            },
            "windSpeed": {
                "dwd": 0.61,
                "ecmwf": 0.94,
                "ecmwf:aifs": 1.44,
                "noaa": 1.54,
                "sg": 0.94
            }
        },
        {
            "airTemperature": {
                "dwd": 14.52,
                "ecmwf": 14.0,
                "ecmwf:aifs": 13.67,
                "noaa": 13.59,
                "sg": 14.0
            },
            "cloudCover": {
                "dwd": 37.03,
                "noaa": 36.67,
                "sg": 37.03
            },
            "time": "2025-09-05T22:00:00+00:00",
            "waterTemperature": {
                "noaa": 11.3,
                "sg": 11.3
            },
            "windDirection": {
                "dwd": 99.35,
                "ecmwf": 180.77,
                "ecmwf:aifs": 213.34,
                "noaa": 54.01,
                "sg": 180.77
            },
            "windSpeed": {
                "dwd": 0.71,
                "ecmwf": 1.03,
                "ecmwf:aifs": 1.4,
                "noaa": 1.55,
                "sg": 1.03
            }
        },
        {
            "airTemperature": {
                "dwd": 13.83,
                "ecmwf": 13.32,
                "ecmwf:aifs": 12.54,
                "noaa": 13.3,
                "sg": 13.32
            },
            "cloudCover": {
                "dwd": 27.28,
                "noaa": 68.33,
                "sg": 27.28
            },
            "time": "2025-09-05T23:00:00+00:00",
            "waterTemperature": {
                "noaa": 11.04,
                "sg": 11.04
            },
            "windDirection": {
                "dwd": 136.47,
                "ecmwf": 180.48,
                "ecmwf:aifs": 202.91,
                "noaa": 73.49,
                "sg": 180.48
            },
            "windSpeed": {
                "dwd": 0.67,
                "ecmwf": 1.12,
                "ecmwf:aifs": 1.37,
                "noaa": 1.55,
                "sg": 1.12
            }
        },
        {
            "airTemperature": {
                "dwd": 12.94,
                "ecmwf": 12.64,
                "ecmwf:aifs": 11.42,
                "noaa": 13.01,
                "sg": 12.64
            },
            "cloudCover": {
                "dwd": 12.42,
                "noaa": 100.0,
                "sg": 12.42
            },
            "time": "2025-09-06T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 10.79,
                "sg": 10.79
            },
            "windDirection": {
                "dwd": 143.45,
                "ecmwf": 180.19,
                "ecmwf:aifs": 192.48,
                "noaa": 92.97,
                "sg": 180.19
            },
            "windSpeed": {
                "dwd": 0.76,
                "ecmwf": 1.21,
                "ecmwf:aifs": 1.34,
                "noaa": 1.56,
                "sg": 1.21
            }
        },
        {
            "airTemperature": {
                "dwd": 11.8,
                "ecmwf": 12.29,
                "ecmwf:aifs": 11.27,
                "noaa": 12.89,
                "sg": 12.29
            },
            "cloudCover": {
                "dwd": 10.49,
                "noaa": 79.1,
                "sg": 10.49
            },
            "time": "2025-09-06T01:00:00+00:00",
            "waterTemperature": {
                "noaa": 10.93,
                "sg": 10.93
            },
            "windDirection": {
                "dwd": 178.73,
                "ecmwf": 177.68,
                "ecmwf:aifs": 186.0,
                "noaa": 97.26,
                "sg": 177.68
            },
            "windSpeed": {
                "dwd": 0.86,
                "ecmwf": 1.29,
                "ecmwf:aifs": 1.45,
                "noaa": 1.87,
                "sg": 1.29
            }
        },
        {
            "airTemperature": {
                "dwd": 10.94,
                "ecmwf": 11.94,
                "ecmwf:aifs": 11.12,
                "noaa": 12.78,
                "sg": 11.94
            },
            "cloudCover": {
                "dwd": 18.26,
                "noaa": 58.2,
                "sg": 18.26
            },
            "time": "2025-09-06T02:00:00+00:00",
            "waterTemperature": {
                "noaa": 11.08,
                "sg": 11.08
            },
            "windDirection": {
                "dwd": 156.65,
                "ecmwf": 175.17,
                "ecmwf:aifs": 179.52,
                "noaa": 101.54,
                "sg": 175.17
            },
            "windSpeed": {
                "dwd": 0.95,
                "ecmwf": 1.36,
                "ecmwf:aifs": 1.55,
                "noaa": 2.18,
                "sg": 1.36
            }
        },
        {
            "airTemperature": {
                "dwd": 10.45,
                "ecmwf": 11.59,
                "ecmwf:aifs": 10.98,
                "noaa": 12.66,
                "sg": 11.59
            },
            "cloudCover": {
                "dwd": 27.04,
                "noaa": 37.3,
                "sg": 27.04
            },
            "time": "2025-09-06T03:00:00+00:00",
            "waterTemperature": {
                "noaa": 11.23,
                "sg": 11.23
            },
            "windDirection": {
                "dwd": 157.88,
                "ecmwf": 172.66,
                "ecmwf:aifs": 173.04,
                "noaa": 105.83,
                "sg": 172.66
            },
            "windSpeed": {
                "dwd": 0.93,
                "ecmwf": 1.44,
                "ecmwf:aifs": 1.66,
                "noaa": 2.49,
                "sg": 1.44
            }
        },
        {
            "airTemperature": {
                "dwd": 10.2,
                "ecmwf": 11.43,
                "ecmwf:aifs": 10.83,
                "noaa": 12.86,
                "sg": 11.43
            },
            "cloudCover": {
                "dwd": 55.83,
                "noaa": 44.1,
                "sg": 55.83
            },
            "time": "2025-09-06T04:00:00+00:00",
            "waterTemperature": {
                "noaa": 11.74,
                "sg": 11.74
            },
            "windDirection": {
                "dwd": 147.85,
                "ecmwf": 166.41,
                "ecmwf:aifs": 166.57,
                "noaa": 109.58,
                "sg": 166.41
            },
            "windSpeed": {
                "dwd": 0.94,
                "ecmwf": 1.54,
                "ecmwf:aifs": 1.76,
                "noaa": 2.61,
                "sg": 1.54
            }
        },
        {
            "airTemperature": {
                "dwd": 10.02,
                "ecmwf": 11.27,
                "ecmwf:aifs": 10.68,
                "noaa": 13.06,
                "sg": 11.27
            },
            "cloudCover": {
                "dwd": 92.74,
                "noaa": 50.9,
                "sg": 92.74
            },
            "time": "2025-09-06T05:00:00+00:00",
            "waterTemperature": {
                "noaa": 12.24,
                "sg": 12.24
            },
            "windDirection": {
                "dwd": 143.29,
                "ecmwf": 160.16,
                "ecmwf:aifs": 160.09,
                "noaa": 113.33,
                "sg": 160.16
            },
            "windSpeed": {
                "dwd": 0.98,
                "ecmwf": 1.64,
                "ecmwf:aifs": 1.87,
                "noaa": 2.73,
                "sg": 1.64
            }
        },
        {
            "airTemperature": {
                "dwd": 10.53,
                "ecmwf": 11.11,
                "ecmwf:aifs": 10.54,
                "noaa": 13.25,
                "sg": 11.11
            },
            "cloudCover": {
                "dwd": 95.02,
                "noaa": 57.7,
                "sg": 95.02
            },
            "time": "2025-09-06T06:00:00+00:00",
            "waterTemperature": {
                "noaa": 12.75,
                "sg": 12.75
            },
            "windDirection": {
                "dwd": 135.64,
                "ecmwf": 153.91,
                "ecmwf:aifs": 153.61,
                "noaa": 117.08,
                "sg": 153.91
            },
            "windSpeed": {
                "dwd": 1.02,
                "ecmwf": 1.74,
                "ecmwf:aifs": 1.97,
                "noaa": 2.85,
                "sg": 1.74
            }
        },
        {
            "airTemperature": {
                "dwd": 13.13,
                "ecmwf": 11.37,
                "ecmwf:aifs": 12.53,
                "noaa": 16.0,
                "sg": 11.37
            },
            "cloudCover": {
                "dwd": 96.57,
                "noaa": 68.37,
                "sg": 96.57
            },
            "time": "2025-09-06T07:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.08,
                "sg": 17.08
            },
            "windDirection": {
                "dwd": 145.84,
                "ecmwf": 151.33,
                "ecmwf:aifs": 149.98,
                "noaa": 130.63,
                "sg": 151.33
            },
            "windSpeed": {
                "dwd": 1.29,
                "ecmwf": 1.7,
                "ecmwf:aifs": 2.12,
                "noaa": 2.67,
                "sg": 1.7
            }
        },
        {
            "airTemperature": {
                "dwd": 15.72,
                "ecmwf": 11.63,
                "ecmwf:aifs": 14.53,
                "noaa": 18.75,
                "sg": 11.63
            },
            "cloudCover": {
                "dwd": 98.12,
                "noaa": 79.03,
                "sg": 98.12
            },
            "time": "2025-09-06T08:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.41,
                "sg": 21.41
            },
            "windDirection": {
                "dwd": 156.04,
                "ecmwf": 148.75,
                "ecmwf:aifs": 146.35,
                "noaa": 144.18,
                "sg": 148.75
            },
            "windSpeed": {
                "dwd": 1.57,
                "ecmwf": 1.66,
                "ecmwf:aifs": 2.27,
                "noaa": 2.48,
                "sg": 1.66
            }
        },
        {
            "airTemperature": {
                "dwd": 18.32,
                "ecmwf": 11.89,
                "ecmwf:aifs": 16.52,
                "noaa": 21.5,
                "sg": 11.89
            },
            "cloudCover": {
                "dwd": 99.67,
                "noaa": 89.7,
                "sg": 99.67
            },
            "time": "2025-09-06T09:00:00+00:00",
            "waterTemperature": {
                "noaa": 25.74,
                "sg": 25.74
            },
            "windDirection": {
                "dwd": 166.24,
                "ecmwf": 146.17,
                "ecmwf:aifs": 142.72,
                "noaa": 157.73,
                "sg": 146.17
            },
            "windSpeed": {
                "dwd": 1.84,
                "ecmwf": 1.62,
                "ecmwf:aifs": 2.43,
                "noaa": 2.3,
                "sg": 1.62
            }
        },
        {
            "airTemperature": {
                "dwd": 19.24,
                "ecmwf": 14.26,
                "ecmwf:aifs": 18.52,
                "noaa": 22.95,
                "sg": 14.26
            },
            "cloudCover": {
                "dwd": 99.78,
                "noaa": 93.13,
                "sg": 99.78
            },
            "time": "2025-09-06T10:00:00+00:00",
            "waterTemperature": {
                "noaa": 27.33,
                "sg": 27.33
            },
            "windDirection": {
                "dwd": 157.77,
                "ecmwf": 144.65,
                "ecmwf:aifs": 139.1,
                "noaa": 152.85,
                "sg": 144.65
            },
            "windSpeed": {
                "dwd": 2.05,
                "ecmwf": 2.0,
                "ecmwf:aifs": 2.58,
                "noaa": 2.8,
                "sg": 2.0
            }
        },
        {
            "airTemperature": {
                "dwd": 20.16,
                "ecmwf": 16.63,
                "ecmwf:aifs": 20.52,
                "noaa": 24.39,
                "sg": 16.63
            },
            "cloudCover": {
                "dwd": 99.89,
                "noaa": 96.57,
                "sg": 99.89
            },
            "time": "2025-09-06T11:00:00+00:00",
            "waterTemperature": {
                "noaa": 28.92,
                "sg": 28.92
            },
            "windDirection": {
                "dwd": 149.3,
                "ecmwf": 143.12,
                "ecmwf:aifs": 135.47,
                "noaa": 147.97,
                "sg": 143.12
            },
            "windSpeed": {
                "dwd": 2.27,
                "ecmwf": 2.37,
                "ecmwf:aifs": 2.73,
                "noaa": 3.3,
                "sg": 2.37
            }
        },
        {
            "airTemperature": {
                "dwd": 21.09,
                "ecmwf": 19.0,
                "ecmwf:aifs": 22.51,
                "noaa": 25.84,
                "sg": 19.0
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-06T12:00:00+00:00",
            "waterTemperature": {
                "noaa": 30.51,
                "sg": 30.51
            },
            "windDirection": {
                "dwd": 140.83,
                "ecmwf": 141.6,
                "ecmwf:aifs": 131.84,
                "noaa": 143.09,
                "sg": 141.6
            },
            "windSpeed": {
                "dwd": 2.48,
                "ecmwf": 2.75,
                "ecmwf:aifs": 2.88,
                "noaa": 3.8,
                "sg": 2.75
            }
        },
        {
            "airTemperature": {
                "dwd": 21.6,
                "ecmwf": 19.95,
                "ecmwf:aifs": 22.22,
                "noaa": 26.02,
                "sg": 19.95
            },
            "cloudCover": {
                "dwd": 92.41,
                "noaa": 100.0,
                "sg": 92.41
            },
            "time": "2025-09-06T13:00:00+00:00",
            "waterTemperature": {
                "noaa": 29.6,
                "sg": 29.6
            },
            "windDirection": {
                "dwd": 135.11,
                "ecmwf": 132.1,
                "ecmwf:aifs": 129.36,
                "noaa": 139.79,
                "sg": 132.1
            },
            "windSpeed": {
                "dwd": 2.75,
                "ecmwf": 2.87,
                "ecmwf:aifs": 2.87,
                "noaa": 4.09,
                "sg": 2.87
            }
        },
        {
            "airTemperature": {
                "dwd": 22.12,
                "ecmwf": 20.91,
                "ecmwf:aifs": 21.92,
                "noaa": 26.21,
                "sg": 20.91
            },
            "cloudCover": {
                "dwd": 84.81,
                "noaa": 100.0,
                "sg": 84.81
            },
            "time": "2025-09-06T14:00:00+00:00",
            "waterTemperature": {
                "noaa": 28.69,
                "sg": 28.69
            },
            "windDirection": {
                "dwd": 129.38,
                "ecmwf": 122.59,
                "ecmwf:aifs": 126.88,
                "noaa": 136.5,
                "sg": 122.59
            },
            "windSpeed": {
                "dwd": 3.01,
                "ecmwf": 2.99,
                "ecmwf:aifs": 2.86,
                "noaa": 4.39,
                "sg": 2.99
            }
        },
        {
            "airTemperature": {
                "dwd": 22.63,
                "ecmwf": 21.86,
                "ecmwf:aifs": 21.62,
                "noaa": 26.39,
                "sg": 21.86
            },
            "cloudCover": {
                "dwd": 77.22,
                "noaa": 100.0,
                "sg": 77.22
            },
            "time": "2025-09-06T15:00:00+00:00",
            "waterTemperature": {
                "noaa": 27.78,
                "sg": 27.78
            },
            "windDirection": {
                "dwd": 123.66,
                "ecmwf": 113.09,
                "ecmwf:aifs": 124.39,
                "noaa": 133.2,
                "sg": 113.09
            },
            "windSpeed": {
                "dwd": 3.28,
                "ecmwf": 3.11,
                "ecmwf:aifs": 2.85,
                "noaa": 4.68,
                "sg": 3.11
            }
        },
        {
            "airTemperature": {
                "dwd": 21.6,
                "ecmwf": 21.09,
                "ecmwf:aifs": 21.33,
                "noaa": 24.83,
                "sg": 21.09
            },
            "cloudCover": {
                "dwd": 84.81,
                "noaa": 99.87,
                "sg": 84.81
            },
            "time": "2025-09-06T16:00:00+00:00",
            "waterTemperature": {
                "noaa": 25.11,
                "sg": 25.11
            },
            "windDirection": {
                "dwd": 117.75,
                "ecmwf": 110.83,
                "ecmwf:aifs": 121.91,
                "noaa": 131.05,
                "sg": 110.83
            },
            "windSpeed": {
                "dwd": 2.87,
                "ecmwf": 2.79,
                "ecmwf:aifs": 2.83,
                "noaa": 4.33,
                "sg": 2.79
            }
        },
        {
            "airTemperature": {
                "dwd": 20.58,
                "ecmwf": 20.31,
                "ecmwf:aifs": 21.03,
                "noaa": 23.28,
                "sg": 20.31
            },
            "cloudCover": {
                "dwd": 92.41,
                "noaa": 99.73,
                "sg": 92.41
            },
            "time": "2025-09-06T17:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.45,
                "sg": 22.45
            },
            "windDirection": {
                "dwd": 111.84,
                "ecmwf": 108.57,
                "ecmwf:aifs": 119.43,
                "noaa": 128.9,
                "sg": 108.57
            },
            "windSpeed": {
                "dwd": 2.46,
                "ecmwf": 2.48,
                "ecmwf:aifs": 2.82,
                "noaa": 3.98,
                "sg": 2.48
            }
        },
        {
            "airTemperature": {
                "dwd": 19.55,
                "ecmwf": 19.53,
                "ecmwf:aifs": 20.74,
                "noaa": 21.72,
                "sg": 19.53
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 99.6,
                "sg": 100.0
            },
            "time": "2025-09-06T18:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.78,
                "sg": 19.78
            },
            "windDirection": {
                "dwd": 105.93,
                "ecmwf": 106.31,
                "ecmwf:aifs": 116.95,
                "noaa": 126.75,
                "sg": 106.31
            },
            "windSpeed": {
                "dwd": 2.05,
                "ecmwf": 2.16,
                "ecmwf:aifs": 2.81,
                "noaa": 3.63,
                "sg": 2.16
            }
        },
        {
            "airTemperature": {
                "dwd": 18.38,
                "ecmwf": 18.56,
                "ecmwf:aifs": 19.8,
                "noaa": 20.36,
                "sg": 18.56
            },
            "cloudCover": {
                "dwd": 99.34,
                "noaa": 99.73,
                "sg": 99.34
            },
            "time": "2025-09-06T19:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.53,
                "sg": 18.53
            },
            "windDirection": {
                "dwd": 104.61,
                "ecmwf": 109.45,
                "ecmwf:aifs": 119.93,
                "noaa": 128.43,
                "sg": 109.45
            },
            "windSpeed": {
                "dwd": 1.95,
                "ecmwf": 2.46,
                "ecmwf:aifs": 2.95,
                "noaa": 3.86,
                "sg": 2.46
            }
        },
        {
            "airTemperature": {
                "dwd": 17.21,
                "ecmwf": 17.59,
                "ecmwf:aifs": 18.87,
                "noaa": 19.01,
                "sg": 17.59
            },
            "cloudCover": {
                "dwd": 98.68,
                "noaa": 99.87,
                "sg": 98.68
            },
            "time": "2025-09-06T20:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.29,
                "sg": 17.29
            },
            "windDirection": {
                "dwd": 103.3,
                "ecmwf": 112.6,
                "ecmwf:aifs": 122.92,
                "noaa": 130.1,
                "sg": 112.6
            },
            "windSpeed": {
                "dwd": 1.86,
                "ecmwf": 2.75,
                "ecmwf:aifs": 3.1,
                "noaa": 4.1,
                "sg": 2.75
            }
        },
        {
            "airTemperature": {
                "dwd": 16.04,
                "ecmwf": 16.62,
                "ecmwf:aifs": 17.94,
                "noaa": 17.66,
                "sg": 16.62
            },
            "cloudCover": {
                "dwd": 98.03,
                "noaa": 100.0,
                "sg": 98.03
            },
            "time": "2025-09-06T21:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.04,
                "sg": 16.04
            },
            "windDirection": {
                "dwd": 101.98,
                "ecmwf": 115.74,
                "ecmwf:aifs": 125.9,
                "noaa": 131.78,
                "sg": 115.74
            },
            "windSpeed": {
                "dwd": 1.76,
                "ecmwf": 3.05,
                "ecmwf:aifs": 3.25,
                "noaa": 4.33,
                "sg": 3.05
            }
        },
        {
            "airTemperature": {
                "dwd": 16.04,
                "ecmwf": 16.09,
                "ecmwf:aifs": 17.0,
                "noaa": 16.82,
                "sg": 16.09
            },
            "cloudCover": {
                "dwd": 98.68,
                "noaa": 95.7,
                "sg": 98.68
            },
            "time": "2025-09-06T22:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.22,
                "sg": 15.22
            },
            "windDirection": {
                "dwd": 106.13,
                "ecmwf": 119.1,
                "ecmwf:aifs": 128.89,
                "noaa": 125.83,
                "sg": 119.1
            },
            "windSpeed": {
                "dwd": 1.98,
                "ecmwf": 3.08,
                "ecmwf:aifs": 3.39,
                "noaa": 4.14,
                "sg": 3.08
            }
        },
        {
            "airTemperature": {
                "dwd": 16.04,
                "ecmwf": 15.57,
                "ecmwf:aifs": 16.07,
                "noaa": 15.98,
                "sg": 15.57
            },
            "cloudCover": {
                "dwd": 99.34,
                "noaa": 91.4,
                "sg": 99.34
            },
            "time": "2025-09-06T23:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.41,
                "sg": 14.41
            },
            "windDirection": {
                "dwd": 110.28,
                "ecmwf": 122.45,
                "ecmwf:aifs": 131.88,
                "noaa": 119.87,
                "sg": 122.45
            },
            "windSpeed": {
                "dwd": 2.21,
                "ecmwf": 3.1,
                "ecmwf:aifs": 3.54,
                "noaa": 3.96,
                "sg": 3.1
            }
        },
        {
            "airTemperature": {
                "dwd": 16.04,
                "ecmwf": 15.05,
                "ecmwf:aifs": 15.13,
                "noaa": 15.14,
                "sg": 15.05
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 87.1,
                "sg": 100.0
            },
            "time": "2025-09-07T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.59,
                "sg": 13.59
            },
            "windDirection": {
                "dwd": 114.43,
                "ecmwf": 125.81,
                "ecmwf:aifs": 134.86,
                "noaa": 113.92,
                "sg": 125.81
            },
            "windSpeed": {
                "dwd": 2.43,
                "ecmwf": 3.13,
                "ecmwf:aifs": 3.68,
                "noaa": 3.77,
                "sg": 3.13
            }
        },
        {
            "airTemperature": {
                "dwd": 15.78,
                "ecmwf": 14.71,
                "ecmwf:aifs": 15.16,
                "noaa": 14.65,
                "sg": 14.71
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 91.4,
                "sg": 100.0
            },
            "time": "2025-09-07T01:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.12,
                "sg": 13.12
            },
            "windDirection": {
                "dwd": 116.64,
                "ecmwf": 126.4,
                "ecmwf:aifs": 136.85,
                "noaa": 114.79,
                "sg": 126.4
            },
            "windSpeed": {
                "dwd": 2.24,
                "ecmwf": 3.18,
                "ecmwf:aifs": 3.73,
                "noaa": 3.84,
                "sg": 3.18
            }
        },
        {
            "airTemperature": {
                "dwd": 15.51,
                "ecmwf": 14.37,
                "ecmwf:aifs": 15.19,
                "noaa": 14.16,
                "sg": 14.37
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 95.7,
                "sg": 100.0
            },
            "time": "2025-09-07T02:00:00+00:00",
            "waterTemperature": {
                "noaa": 12.65,
                "sg": 12.65
            },
            "windDirection": {
                "dwd": 118.86,
                "ecmwf": 127.0,
                "ecmwf:aifs": 138.83,
                "noaa": 115.67,
                "sg": 127.0
            },
            "windSpeed": {
                "dwd": 2.05,
                "ecmwf": 3.23,
                "ecmwf:aifs": 3.78,
                "noaa": 3.91,
                "sg": 3.23
            }
        },
        {
            "airTemperature": {
                "dwd": 15.25,
                "ecmwf": 14.03,
                "ecmwf:aifs": 15.21,
                "noaa": 13.67,
                "sg": 14.03
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T03:00:00+00:00",
            "waterTemperature": {
                "noaa": 12.18,
                "sg": 12.18
            },
            "windDirection": {
                "dwd": 121.07,
                "ecmwf": 127.59,
                "ecmwf:aifs": 140.82,
                "noaa": 116.54,
                "sg": 127.59
            },
            "windSpeed": {
                "dwd": 1.86,
                "ecmwf": 3.28,
                "ecmwf:aifs": 3.84,
                "noaa": 3.98,
                "sg": 3.28
            }
        },
        {
            "airTemperature": {
                "dwd": 15.36,
                "ecmwf": 13.97,
                "ecmwf:aifs": 15.24,
                "noaa": 13.97,
                "sg": 13.97
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T04:00:00+00:00",
            "waterTemperature": {
                "noaa": 12.84,
                "sg": 12.84
            },
            "windDirection": {
                "dwd": 118.07,
                "ecmwf": 126.35,
                "ecmwf:aifs": 142.8,
                "noaa": 115.55,
                "sg": 126.35
            },
            "windSpeed": {
                "dwd": 2.1,
                "ecmwf": 3.3,
                "ecmwf:aifs": 3.89,
                "noaa": 4.31,
                "sg": 3.3
            }
        },
        {
            "airTemperature": {
                "dwd": 15.48,
                "ecmwf": 13.91,
                "ecmwf:aifs": 15.27,
                "noaa": 14.26,
                "sg": 13.91
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T05:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.51,
                "sg": 13.51
            },
            "windDirection": {
                "dwd": 115.07,
                "ecmwf": 125.11,
                "ecmwf:aifs": 144.79,
                "noaa": 114.57,
                "sg": 125.11
            },
            "windSpeed": {
                "dwd": 2.35,
                "ecmwf": 3.32,
                "ecmwf:aifs": 3.94,
                "noaa": 4.63,
                "sg": 3.32
            }
        },
        {
            "airTemperature": {
                "dwd": 15.59,
                "ecmwf": 13.85,
                "ecmwf:aifs": 15.29,
                "noaa": 14.56,
                "sg": 13.85
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T06:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.17,
                "sg": 14.17
            },
            "windDirection": {
                "dwd": 112.07,
                "ecmwf": 123.87,
                "ecmwf:aifs": 146.77,
                "noaa": 113.58,
                "sg": 123.87
            },
            "windSpeed": {
                "dwd": 2.59,
                "ecmwf": 3.34,
                "ecmwf:aifs": 3.99,
                "noaa": 4.96,
                "sg": 3.34
            }
        },
        {
            "airTemperature": {
                "dwd": 17.18,
                "ecmwf": 14.13,
                "ecmwf:aifs": 17.21,
                "noaa": 17.62,
                "sg": 14.13
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T07:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.09,
                "sg": 18.09
            },
            "windDirection": {
                "dwd": 120.53,
                "ecmwf": 124.73,
                "ecmwf:aifs": 152.81,
                "noaa": 119.08,
                "sg": 124.73
            },
            "windSpeed": {
                "dwd": 2.92,
                "ecmwf": 3.12,
                "ecmwf:aifs": 4.26,
                "noaa": 5.34,
                "sg": 3.12
            }
        },
        {
            "airTemperature": {
                "dwd": 18.78,
                "ecmwf": 14.42,
                "ecmwf:aifs": 19.13,
                "noaa": 20.67,
                "sg": 14.42
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T08:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.01,
                "sg": 22.01
            },
            "windDirection": {
                "dwd": 128.99,
                "ecmwf": 125.6,
                "ecmwf:aifs": 158.85,
                "noaa": 124.58,
                "sg": 125.6
            },
            "windSpeed": {
                "dwd": 3.26,
                "ecmwf": 2.9,
                "ecmwf:aifs": 4.53,
                "noaa": 5.73,
                "sg": 2.9
            }
        },
        {
            "airTemperature": {
                "dwd": 20.37,
                "ecmwf": 14.7,
                "ecmwf:aifs": 21.04,
                "noaa": 23.73,
                "sg": 14.7
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T09:00:00+00:00",
            "waterTemperature": {
                "noaa": 25.93,
                "sg": 25.93
            },
            "windDirection": {
                "dwd": 137.45,
                "ecmwf": 126.46,
                "ecmwf:aifs": 164.89,
                "noaa": 130.08,
                "sg": 126.46
            },
            "windSpeed": {
                "dwd": 3.59,
                "ecmwf": 2.68,
                "ecmwf:aifs": 4.79,
                "noaa": 6.11,
                "sg": 2.68
            }
        },
        {
            "airTemperature": {
                "dwd": 21.4,
                "ecmwf": 16.82,
                "ecmwf:aifs": 22.96,
                "noaa": 26.1,
                "sg": 16.82
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T10:00:00+00:00",
            "waterTemperature": {
                "noaa": 28.87,
                "sg": 28.87
            },
            "windDirection": {
                "dwd": 147.77,
                "ecmwf": 132.74,
                "ecmwf:aifs": 170.93,
                "noaa": 135.22,
                "sg": 132.74
            },
            "windSpeed": {
                "dwd": 3.89,
                "ecmwf": 2.98,
                "ecmwf:aifs": 5.06,
                "noaa": 6.14,
                "sg": 2.98
            }
        },
        {
            "airTemperature": {
                "dwd": 22.42,
                "ecmwf": 18.95,
                "ecmwf:aifs": 24.88,
                "noaa": 28.47,
                "sg": 18.95
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T11:00:00+00:00",
            "waterTemperature": {
                "noaa": 31.8,
                "sg": 31.8
            },
            "windDirection": {
                "dwd": 158.08,
                "ecmwf": 139.02,
                "ecmwf:aifs": 176.97,
                "noaa": 140.35,
                "sg": 139.02
            },
            "windSpeed": {
                "dwd": 4.2,
                "ecmwf": 3.29,
                "ecmwf:aifs": 5.33,
                "noaa": 6.17,
                "sg": 3.29
            }
        },
        {
            "airTemperature": {
                "dwd": 23.44,
                "ecmwf": 21.07,
                "ecmwf:aifs": 26.79,
                "noaa": 30.84,
                "sg": 21.07
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T12:00:00+00:00",
            "waterTemperature": {
                "noaa": 34.74,
                "sg": 34.74
            },
            "windDirection": {
                "dwd": 168.4,
                "ecmwf": 145.3,
                "ecmwf:aifs": 183.01,
                "noaa": 145.49,
                "sg": 145.3
            },
            "windSpeed": {
                "dwd": 4.5,
                "ecmwf": 3.59,
                "ecmwf:aifs": 5.6,
                "noaa": 6.2,
                "sg": 3.59
            }
        },
        {
            "airTemperature": {
                "dwd": 22.87,
                "ecmwf": 22.8,
                "ecmwf:aifs": 26.18,
                "noaa": 31.19,
                "sg": 22.8
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 89.5,
                "sg": 100.0
            },
            "time": "2025-09-07T13:00:00+00:00",
            "waterTemperature": {
                "noaa": 34.56,
                "sg": 34.56
            },
            "windDirection": {
                "dwd": 176.51,
                "ecmwf": 158.06,
                "ecmwf:aifs": 187.89,
                "noaa": 154.41,
                "sg": 158.06
            },
            "windSpeed": {
                "dwd": 4.99,
                "ecmwf": 3.96,
                "ecmwf:aifs": 5.4,
                "noaa": 5.98,
                "sg": 3.96
            }
        },
        {
            "airTemperature": {
                "dwd": 22.3,
                "ecmwf": 24.53,
                "ecmwf:aifs": 25.58,
                "noaa": 31.54,
                "sg": 24.53
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 79.0,
                "sg": 100.0
            },
            "time": "2025-09-07T14:00:00+00:00",
            "waterTemperature": {
                "noaa": 34.37,
                "sg": 34.37
            },
            "windDirection": {
                "dwd": 184.62,
                "ecmwf": 170.81,
                "ecmwf:aifs": 192.77,
                "noaa": 163.34,
                "sg": 170.81
            },
            "windSpeed": {
                "dwd": 5.48,
                "ecmwf": 4.32,
                "ecmwf:aifs": 5.21,
                "noaa": 5.76,
                "sg": 4.32
            }
        },
        {
            "airTemperature": {
                "dwd": 21.73,
                "ecmwf": 26.26,
                "ecmwf:aifs": 24.97,
                "noaa": 31.9,
                "sg": 26.26
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 68.5,
                "sg": 100.0
            },
            "time": "2025-09-07T15:00:00+00:00",
            "waterTemperature": {
                "noaa": 34.19,
                "sg": 34.19
            },
            "windDirection": {
                "dwd": 192.73,
                "ecmwf": 183.57,
                "ecmwf:aifs": 197.65,
                "noaa": 172.26,
                "sg": 183.57
            },
            "windSpeed": {
                "dwd": 5.97,
                "ecmwf": 4.69,
                "ecmwf:aifs": 5.01,
                "noaa": 5.54,
                "sg": 4.69
            }
        },
        {
            "airTemperature": {
                "dwd": 21.16,
                "ecmwf": 25.19,
                "ecmwf:aifs": 24.36,
                "noaa": 30.25,
                "sg": 25.19
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 79.0,
                "sg": 100.0
            },
            "time": "2025-09-07T16:00:00+00:00",
            "waterTemperature": {
                "noaa": 31.2,
                "sg": 31.2
            },
            "windDirection": {
                "dwd": 190.74,
                "ecmwf": 193.39,
                "ecmwf:aifs": 202.53,
                "noaa": 166.25,
                "sg": 193.39
            },
            "windSpeed": {
                "dwd": 5.66,
                "ecmwf": 4.47,
                "ecmwf:aifs": 4.82,
                "noaa": 5.06,
                "sg": 4.47
            }
        },
        {
            "airTemperature": {
                "dwd": 20.59,
                "ecmwf": 24.11,
                "ecmwf:aifs": 23.75,
                "noaa": 28.6,
                "sg": 24.11
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 89.5,
                "sg": 100.0
            },
            "time": "2025-09-07T17:00:00+00:00",
            "waterTemperature": {
                "noaa": 28.22,
                "sg": 28.22
            },
            "windDirection": {
                "dwd": 188.75,
                "ecmwf": 203.22,
                "ecmwf:aifs": 207.41,
                "noaa": 160.24,
                "sg": 203.22
            },
            "windSpeed": {
                "dwd": 5.35,
                "ecmwf": 4.25,
                "ecmwf:aifs": 4.62,
                "noaa": 4.57,
                "sg": 4.25
            }
        },
        {
            "airTemperature": {
                "dwd": 20.02,
                "ecmwf": 23.03,
                "ecmwf:aifs": 23.15,
                "noaa": 26.95,
                "sg": 23.03
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T18:00:00+00:00",
            "waterTemperature": {
                "noaa": 25.24,
                "sg": 25.24
            },
            "windDirection": {
                "dwd": 186.76,
                "ecmwf": 213.04,
                "ecmwf:aifs": 212.29,
                "noaa": 154.23,
                "sg": 213.04
            },
            "windSpeed": {
                "dwd": 5.04,
                "ecmwf": 4.03,
                "ecmwf:aifs": 4.43,
                "noaa": 4.09,
                "sg": 4.03
            }
        },
        {
            "airTemperature": {
                "dwd": 19.69,
                "ecmwf": 22.84,
                "ecmwf:aifs": 22.37,
                "noaa": 25.68,
                "sg": 22.84
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T19:00:00+00:00",
            "waterTemperature": {
                "noaa": 24.24,
                "sg": 24.24
            },
            "windDirection": {
                "dwd": 189.79,
                "ecmwf": 209.99,
                "ecmwf:aifs": 212.97,
                "noaa": 150.82,
                "sg": 209.99
            },
            "windSpeed": {
                "dwd": 5.27,
                "ecmwf": 4.03,
                "ecmwf:aifs": 4.2,
                "noaa": 4.61,
                "sg": 4.03
            }
        },
        {
            "airTemperature": {
                "dwd": 19.36,
                "ecmwf": 22.65,
                "ecmwf:aifs": 21.59,
                "noaa": 24.42,
                "sg": 22.65
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T20:00:00+00:00",
            "waterTemperature": {
                "noaa": 23.25,
                "sg": 23.25
            },
            "windDirection": {
                "dwd": 192.81,
                "ecmwf": 206.93,
                "ecmwf:aifs": 213.64,
                "noaa": 147.4,
                "sg": 206.93
            },
            "windSpeed": {
                "dwd": 5.5,
                "ecmwf": 4.02,
                "ecmwf:aifs": 3.97,
                "noaa": 5.14,
                "sg": 4.02
            }
        },
        {
            "airTemperature": {
                "dwd": 19.03,
                "ecmwf": 22.46,
                "ecmwf:aifs": 20.81,
                "noaa": 23.15,
                "sg": 22.46
            },
            "cloudCover": {
                "dwd": 100.0,
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-07T21:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.25,
                "sg": 22.25
            },
            "windDirection": {
                "dwd": 195.84,
                "ecmwf": 203.88,
                "ecmwf:aifs": 214.32,
                "noaa": 143.99,
                "sg": 203.88
            },
            "windSpeed": {
                "dwd": 5.73,
                "ecmwf": 4.02,
                "ecmwf:aifs": 3.74,
                "noaa": 5.66,
                "sg": 4.02
            }
        },
        {
            "airTemperature": {
                "dwd": 18.56,
                "ecmwf": 20.86,
                "ecmwf:aifs": 20.03,
                "noaa": 22.35,
                "sg": 20.86
            },
            "cloudCover": {
                "dwd": 95.25,
                "noaa": 100.0,
                "sg": 95.25
            },
            "time": "2025-09-07T22:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.45,
                "sg": 21.45
            },
            "windDirection": {
                "dwd": 205.86,
                "ecmwf": 203.24,
                "ecmwf:aifs": 215.0,
                "noaa": 144.38,
                "sg": 203.24
            },
            "windSpeed": {
                "dwd": 6.04,
                "ecmwf": 4.22,
                "ecmwf:aifs": 3.52,
                "noaa": 4.99,
                "sg": 4.22
            }
        },
        {
            "airTemperature": {
                "dwd": 18.08,
                "ecmwf": 19.26,
                "ecmwf:aifs": 19.25,
                "noaa": 21.55,
                "sg": 19.26
            },
            "cloudCover": {
                "dwd": 90.51,
                "noaa": 100.0,
                "sg": 90.51
            },
            "time": "2025-09-07T23:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.64,
                "sg": 20.64
            },
            "windDirection": {
                "dwd": 215.89,
                "ecmwf": 202.59,
                "ecmwf:aifs": 215.67,
                "noaa": 144.76,
                "sg": 202.59
            },
            "windSpeed": {
                "dwd": 6.36,
                "ecmwf": 4.41,
                "ecmwf:aifs": 3.29,
                "noaa": 4.32,
                "sg": 4.41
            }
        },
        {
            "airTemperature": {
                "dwd": 17.61,
                "ecmwf": 17.66,
                "ecmwf:aifs": 18.48,
                "noaa": 20.75,
                "sg": 17.66
            },
            "cloudCover": {
                "dwd": 85.76,
                "noaa": 100.0,
                "sg": 85.76
            },
            "time": "2025-09-08T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.84,
                "sg": 19.84
            },
            "windDirection": {
                "dwd": 225.91,
                "ecmwf": 201.95,
                "ecmwf:aifs": 216.35,
                "noaa": 145.15,
                "sg": 201.95
            },
            "windSpeed": {
                "dwd": 6.67,
                "ecmwf": 4.61,
                "ecmwf:aifs": 3.06,
                "noaa": 3.65,
                "sg": 4.61
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.55,
                "ecmwf:aifs": 18.06,
                "noaa": 20.35,
                "sg": 17.55
            },
            "cloudCover": {
                "noaa": 98.43,
                "sg": 98.43
            },
            "time": "2025-09-08T01:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.12,
                "sg": 19.12
            },
            "windDirection": {
                "ecmwf": 204.59,
                "ecmwf:aifs": 213.86,
                "noaa": 161.11,
                "sg": 204.59
            },
            "windSpeed": {
                "ecmwf": 4.68,
                "ecmwf:aifs": 3.12,
                "noaa": 3.12,
                "sg": 4.68
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.45,
                "ecmwf:aifs": 17.65,
                "noaa": 19.94,
                "sg": 17.45
            },
            "cloudCover": {
                "noaa": 96.87,
                "sg": 96.87
            },
            "time": "2025-09-08T02:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.4,
                "sg": 18.4
            },
            "windDirection": {
                "ecmwf": 207.23,
                "ecmwf:aifs": 211.37,
                "noaa": 177.06,
                "sg": 207.23
            },
            "windSpeed": {
                "ecmwf": 4.74,
                "ecmwf:aifs": 3.18,
                "noaa": 2.59,
                "sg": 4.74
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.34,
                "ecmwf:aifs": 17.23,
                "noaa": 19.54,
                "sg": 17.34
            },
            "cloudCover": {
                "noaa": 95.3,
                "sg": 95.3
            },
            "time": "2025-09-08T03:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.67,
                "sg": 17.67
            },
            "windDirection": {
                "ecmwf": 209.87,
                "ecmwf:aifs": 208.88,
                "noaa": 193.02,
                "sg": 209.87
            },
            "windSpeed": {
                "ecmwf": 4.81,
                "ecmwf:aifs": 3.25,
                "noaa": 2.06,
                "sg": 4.81
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.23,
                "ecmwf:aifs": 16.81,
                "noaa": 19.49,
                "sg": 17.23
            },
            "cloudCover": {
                "noaa": 96.87,
                "sg": 96.87
            },
            "time": "2025-09-08T04:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.09,
                "sg": 18.09
            },
            "windDirection": {
                "ecmwf": 212.51,
                "ecmwf:aifs": 206.38,
                "noaa": 177.39,
                "sg": 212.51
            },
            "windSpeed": {
                "ecmwf": 4.88,
                "ecmwf:aifs": 3.31,
                "noaa": 2.04,
                "sg": 4.88
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.12,
                "ecmwf:aifs": 16.4,
                "noaa": 19.43,
                "sg": 17.12
            },
            "cloudCover": {
                "noaa": 98.43,
                "sg": 98.43
            },
            "time": "2025-09-08T05:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.51,
                "sg": 18.51
            },
            "windDirection": {
                "ecmwf": 215.14,
                "ecmwf:aifs": 203.89,
                "noaa": 161.77,
                "sg": 215.14
            },
            "windSpeed": {
                "ecmwf": 4.94,
                "ecmwf:aifs": 3.37,
                "noaa": 2.01,
                "sg": 4.94
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.01,
                "ecmwf:aifs": 15.98,
                "noaa": 19.38,
                "sg": 17.01
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T06:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.94,
                "sg": 18.94
            },
            "windDirection": {
                "ecmwf": 217.78,
                "ecmwf:aifs": 201.4,
                "noaa": 146.14,
                "sg": 217.78
            },
            "windSpeed": {
                "ecmwf": 5.01,
                "ecmwf:aifs": 3.43,
                "noaa": 1.99,
                "sg": 5.01
            }
        },
        {
            "airTemperature": {
                "ecmwf": 16.9,
                "ecmwf:aifs": 17.14,
                "noaa": 20.4,
                "sg": 16.9
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T07:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.03,
                "sg": 20.03
            },
            "windDirection": {
                "ecmwf": 220.42,
                "ecmwf:aifs": 202.37,
                "noaa": 158.36,
                "sg": 220.42
            },
            "windSpeed": {
                "ecmwf": 5.08,
                "ecmwf:aifs": 3.66,
                "noaa": 1.6,
                "sg": 5.08
            }
        },
        {
            "airTemperature": {
                "ecmwf": 16.8,
                "ecmwf:aifs": 18.29,
                "noaa": 21.43,
                "sg": 16.8
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T08:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.13,
                "sg": 21.13
            },
            "windDirection": {
                "ecmwf": 223.06,
                "ecmwf:aifs": 203.34,
                "noaa": 170.57,
                "sg": 223.06
            },
            "windSpeed": {
                "ecmwf": 5.14,
                "ecmwf:aifs": 3.89,
                "noaa": 1.22,
                "sg": 5.14
            }
        },
        {
            "airTemperature": {
                "ecmwf": 16.69,
                "ecmwf:aifs": 19.44,
                "noaa": 22.45,
                "sg": 16.69
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T09:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.23,
                "sg": 22.23
            },
            "windDirection": {
                "ecmwf": 225.7,
                "ecmwf:aifs": 204.31,
                "noaa": 182.79,
                "sg": 225.7
            },
            "windSpeed": {
                "ecmwf": 5.21,
                "ecmwf:aifs": 4.13,
                "noaa": 0.83,
                "sg": 5.21
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.4,
                "ecmwf:aifs": 20.59,
                "noaa": 24.25,
                "sg": 17.4
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T10:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.19,
                "sg": 26.19
            },
            "windDirection": {
                "ecmwf": 221.44,
                "ecmwf:aifs": 205.28,
                "noaa": 188.35,
                "sg": 221.44
            },
            "windSpeed": {
                "ecmwf": 5.41,
                "ecmwf:aifs": 4.36,
                "noaa": 1.21,
                "sg": 5.41
            }
        },
        {
            "airTemperature": {
                "ecmwf": 18.11,
                "ecmwf:aifs": 21.74,
                "noaa": 26.05,
                "sg": 18.11
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T11:00:00+00:00",
            "waterTemperature": {
                "noaa": 30.15,
                "sg": 30.15
            },
            "windDirection": {
                "ecmwf": 217.17,
                "ecmwf:aifs": 206.25,
                "noaa": 193.91,
                "sg": 217.17
            },
            "windSpeed": {
                "ecmwf": 5.6,
                "ecmwf:aifs": 4.59,
                "noaa": 1.58,
                "sg": 5.6
            }
        },
        {
            "airTemperature": {
                "ecmwf": 18.83,
                "ecmwf:aifs": 22.89,
                "noaa": 27.84,
                "sg": 18.83
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T12:00:00+00:00",
            "waterTemperature": {
                "noaa": 34.11,
                "sg": 34.11
            },
            "windDirection": {
                "ecmwf": 212.91,
                "ecmwf:aifs": 207.22,
                "noaa": 199.47,
                "sg": 212.91
            },
            "windSpeed": {
                "ecmwf": 5.8,
                "ecmwf:aifs": 4.82,
                "noaa": 1.96,
                "sg": 5.8
            }
        },
        {
            "airTemperature": {
                "ecmwf": 19.61,
                "ecmwf:aifs": 22.46,
                "noaa": 26.0,
                "sg": 19.61
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T13:00:00+00:00",
            "waterTemperature": {
                "noaa": 30.0,
                "sg": 30.0
            },
            "windDirection": {
                "ecmwf": 209.81,
                "ecmwf:aifs": 206.97,
                "noaa": 180.98,
                "sg": 209.81
            },
            "windSpeed": {
                "ecmwf": 5.7,
                "ecmwf:aifs": 4.46,
                "noaa": 2.17,
                "sg": 5.7
            }
        },
        {
            "airTemperature": {
                "ecmwf": 20.4,
                "ecmwf:aifs": 22.02,
                "noaa": 24.16,
                "sg": 20.4
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T14:00:00+00:00",
            "waterTemperature": {
                "noaa": 25.88,
                "sg": 25.88
            },
            "windDirection": {
                "ecmwf": 206.72,
                "ecmwf:aifs": 206.72,
                "noaa": 162.49,
                "sg": 206.72
            },
            "windSpeed": {
                "ecmwf": 5.6,
                "ecmwf:aifs": 4.11,
                "noaa": 2.39,
                "sg": 5.6
            }
        },
        {
            "airTemperature": {
                "ecmwf": 21.19,
                "ecmwf:aifs": 21.59,
                "noaa": 22.32,
                "sg": 21.19
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T15:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.77,
                "sg": 21.77
            },
            "windDirection": {
                "ecmwf": 203.62,
                "ecmwf:aifs": 206.47,
                "noaa": 144.0,
                "sg": 203.62
            },
            "windSpeed": {
                "ecmwf": 5.5,
                "ecmwf:aifs": 3.75,
                "noaa": 2.6,
                "sg": 5.5
            }
        },
        {
            "airTemperature": {
                "ecmwf": 20.7,
                "ecmwf:aifs": 21.15,
                "noaa": 21.53,
                "sg": 20.7
            },
            "cloudCover": {
                "noaa": 99.97,
                "sg": 99.97
            },
            "time": "2025-09-08T16:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.91,
                "sg": 20.91
            },
            "windDirection": {
                "ecmwf": 198.64,
                "ecmwf:aifs": 206.23,
                "noaa": 188.74,
                "sg": 198.64
            },
            "windSpeed": {
                "ecmwf": 4.94,
                "ecmwf:aifs": 3.39,
                "noaa": 2.99,
                "sg": 4.94
            }
        },
        {
            "airTemperature": {
                "ecmwf": 20.21,
                "ecmwf:aifs": 20.71,
                "noaa": 20.74,
                "sg": 20.21
            },
            "cloudCover": {
                "noaa": 99.93,
                "sg": 99.93
            },
            "time": "2025-09-08T17:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.05,
                "sg": 20.05
            },
            "windDirection": {
                "ecmwf": 193.65,
                "ecmwf:aifs": 205.98,
                "noaa": 233.47,
                "sg": 193.65
            },
            "windSpeed": {
                "ecmwf": 4.38,
                "ecmwf:aifs": 3.04,
                "noaa": 3.37,
                "sg": 4.38
            }
        },
        {
            "airTemperature": {
                "ecmwf": 19.72,
                "ecmwf:aifs": 20.28,
                "noaa": 19.95,
                "sg": 19.72
            },
            "cloudCover": {
                "noaa": 99.9,
                "sg": 99.9
            },
            "time": "2025-09-08T18:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.19,
                "sg": 19.19
            },
            "windDirection": {
                "ecmwf": 188.67,
                "ecmwf:aifs": 205.73,
                "noaa": 278.21,
                "sg": 188.67
            },
            "windSpeed": {
                "ecmwf": 3.82,
                "ecmwf:aifs": 2.68,
                "noaa": 3.76,
                "sg": 3.82
            }
        },
        {
            "airTemperature": {
                "ecmwf": 19.13,
                "ecmwf:aifs": 19.55,
                "noaa": 18.94,
                "sg": 19.13
            },
            "cloudCover": {
                "noaa": 99.93,
                "sg": 99.93
            },
            "time": "2025-09-08T19:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.46,
                "sg": 18.46
            },
            "windDirection": {
                "ecmwf": 188.7,
                "ecmwf:aifs": 206.63,
                "noaa": 277.73,
                "sg": 188.7
            },
            "windSpeed": {
                "ecmwf": 4.0,
                "ecmwf:aifs": 2.55,
                "noaa": 4.46,
                "sg": 4.0
            }
        },
        {
            "airTemperature": {
                "ecmwf": 18.53,
                "ecmwf:aifs": 18.82,
                "noaa": 17.93,
                "sg": 18.53
            },
            "cloudCover": {
                "noaa": 99.97,
                "sg": 99.97
            },
            "time": "2025-09-08T20:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.73,
                "sg": 17.73
            },
            "windDirection": {
                "ecmwf": 188.73,
                "ecmwf:aifs": 207.53,
                "noaa": 277.24,
                "sg": 188.73
            },
            "windSpeed": {
                "ecmwf": 4.17,
                "ecmwf:aifs": 2.41,
                "noaa": 5.16,
                "sg": 4.17
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.94,
                "ecmwf:aifs": 18.1,
                "noaa": 16.92,
                "sg": 17.94
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T21:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.0,
                "sg": 17.0
            },
            "windDirection": {
                "ecmwf": 188.76,
                "ecmwf:aifs": 208.42,
                "noaa": 276.76,
                "sg": 188.76
            },
            "windSpeed": {
                "ecmwf": 4.35,
                "ecmwf:aifs": 2.28,
                "noaa": 5.86,
                "sg": 4.35
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.64,
                "ecmwf:aifs": 17.37,
                "noaa": 16.51,
                "sg": 17.64
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T22:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.48,
                "sg": 16.48
            },
            "windDirection": {
                "ecmwf": 191.43,
                "ecmwf:aifs": 209.32,
                "noaa": 277.43,
                "sg": 191.43
            },
            "windSpeed": {
                "ecmwf": 4.43,
                "ecmwf:aifs": 2.15,
                "noaa": 5.01,
                "sg": 4.43
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.34,
                "ecmwf:aifs": 16.64,
                "noaa": 16.09,
                "sg": 17.34
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-08T23:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.96,
                "sg": 15.96
            },
            "windDirection": {
                "ecmwf": 194.1,
                "ecmwf:aifs": 210.22,
                "noaa": 278.1,
                "sg": 194.1
            },
            "windSpeed": {
                "ecmwf": 4.51,
                "ecmwf:aifs": 2.01,
                "noaa": 4.16,
                "sg": 4.51
            }
        },
        {
            "airTemperature": {
                "ecmwf": 17.04,
                "ecmwf:aifs": 15.91,
                "noaa": 15.68,
                "sg": 17.04
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.44,
                "sg": 15.44
            },
            "windDirection": {
                "ecmwf": 196.77,
                "ecmwf:aifs": 211.12,
                "noaa": 278.77,
                "sg": 196.77
            },
            "windSpeed": {
                "ecmwf": 4.59,
                "ecmwf:aifs": 1.88,
                "noaa": 3.31,
                "sg": 4.59
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.68,
                "noaa": 15.01,
                "sg": 15.01
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T01:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.68,
                "sg": 14.68
            },
            "windDirection": {
                "ecmwf:aifs": 213.82,
                "noaa": 271.02,
                "sg": 271.02
            },
            "windSpeed": {
                "ecmwf:aifs": 1.91,
                "noaa": 3.3,
                "sg": 3.3
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.44,
                "noaa": 14.34,
                "sg": 14.34
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T02:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.92,
                "sg": 13.92
            },
            "windDirection": {
                "ecmwf:aifs": 216.53,
                "noaa": 263.26,
                "sg": 263.26
            },
            "windSpeed": {
                "ecmwf:aifs": 1.94,
                "noaa": 3.3,
                "sg": 3.3
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.21,
                "noaa": 13.66,
                "sg": 13.66
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T03:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.16,
                "sg": 13.16
            },
            "windDirection": {
                "ecmwf:aifs": 219.24,
                "noaa": 255.51,
                "sg": 255.51
            },
            "windSpeed": {
                "ecmwf:aifs": 1.97,
                "noaa": 3.29,
                "sg": 3.29
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.97,
                "noaa": 13.7,
                "sg": 13.7
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T04:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.26,
                "sg": 13.26
            },
            "windDirection": {
                "ecmwf:aifs": 221.94,
                "noaa": 243.37,
                "sg": 243.37
            },
            "windSpeed": {
                "ecmwf:aifs": 2.0,
                "noaa": 2.96,
                "sg": 2.96
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.74,
                "noaa": 13.73,
                "sg": 13.73
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T05:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.36,
                "sg": 13.36
            },
            "windDirection": {
                "ecmwf:aifs": 224.65,
                "noaa": 231.24,
                "sg": 231.24
            },
            "windSpeed": {
                "ecmwf:aifs": 2.03,
                "noaa": 2.62,
                "sg": 2.62
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.51,
                "noaa": 13.76,
                "sg": 13.76
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T06:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.46,
                "sg": 13.46
            },
            "windDirection": {
                "ecmwf:aifs": 227.35,
                "noaa": 219.1,
                "sg": 219.1
            },
            "windSpeed": {
                "ecmwf:aifs": 2.06,
                "noaa": 2.29,
                "sg": 2.29
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.55,
                "noaa": 15.33,
                "sg": 15.33
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T07:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.85,
                "sg": 15.85
            },
            "windDirection": {
                "ecmwf:aifs": 227.14,
                "noaa": 220.01,
                "sg": 220.01
            },
            "windSpeed": {
                "ecmwf:aifs": 2.34,
                "noaa": 2.72,
                "sg": 2.72
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 16.59,
                "noaa": 16.9,
                "sg": 16.9
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T08:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.25,
                "sg": 18.25
            },
            "windDirection": {
                "ecmwf:aifs": 226.92,
                "noaa": 220.92,
                "sg": 220.92
            },
            "windSpeed": {
                "ecmwf:aifs": 2.61,
                "noaa": 3.15,
                "sg": 3.15
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 17.63,
                "noaa": 18.46,
                "sg": 18.46
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T09:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.64,
                "sg": 20.64
            },
            "windDirection": {
                "ecmwf:aifs": 226.71,
                "noaa": 221.83,
                "sg": 221.83
            },
            "windSpeed": {
                "ecmwf:aifs": 2.89,
                "noaa": 3.58,
                "sg": 3.58
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 18.68,
                "noaa": 19.4,
                "sg": 19.4
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T10:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.91,
                "sg": 21.91
            },
            "windDirection": {
                "ecmwf:aifs": 226.49,
                "noaa": 226.61,
                "sg": 226.61
            },
            "windSpeed": {
                "ecmwf:aifs": 3.17,
                "noaa": 3.79,
                "sg": 3.79
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 19.72,
                "noaa": 20.33,
                "sg": 20.33
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T11:00:00+00:00",
            "waterTemperature": {
                "noaa": 23.19,
                "sg": 23.19
            },
            "windDirection": {
                "ecmwf:aifs": 226.27,
                "noaa": 231.38,
                "sg": 231.38
            },
            "windSpeed": {
                "ecmwf:aifs": 3.44,
                "noaa": 4.0,
                "sg": 4.0
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 20.76,
                "noaa": 21.26,
                "sg": 21.26
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T12:00:00+00:00",
            "waterTemperature": {
                "noaa": 24.46,
                "sg": 24.46
            },
            "windDirection": {
                "ecmwf:aifs": 226.06,
                "noaa": 236.16,
                "sg": 236.16
            },
            "windSpeed": {
                "ecmwf:aifs": 3.72,
                "noaa": 4.21,
                "sg": 4.21
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 20.45,
                "noaa": 21.13,
                "sg": 21.13
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T13:00:00+00:00",
            "waterTemperature": {
                "noaa": 23.56,
                "sg": 23.56
            },
            "windDirection": {
                "ecmwf:aifs": 222.61,
                "noaa": 232.12,
                "sg": 232.12
            },
            "windSpeed": {
                "ecmwf:aifs": 3.57,
                "noaa": 3.98,
                "sg": 3.98
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 20.14,
                "noaa": 21.0,
                "sg": 21.0
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T14:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.66,
                "sg": 22.66
            },
            "windDirection": {
                "ecmwf:aifs": 219.17,
                "noaa": 228.08,
                "sg": 228.08
            },
            "windSpeed": {
                "ecmwf:aifs": 3.42,
                "noaa": 3.75,
                "sg": 3.75
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 19.82,
                "noaa": 20.87,
                "sg": 20.87
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-09T15:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.76,
                "sg": 21.76
            },
            "windDirection": {
                "ecmwf:aifs": 215.72,
                "noaa": 224.04,
                "sg": 224.04
            },
            "windSpeed": {
                "ecmwf:aifs": 3.27,
                "noaa": 3.52,
                "sg": 3.52
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 19.51,
                "noaa": 19.87,
                "sg": 19.87
            },
            "cloudCover": {
                "noaa": 86.23,
                "sg": 86.23
            },
            "time": "2025-09-09T16:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.84,
                "sg": 19.84
            },
            "windDirection": {
                "ecmwf:aifs": 212.27,
                "noaa": 215.17,
                "sg": 215.17
            },
            "windSpeed": {
                "ecmwf:aifs": 3.13,
                "noaa": 3.19,
                "sg": 3.19
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 19.2,
                "noaa": 18.87,
                "sg": 18.87
            },
            "cloudCover": {
                "noaa": 72.47,
                "sg": 72.47
            },
            "time": "2025-09-09T17:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.91,
                "sg": 17.91
            },
            "windDirection": {
                "ecmwf:aifs": 208.83,
                "noaa": 206.31,
                "sg": 206.31
            },
            "windSpeed": {
                "ecmwf:aifs": 2.98,
                "noaa": 2.85,
                "sg": 2.85
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 18.88,
                "noaa": 17.86,
                "sg": 17.86
            },
            "cloudCover": {
                "noaa": 58.7,
                "sg": 58.7
            },
            "time": "2025-09-09T18:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.99,
                "sg": 15.99
            },
            "windDirection": {
                "ecmwf:aifs": 205.38,
                "noaa": 197.44,
                "sg": 197.44
            },
            "windSpeed": {
                "ecmwf:aifs": 2.83,
                "noaa": 2.52,
                "sg": 2.52
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 18.13,
                "noaa": 16.96,
                "sg": 16.96
            },
            "cloudCover": {
                "noaa": 50.7,
                "sg": 50.7
            },
            "time": "2025-09-09T19:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.11,
                "sg": 15.11
            },
            "windDirection": {
                "ecmwf:aifs": 201.35,
                "noaa": 206.28,
                "sg": 206.28
            },
            "windSpeed": {
                "ecmwf:aifs": 3.14,
                "noaa": 2.52,
                "sg": 2.52
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 17.37,
                "noaa": 16.06,
                "sg": 16.06
            },
            "cloudCover": {
                "noaa": 42.7,
                "sg": 42.7
            },
            "time": "2025-09-09T20:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.24,
                "sg": 14.24
            },
            "windDirection": {
                "ecmwf:aifs": 197.31,
                "noaa": 215.11,
                "sg": 215.11
            },
            "windSpeed": {
                "ecmwf:aifs": 3.46,
                "noaa": 2.51,
                "sg": 2.51
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 16.61,
                "noaa": 15.16,
                "sg": 15.16
            },
            "cloudCover": {
                "noaa": 34.7,
                "sg": 34.7
            },
            "time": "2025-09-09T21:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.37,
                "sg": 13.37
            },
            "windDirection": {
                "ecmwf:aifs": 193.28,
                "noaa": 223.95,
                "sg": 223.95
            },
            "windSpeed": {
                "ecmwf:aifs": 3.77,
                "noaa": 2.51,
                "sg": 2.51
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.85,
                "noaa": 15.27,
                "sg": 15.27
            },
            "cloudCover": {
                "noaa": 56.4,
                "sg": 56.4
            },
            "time": "2025-09-09T22:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.66,
                "sg": 13.66
            },
            "windDirection": {
                "ecmwf:aifs": 189.25,
                "noaa": 225.81,
                "sg": 225.81
            },
            "windSpeed": {
                "ecmwf:aifs": 4.08,
                "noaa": 2.56,
                "sg": 2.56
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.09,
                "noaa": 15.37,
                "sg": 15.37
            },
            "cloudCover": {
                "noaa": 78.1,
                "sg": 78.1
            },
            "time": "2025-09-09T23:00:00+00:00",
            "waterTemperature": {
                "noaa": 13.96,
                "sg": 13.96
            },
            "windDirection": {
                "ecmwf:aifs": 185.21,
                "noaa": 227.67,
                "sg": 227.67
            },
            "windSpeed": {
                "ecmwf:aifs": 4.4,
                "noaa": 2.6,
                "sg": 2.6
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.33,
                "noaa": 15.47,
                "sg": 15.47
            },
            "cloudCover": {
                "noaa": 99.8,
                "sg": 99.8
            },
            "time": "2025-09-10T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.25,
                "sg": 14.25
            },
            "windDirection": {
                "ecmwf:aifs": 181.18,
                "noaa": 229.53,
                "sg": 229.53
            },
            "windSpeed": {
                "ecmwf:aifs": 4.71,
                "noaa": 2.65,
                "sg": 2.65
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.32,
                "noaa": 15.77,
                "sg": 15.77
            },
            "cloudCover": {
                "noaa": 99.83,
                "sg": 99.83
            },
            "time": "2025-09-10T01:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.69,
                "sg": 14.69
            },
            "windDirection": {
                "ecmwf:aifs": 180.48,
                "noaa": 214.04,
                "sg": 214.04
            },
            "windSpeed": {
                "ecmwf:aifs": 5.08,
                "noaa": 2.62,
                "sg": 2.62
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.31,
                "noaa": 16.06,
                "sg": 16.06
            },
            "cloudCover": {
                "noaa": 99.87,
                "sg": 99.87
            },
            "time": "2025-09-10T02:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.12,
                "sg": 15.12
            },
            "windDirection": {
                "ecmwf:aifs": 179.79,
                "noaa": 198.54,
                "sg": 198.54
            },
            "windSpeed": {
                "ecmwf:aifs": 5.46,
                "noaa": 2.59,
                "sg": 2.59
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.3,
                "noaa": 16.36,
                "sg": 16.36
            },
            "cloudCover": {
                "noaa": 99.9,
                "sg": 99.9
            },
            "time": "2025-09-10T03:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.55,
                "sg": 15.55
            },
            "windDirection": {
                "ecmwf:aifs": 179.09,
                "noaa": 183.05,
                "sg": 183.05
            },
            "windSpeed": {
                "ecmwf:aifs": 5.83,
                "noaa": 2.56,
                "sg": 2.56
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.29,
                "noaa": 15.83,
                "sg": 15.83
            },
            "cloudCover": {
                "noaa": 69.13,
                "sg": 69.13
            },
            "time": "2025-09-10T04:00:00+00:00",
            "waterTemperature": {
                "noaa": 15.1,
                "sg": 15.1
            },
            "windDirection": {
                "ecmwf:aifs": 178.39,
                "noaa": 191.11,
                "sg": 191.11
            },
            "windSpeed": {
                "ecmwf:aifs": 6.2,
                "noaa": 2.89,
                "sg": 2.89
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.28,
                "noaa": 15.29,
                "sg": 15.29
            },
            "cloudCover": {
                "noaa": 38.37,
                "sg": 38.37
            },
            "time": "2025-09-10T05:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.64,
                "sg": 14.64
            },
            "windDirection": {
                "ecmwf:aifs": 177.7,
                "noaa": 199.16,
                "sg": 199.16
            },
            "windSpeed": {
                "ecmwf:aifs": 6.58,
                "noaa": 3.21,
                "sg": 3.21
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.27,
                "noaa": 14.76,
                "sg": 14.76
            },
            "cloudCover": {
                "noaa": 7.6,
                "sg": 7.6
            },
            "time": "2025-09-10T06:00:00+00:00",
            "waterTemperature": {
                "noaa": 14.19,
                "sg": 14.19
            },
            "windDirection": {
                "ecmwf:aifs": 177.0,
                "noaa": 207.22,
                "sg": 207.22
            },
            "windSpeed": {
                "ecmwf:aifs": 6.95,
                "noaa": 3.54,
                "sg": 3.54
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.55,
                "noaa": 16.62,
                "sg": 16.62
            },
            "cloudCover": {
                "noaa": 6.57,
                "sg": 6.57
            },
            "time": "2025-09-10T07:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.18,
                "sg": 17.18
            },
            "windDirection": {
                "ecmwf:aifs": 179.7,
                "noaa": 210.88,
                "sg": 210.88
            },
            "windSpeed": {
                "ecmwf:aifs": 7.06,
                "noaa": 4.35,
                "sg": 4.35
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.84,
                "noaa": 18.49,
                "sg": 18.49
            },
            "cloudCover": {
                "noaa": 5.53,
                "sg": 5.53
            },
            "time": "2025-09-10T08:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.17,
                "sg": 20.17
            },
            "windDirection": {
                "ecmwf:aifs": 182.4,
                "noaa": 214.55,
                "sg": 214.55
            },
            "windSpeed": {
                "ecmwf:aifs": 7.18,
                "noaa": 5.16,
                "sg": 5.16
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.12,
                "noaa": 20.35,
                "sg": 20.35
            },
            "cloudCover": {
                "noaa": 4.5,
                "sg": 4.5
            },
            "time": "2025-09-10T09:00:00+00:00",
            "waterTemperature": {
                "noaa": 23.16,
                "sg": 23.16
            },
            "windDirection": {
                "ecmwf:aifs": 185.11,
                "noaa": 218.21,
                "sg": 218.21
            },
            "windSpeed": {
                "ecmwf:aifs": 7.29,
                "noaa": 5.97,
                "sg": 5.97
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.41,
                "noaa": 21.42,
                "sg": 21.42
            },
            "cloudCover": {
                "noaa": 4.67,
                "sg": 4.67
            },
            "time": "2025-09-10T10:00:00+00:00",
            "waterTemperature": {
                "noaa": 24.57,
                "sg": 24.57
            },
            "windDirection": {
                "ecmwf:aifs": 187.81,
                "noaa": 218.74,
                "sg": 218.74
            },
            "windSpeed": {
                "ecmwf:aifs": 7.41,
                "noaa": 6.14,
                "sg": 6.14
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.7,
                "noaa": 22.48,
                "sg": 22.48
            },
            "cloudCover": {
                "noaa": 4.83,
                "sg": 4.83
            },
            "time": "2025-09-10T11:00:00+00:00",
            "waterTemperature": {
                "noaa": 25.99,
                "sg": 25.99
            },
            "windDirection": {
                "ecmwf:aifs": 190.51,
                "noaa": 219.26,
                "sg": 219.26
            },
            "windSpeed": {
                "ecmwf:aifs": 7.52,
                "noaa": 6.32,
                "sg": 6.32
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.98,
                "noaa": 23.55,
                "sg": 23.55
            },
            "cloudCover": {
                "noaa": 5.0,
                "sg": 5.0
            },
            "time": "2025-09-10T12:00:00+00:00",
            "waterTemperature": {
                "noaa": 27.41,
                "sg": 27.41
            },
            "windDirection": {
                "ecmwf:aifs": 193.21,
                "noaa": 219.79,
                "sg": 219.79
            },
            "windSpeed": {
                "ecmwf:aifs": 7.64,
                "noaa": 6.49,
                "sg": 6.49
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.89,
                "noaa": 23.33,
                "sg": 23.33
            },
            "cloudCover": {
                "noaa": 34.83,
                "sg": 34.83
            },
            "time": "2025-09-10T13:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.63,
                "sg": 26.63
            },
            "windDirection": {
                "ecmwf:aifs": 195.79,
                "noaa": 222.56,
                "sg": 222.56
            },
            "windSpeed": {
                "ecmwf:aifs": 7.12,
                "noaa": 6.63,
                "sg": 6.63
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.8,
                "noaa": 23.1,
                "sg": 23.1
            },
            "cloudCover": {
                "noaa": 64.67,
                "sg": 64.67
            },
            "time": "2025-09-10T14:00:00+00:00",
            "waterTemperature": {
                "noaa": 25.85,
                "sg": 25.85
            },
            "windDirection": {
                "ecmwf:aifs": 198.36,
                "noaa": 225.32,
                "sg": 225.32
            },
            "windSpeed": {
                "ecmwf:aifs": 6.61,
                "noaa": 6.77,
                "sg": 6.77
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.71,
                "noaa": 22.88,
                "sg": 22.88
            },
            "cloudCover": {
                "noaa": 94.5,
                "sg": 94.5
            },
            "time": "2025-09-10T15:00:00+00:00",
            "waterTemperature": {
                "noaa": 25.07,
                "sg": 25.07
            },
            "windDirection": {
                "ecmwf:aifs": 200.94,
                "noaa": 228.09,
                "sg": 228.09
            },
            "windSpeed": {
                "ecmwf:aifs": 6.1,
                "noaa": 6.91,
                "sg": 6.91
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.62,
                "noaa": 22.17,
                "sg": 22.17
            },
            "cloudCover": {
                "noaa": 96.33,
                "sg": 96.33
            },
            "time": "2025-09-10T16:00:00+00:00",
            "waterTemperature": {
                "noaa": 23.39,
                "sg": 23.39
            },
            "windDirection": {
                "ecmwf:aifs": 203.51,
                "noaa": 217.15,
                "sg": 217.15
            },
            "windSpeed": {
                "ecmwf:aifs": 5.58,
                "noaa": 5.85,
                "sg": 5.85
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.53,
                "noaa": 21.47,
                "sg": 21.47
            },
            "cloudCover": {
                "noaa": 98.17,
                "sg": 98.17
            },
            "time": "2025-09-10T17:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.71,
                "sg": 21.71
            },
            "windDirection": {
                "ecmwf:aifs": 206.09,
                "noaa": 206.21,
                "sg": 206.21
            },
            "windSpeed": {
                "ecmwf:aifs": 5.07,
                "noaa": 4.8,
                "sg": 4.8
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.44,
                "noaa": 20.76,
                "sg": 20.76
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-10T18:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.04,
                "sg": 20.04
            },
            "windDirection": {
                "ecmwf:aifs": 208.66,
                "noaa": 195.27,
                "sg": 195.27
            },
            "windSpeed": {
                "ecmwf:aifs": 4.55,
                "noaa": 3.74,
                "sg": 3.74
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.94,
                "noaa": 20.37,
                "sg": 20.37
            },
            "cloudCover": {
                "noaa": 87.0,
                "sg": 87.0
            },
            "time": "2025-09-10T19:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.81,
                "sg": 19.81
            },
            "windDirection": {
                "ecmwf:aifs": 209.35,
                "noaa": 199.48,
                "sg": 199.48
            },
            "windSpeed": {
                "ecmwf:aifs": 4.7,
                "noaa": 4.23,
                "sg": 4.23
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.44,
                "noaa": 19.98,
                "sg": 19.98
            },
            "cloudCover": {
                "noaa": 74.0,
                "sg": 74.0
            },
            "time": "2025-09-10T20:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.58,
                "sg": 19.58
            },
            "windDirection": {
                "ecmwf:aifs": 210.05,
                "noaa": 203.69,
                "sg": 203.69
            },
            "windSpeed": {
                "ecmwf:aifs": 4.86,
                "noaa": 4.73,
                "sg": 4.73
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 13.95,
                "noaa": 19.59,
                "sg": 19.59
            },
            "cloudCover": {
                "noaa": 61.0,
                "sg": 61.0
            },
            "time": "2025-09-10T21:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.35,
                "sg": 19.35
            },
            "windDirection": {
                "ecmwf:aifs": 210.74,
                "noaa": 207.9,
                "sg": 207.9
            },
            "windSpeed": {
                "ecmwf:aifs": 5.01,
                "noaa": 5.22,
                "sg": 5.22
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 13.45,
                "noaa": 18.88,
                "sg": 18.88
            },
            "cloudCover": {
                "noaa": 73.43,
                "sg": 73.43
            },
            "time": "2025-09-10T22:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.68,
                "sg": 18.68
            },
            "windDirection": {
                "ecmwf:aifs": 211.43,
                "noaa": 209.68,
                "sg": 209.68
            },
            "windSpeed": {
                "ecmwf:aifs": 5.16,
                "noaa": 5.04,
                "sg": 5.04
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.95,
                "noaa": 18.17,
                "sg": 18.17
            },
            "cloudCover": {
                "noaa": 85.87,
                "sg": 85.87
            },
            "time": "2025-09-10T23:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.02,
                "sg": 18.02
            },
            "windDirection": {
                "ecmwf:aifs": 212.13,
                "noaa": 211.46,
                "sg": 211.46
            },
            "windSpeed": {
                "ecmwf:aifs": 5.32,
                "noaa": 4.85,
                "sg": 4.85
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.45,
                "noaa": 17.45,
                "sg": 17.45
            },
            "cloudCover": {
                "noaa": 98.3,
                "sg": 98.3
            },
            "time": "2025-09-11T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.35,
                "sg": 17.35
            },
            "windDirection": {
                "ecmwf:aifs": 212.82,
                "noaa": 213.24,
                "sg": 213.24
            },
            "windSpeed": {
                "ecmwf:aifs": 5.47,
                "noaa": 4.67,
                "sg": 4.67
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.39,
                "noaa": 17.63,
                "sg": 17.63
            },
            "cloudCover": {
                "noaa": 98.87,
                "sg": 98.87
            },
            "time": "2025-09-11T01:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.58,
                "sg": 17.58
            },
            "windDirection": {
                "ecmwf:aifs": 213.11,
                "noaa": 210.83,
                "sg": 210.83
            },
            "windSpeed": {
                "ecmwf:aifs": 5.61,
                "noaa": 4.38,
                "sg": 4.38
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.34,
                "noaa": 17.81,
                "sg": 17.81
            },
            "cloudCover": {
                "noaa": 99.43,
                "sg": 99.43
            },
            "time": "2025-09-11T02:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.81,
                "sg": 17.81
            },
            "windDirection": {
                "ecmwf:aifs": 213.4,
                "noaa": 208.43,
                "sg": 208.43
            },
            "windSpeed": {
                "ecmwf:aifs": 5.76,
                "noaa": 4.09,
                "sg": 4.09
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.28,
                "noaa": 17.99,
                "sg": 17.99
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T03:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.04,
                "sg": 18.04
            },
            "windDirection": {
                "ecmwf:aifs": 213.69,
                "noaa": 206.02,
                "sg": 206.02
            },
            "windSpeed": {
                "ecmwf:aifs": 5.9,
                "noaa": 3.8,
                "sg": 3.8
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.22,
                "noaa": 17.87,
                "sg": 17.87
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T04:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.87,
                "sg": 17.87
            },
            "windDirection": {
                "ecmwf:aifs": 213.98,
                "noaa": 202.21,
                "sg": 202.21
            },
            "windSpeed": {
                "ecmwf:aifs": 6.05,
                "noaa": 3.6,
                "sg": 3.6
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.16,
                "noaa": 17.75,
                "sg": 17.75
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T05:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.71,
                "sg": 17.71
            },
            "windDirection": {
                "ecmwf:aifs": 214.27,
                "noaa": 198.4,
                "sg": 198.4
            },
            "windSpeed": {
                "ecmwf:aifs": 6.2,
                "noaa": 3.39,
                "sg": 3.39
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.1,
                "noaa": 17.63,
                "sg": 17.63
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T06:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.54,
                "sg": 17.54
            },
            "windDirection": {
                "ecmwf:aifs": 214.56,
                "noaa": 194.59,
                "sg": 194.59
            },
            "windSpeed": {
                "ecmwf:aifs": 6.34,
                "noaa": 3.19,
                "sg": 3.19
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.67,
                "noaa": 18.77,
                "sg": 18.77
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T07:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.51,
                "sg": 19.51
            },
            "windDirection": {
                "ecmwf:aifs": 217.18,
                "noaa": 194.79,
                "sg": 194.79
            },
            "windSpeed": {
                "ecmwf:aifs": 6.46,
                "noaa": 3.63,
                "sg": 3.63
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 13.24,
                "noaa": 19.92,
                "sg": 19.92
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T08:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.48,
                "sg": 21.48
            },
            "windDirection": {
                "ecmwf:aifs": 219.79,
                "noaa": 195.0,
                "sg": 195.0
            },
            "windSpeed": {
                "ecmwf:aifs": 6.59,
                "noaa": 4.07,
                "sg": 4.07
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 13.81,
                "noaa": 21.06,
                "sg": 21.06
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T09:00:00+00:00",
            "waterTemperature": {
                "noaa": 23.45,
                "sg": 23.45
            },
            "windDirection": {
                "ecmwf:aifs": 222.41,
                "noaa": 195.2,
                "sg": 195.2
            },
            "windSpeed": {
                "ecmwf:aifs": 6.71,
                "noaa": 4.51,
                "sg": 4.51
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.38,
                "noaa": 21.36,
                "sg": 21.36
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T10:00:00+00:00",
            "waterTemperature": {
                "noaa": 23.2,
                "sg": 23.2
            },
            "windDirection": {
                "ecmwf:aifs": 225.03,
                "noaa": 201.44,
                "sg": 201.44
            },
            "windSpeed": {
                "ecmwf:aifs": 6.83,
                "noaa": 4.66,
                "sg": 4.66
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.95,
                "noaa": 21.65,
                "sg": 21.65
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T11:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.95,
                "sg": 22.95
            },
            "windDirection": {
                "ecmwf:aifs": 227.64,
                "noaa": 207.68,
                "sg": 207.68
            },
            "windSpeed": {
                "ecmwf:aifs": 6.96,
                "noaa": 4.8,
                "sg": 4.8
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.52,
                "noaa": 21.94,
                "sg": 21.94
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T12:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.7,
                "sg": 22.7
            },
            "windDirection": {
                "ecmwf:aifs": 230.26,
                "noaa": 213.92,
                "sg": 213.92
            },
            "windSpeed": {
                "ecmwf:aifs": 7.08,
                "noaa": 4.95,
                "sg": 4.95
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.25,
                "noaa": 21.35,
                "sg": 21.35
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T13:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.91,
                "sg": 21.91
            },
            "windDirection": {
                "ecmwf:aifs": 230.79,
                "noaa": 221.39,
                "sg": 221.39
            },
            "windSpeed": {
                "ecmwf:aifs": 6.82,
                "noaa": 4.3,
                "sg": 4.3
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.98,
                "noaa": 20.76,
                "sg": 20.76
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T14:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.13,
                "sg": 21.13
            },
            "windDirection": {
                "ecmwf:aifs": 231.31,
                "noaa": 228.87,
                "sg": 228.87
            },
            "windSpeed": {
                "ecmwf:aifs": 6.55,
                "noaa": 3.65,
                "sg": 3.65
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.71,
                "noaa": 20.16,
                "sg": 20.16
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T15:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.34,
                "sg": 20.34
            },
            "windDirection": {
                "ecmwf:aifs": 231.84,
                "noaa": 236.34,
                "sg": 236.34
            },
            "windSpeed": {
                "ecmwf:aifs": 6.29,
                "noaa": 3.0,
                "sg": 3.0
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.44,
                "noaa": 19.41,
                "sg": 19.41
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T16:00:00+00:00",
            "waterTemperature": {
                "noaa": 19.34,
                "sg": 19.34
            },
            "windDirection": {
                "ecmwf:aifs": 232.37,
                "noaa": 230.09,
                "sg": 230.09
            },
            "windSpeed": {
                "ecmwf:aifs": 6.03,
                "noaa": 2.48,
                "sg": 2.48
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.17,
                "noaa": 18.66,
                "sg": 18.66
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T17:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.34,
                "sg": 18.34
            },
            "windDirection": {
                "ecmwf:aifs": 232.89,
                "noaa": 223.84,
                "sg": 223.84
            },
            "windSpeed": {
                "ecmwf:aifs": 5.76,
                "noaa": 1.97,
                "sg": 1.97
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 13.9,
                "noaa": 17.91,
                "sg": 17.91
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T18:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.34,
                "sg": 17.34
            },
            "windDirection": {
                "ecmwf:aifs": 233.42,
                "noaa": 217.59,
                "sg": 217.59
            },
            "windSpeed": {
                "ecmwf:aifs": 5.5,
                "noaa": 1.45,
                "sg": 1.45
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 13.48,
                "noaa": 17.95,
                "sg": 17.95
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T19:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.37,
                "sg": 17.37
            },
            "windDirection": {
                "ecmwf:aifs": 232.77,
                "noaa": 206.45,
                "sg": 206.45
            },
            "windSpeed": {
                "ecmwf:aifs": 5.48,
                "noaa": 1.47,
                "sg": 1.47
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 13.06,
                "noaa": 17.99,
                "sg": 17.99
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T20:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.4,
                "sg": 17.4
            },
            "windDirection": {
                "ecmwf:aifs": 232.12,
                "noaa": 195.3,
                "sg": 195.3
            },
            "windSpeed": {
                "ecmwf:aifs": 5.47,
                "noaa": 1.49,
                "sg": 1.49
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.64,
                "noaa": 18.03,
                "sg": 18.03
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T21:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.43,
                "sg": 17.43
            },
            "windDirection": {
                "ecmwf:aifs": 231.46,
                "noaa": 184.16,
                "sg": 184.16
            },
            "windSpeed": {
                "ecmwf:aifs": 5.45,
                "noaa": 1.51,
                "sg": 1.51
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.22,
                "noaa": 17.92,
                "sg": 17.92
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T22:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.32,
                "sg": 17.32
            },
            "windDirection": {
                "ecmwf:aifs": 230.81,
                "noaa": 188.19,
                "sg": 188.19
            },
            "windSpeed": {
                "ecmwf:aifs": 5.44,
                "noaa": 1.1,
                "sg": 1.1
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 11.8,
                "noaa": 17.81,
                "sg": 17.81
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-11T23:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.22,
                "sg": 17.22
            },
            "windDirection": {
                "ecmwf:aifs": 230.16,
                "noaa": 192.21,
                "sg": 192.21
            },
            "windSpeed": {
                "ecmwf:aifs": 5.42,
                "noaa": 0.7,
                "sg": 0.7
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 11.38,
                "noaa": 17.7,
                "sg": 17.7
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.11,
                "sg": 17.11
            },
            "windDirection": {
                "ecmwf:aifs": 229.51,
                "noaa": 196.24,
                "sg": 196.24
            },
            "windSpeed": {
                "ecmwf:aifs": 5.41,
                "noaa": 0.29,
                "sg": 0.29
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 11.27,
                "noaa": 17.48,
                "sg": 17.48
            },
            "cloudCover": {
                "noaa": 90.7,
                "sg": 90.7
            },
            "time": "2025-09-12T01:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.74,
                "sg": 16.74
            },
            "windDirection": {
                "ecmwf:aifs": 228.35,
                "noaa": 162.69,
                "sg": 162.69
            },
            "windSpeed": {
                "ecmwf:aifs": 5.34,
                "noaa": 0.67,
                "sg": 0.67
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 11.17,
                "noaa": 17.25,
                "sg": 17.25
            },
            "cloudCover": {
                "noaa": 81.4,
                "sg": 81.4
            },
            "time": "2025-09-12T02:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.38,
                "sg": 16.38
            },
            "windDirection": {
                "ecmwf:aifs": 227.18,
                "noaa": 129.15,
                "sg": 129.15
            },
            "windSpeed": {
                "ecmwf:aifs": 5.26,
                "noaa": 1.04,
                "sg": 1.04
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 11.06,
                "noaa": 17.03,
                "sg": 17.03
            },
            "cloudCover": {
                "noaa": 72.1,
                "sg": 72.1
            },
            "time": "2025-09-12T03:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.01,
                "sg": 16.01
            },
            "windDirection": {
                "ecmwf:aifs": 226.02,
                "noaa": 95.6,
                "sg": 95.6
            },
            "windSpeed": {
                "ecmwf:aifs": 5.19,
                "noaa": 1.42,
                "sg": 1.42
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 10.95,
                "noaa": 17.26,
                "sg": 17.26
            },
            "cloudCover": {
                "noaa": 81.4,
                "sg": 81.4
            },
            "time": "2025-09-12T04:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.44,
                "sg": 16.44
            },
            "windDirection": {
                "ecmwf:aifs": 224.86,
                "noaa": 109.59,
                "sg": 109.59
            },
            "windSpeed": {
                "ecmwf:aifs": 5.12,
                "noaa": 1.44,
                "sg": 1.44
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 10.85,
                "noaa": 17.49,
                "sg": 17.49
            },
            "cloudCover": {
                "noaa": 90.7,
                "sg": 90.7
            },
            "time": "2025-09-12T05:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.86,
                "sg": 16.86
            },
            "windDirection": {
                "ecmwf:aifs": 223.69,
                "noaa": 123.57,
                "sg": 123.57
            },
            "windSpeed": {
                "ecmwf:aifs": 5.04,
                "noaa": 1.46,
                "sg": 1.46
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 10.74,
                "noaa": 17.72,
                "sg": 17.72
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T06:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.29,
                "sg": 17.29
            },
            "windDirection": {
                "ecmwf:aifs": 222.53,
                "noaa": 137.56,
                "sg": 137.56
            },
            "windSpeed": {
                "ecmwf:aifs": 4.97,
                "noaa": 1.48,
                "sg": 1.48
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 11.82,
                "noaa": 18.7,
                "sg": 18.7
            },
            "cloudCover": {
                "noaa": 99.87,
                "sg": 99.87
            },
            "time": "2025-09-12T07:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.94,
                "sg": 18.94
            },
            "windDirection": {
                "ecmwf:aifs": 224.7,
                "noaa": 175.25,
                "sg": 175.25
            },
            "windSpeed": {
                "ecmwf:aifs": 5.06,
                "noaa": 1.5,
                "sg": 1.5
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.91,
                "noaa": 19.67,
                "sg": 19.67
            },
            "cloudCover": {
                "noaa": 99.73,
                "sg": 99.73
            },
            "time": "2025-09-12T08:00:00+00:00",
            "waterTemperature": {
                "noaa": 20.6,
                "sg": 20.6
            },
            "windDirection": {
                "ecmwf:aifs": 226.87,
                "noaa": 212.93,
                "sg": 212.93
            },
            "windSpeed": {
                "ecmwf:aifs": 5.15,
                "noaa": 1.53,
                "sg": 1.53
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.0,
                "noaa": 20.65,
                "sg": 20.65
            },
            "cloudCover": {
                "noaa": 99.6,
                "sg": 99.6
            },
            "time": "2025-09-12T09:00:00+00:00",
            "waterTemperature": {
                "noaa": 22.25,
                "sg": 22.25
            },
            "windDirection": {
                "ecmwf:aifs": 229.04,
                "noaa": 250.62,
                "sg": 250.62
            },
            "windSpeed": {
                "ecmwf:aifs": 5.24,
                "noaa": 1.55,
                "sg": 1.55
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.08,
                "noaa": 21.22,
                "sg": 21.22
            },
            "cloudCover": {
                "noaa": 90.6,
                "sg": 90.6
            },
            "time": "2025-09-12T10:00:00+00:00",
            "waterTemperature": {
                "noaa": 23.52,
                "sg": 23.52
            },
            "windDirection": {
                "ecmwf:aifs": 231.22,
                "noaa": 265.47,
                "sg": 265.47
            },
            "windSpeed": {
                "ecmwf:aifs": 5.34,
                "noaa": 1.65,
                "sg": 1.65
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 16.17,
                "noaa": 21.78,
                "sg": 21.78
            },
            "cloudCover": {
                "noaa": 81.6,
                "sg": 81.6
            },
            "time": "2025-09-12T11:00:00+00:00",
            "waterTemperature": {
                "noaa": 24.78,
                "sg": 24.78
            },
            "windDirection": {
                "ecmwf:aifs": 233.39,
                "noaa": 280.32,
                "sg": 280.32
            },
            "windSpeed": {
                "ecmwf:aifs": 5.43,
                "noaa": 1.74,
                "sg": 1.74
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 17.26,
                "noaa": 22.35,
                "sg": 22.35
            },
            "cloudCover": {
                "noaa": 72.6,
                "sg": 72.6
            },
            "time": "2025-09-12T12:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.05,
                "sg": 26.05
            },
            "windDirection": {
                "ecmwf:aifs": 235.56,
                "noaa": 295.17,
                "sg": 295.17
            },
            "windSpeed": {
                "ecmwf:aifs": 5.52,
                "noaa": 1.84,
                "sg": 1.84
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 16.93,
                "noaa": 23.15,
                "sg": 23.15
            },
            "cloudCover": {
                "noaa": 81.73,
                "sg": 81.73
            },
            "time": "2025-09-12T13:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.38,
                "sg": 26.38
            },
            "windDirection": {
                "ecmwf:aifs": 233.26,
                "noaa": 302.58,
                "sg": 302.58
            },
            "windSpeed": {
                "ecmwf:aifs": 5.06,
                "noaa": 1.97,
                "sg": 1.97
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 16.6,
                "noaa": 23.94,
                "sg": 23.94
            },
            "cloudCover": {
                "noaa": 90.87,
                "sg": 90.87
            },
            "time": "2025-09-12T14:00:00+00:00",
            "waterTemperature": {
                "noaa": 26.71,
                "sg": 26.71
            },
            "windDirection": {
                "ecmwf:aifs": 230.97,
                "noaa": 309.98,
                "sg": 309.98
            },
            "windSpeed": {
                "ecmwf:aifs": 4.6,
                "noaa": 2.11,
                "sg": 2.11
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 16.27,
                "noaa": 24.74,
                "sg": 24.74
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T15:00:00+00:00",
            "waterTemperature": {
                "noaa": 27.04,
                "sg": 27.04
            },
            "windDirection": {
                "ecmwf:aifs": 228.67,
                "noaa": 317.39,
                "sg": 317.39
            },
            "windSpeed": {
                "ecmwf:aifs": 4.14,
                "noaa": 2.24,
                "sg": 2.24
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.94,
                "noaa": 22.66,
                "sg": 22.66
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T16:00:00+00:00",
            "waterTemperature": {
                "noaa": 24.04,
                "sg": 24.04
            },
            "windDirection": {
                "ecmwf:aifs": 226.37,
                "noaa": 321.43,
                "sg": 321.43
            },
            "windSpeed": {
                "ecmwf:aifs": 3.69,
                "noaa": 3.06,
                "sg": 3.06
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.61,
                "noaa": 20.59,
                "sg": 20.59
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T17:00:00+00:00",
            "waterTemperature": {
                "noaa": 21.03,
                "sg": 21.03
            },
            "windDirection": {
                "ecmwf:aifs": 224.08,
                "noaa": 325.47,
                "sg": 325.47
            },
            "windSpeed": {
                "ecmwf:aifs": 3.23,
                "noaa": 3.89,
                "sg": 3.89
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 15.29,
                "noaa": 18.51,
                "sg": 18.51
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T18:00:00+00:00",
            "waterTemperature": {
                "noaa": 18.02,
                "sg": 18.02
            },
            "windDirection": {
                "ecmwf:aifs": 221.78,
                "noaa": 329.51,
                "sg": 329.51
            },
            "windSpeed": {
                "ecmwf:aifs": 2.77,
                "noaa": 4.71,
                "sg": 4.71
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.65,
                "noaa": 18.01,
                "sg": 18.01
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T19:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.66,
                "sg": 17.66
            },
            "windDirection": {
                "ecmwf:aifs": 216.94,
                "noaa": 332.71,
                "sg": 332.71
            },
            "windSpeed": {
                "ecmwf:aifs": 2.77,
                "noaa": 4.31,
                "sg": 4.31
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 14.02,
                "noaa": 17.51,
                "sg": 17.51
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T20:00:00+00:00",
            "waterTemperature": {
                "noaa": 17.31,
                "sg": 17.31
            },
            "windDirection": {
                "ecmwf:aifs": 212.1,
                "noaa": 335.9,
                "sg": 335.9
            },
            "windSpeed": {
                "ecmwf:aifs": 2.77,
                "noaa": 3.91,
                "sg": 3.91
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 13.38,
                "noaa": 17.0,
                "sg": 17.0
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T21:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.95,
                "sg": 16.95
            },
            "windDirection": {
                "ecmwf:aifs": 207.26,
                "noaa": 339.1,
                "sg": 339.1
            },
            "windSpeed": {
                "ecmwf:aifs": 2.77,
                "noaa": 3.51,
                "sg": 3.51
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.75,
                "noaa": 16.91,
                "sg": 16.91
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T22:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.9,
                "sg": 16.9
            },
            "windDirection": {
                "ecmwf:aifs": 202.42,
                "noaa": 348.21,
                "sg": 348.21
            },
            "windSpeed": {
                "ecmwf:aifs": 2.78,
                "noaa": 3.09,
                "sg": 3.09
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 12.12,
                "noaa": 16.82,
                "sg": 16.82
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-12T23:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.84,
                "sg": 16.84
            },
            "windDirection": {
                "ecmwf:aifs": 197.58,
                "noaa": 357.33,
                "sg": 357.33
            },
            "windSpeed": {
                "ecmwf:aifs": 2.78,
                "noaa": 2.66,
                "sg": 2.66
            }
        },
        {
            "airTemperature": {
                "ecmwf:aifs": 11.48,
                "noaa": 16.74,
                "sg": 16.74
            },
            "cloudCover": {
                "noaa": 100.0,
                "sg": 100.0
            },
            "time": "2025-09-13T00:00:00+00:00",
            "waterTemperature": {
                "noaa": 16.79,
                "sg": 16.79
            },
            "windDirection": {
                "ecmwf:aifs": 192.74,
                "noaa": 6.44,
                "sg": 6.44
            },
            "windSpeed": {
                "ecmwf:aifs": 2.78,
                "noaa": 2.24,
                "sg": 2.24
            }
        }
    ],
    "meta": {
        "cost": 1,
        "dailyQuota": 10,
        "end": "2025-09-13 00:00",
        "lat": 50.85045,
        "lng": 4.34878,
        "params": [
            "airTemperature",
            "waterTemperature",
            "cloudCover",
            "rain",
            "swellDirection",
            "windSpeed",
            "windDirection"
        ],
        "requestCount": 4,
        "start": "2025-09-03 00:00"
    }
}
```
