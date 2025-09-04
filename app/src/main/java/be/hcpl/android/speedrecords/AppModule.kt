package be.hcpl.android.speedrecords

import be.hcpl.android.speedrecords.api.OpenWeatherService
import be.hcpl.android.speedrecords.api.RetrofitInstance
import be.hcpl.android.speedrecords.domain.WeatherRepository
import be.hcpl.android.speedrecords.domain.WeatherRepositoryImpl
import be.hcpl.android.speedrecords.api.WeatherTransformer
import be.hcpl.android.speedrecords.api.WeatherTransformerImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    factory<OpenWeatherService> { RetrofitInstance.newInstance().create(OpenWeatherService::class.java) }

    viewModelOf(::MainViewModel)

    factoryOf(::WeatherTransformerImpl) { bind<WeatherTransformer>() }
    factoryOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() }
}