package be.hcpl.android.speedrecords

import be.hcpl.android.speedrecords.api.OpenWeatherService
import be.hcpl.android.speedrecords.api.RetrofitInstance
import be.hcpl.android.speedrecords.api.transformer.WeatherTransformer
import be.hcpl.android.speedrecords.api.transformer.WeatherTransformerImpl
import be.hcpl.android.speedrecords.domain.AssetRepository
import be.hcpl.android.speedrecords.domain.AssetRepositoryImpl
import be.hcpl.android.speedrecords.domain.ConfigRepository
import be.hcpl.android.speedrecords.domain.ConfigRepositoryImpl
import be.hcpl.android.speedrecords.domain.LocationRepository
import be.hcpl.android.speedrecords.domain.LocationRepositoryImpl
import be.hcpl.android.speedrecords.domain.WeatherRepository
import be.hcpl.android.speedrecords.domain.WeatherRepositoryImpl
import be.hcpl.android.speedrecords.ui.transformer.WeatherDataUiModelTransformer
import be.hcpl.android.speedrecords.ui.transformer.WeatherDataUiModelTransformerImpl
import com.google.gson.Gson
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    factory<OpenWeatherService> { RetrofitInstance.newInstance().create(OpenWeatherService::class.java) }

    viewModelOf(::MainViewModel)
    viewModelOf(::DetailViewModel)

    factoryOf(::WeatherTransformerImpl) { bind<WeatherTransformer>() }
    factoryOf(::WeatherDataUiModelTransformerImpl) { bind<WeatherDataUiModelTransformer>() }

    singleOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() }
    singleOf(::LocationRepositoryImpl) { bind<LocationRepository>() }
    factoryOf(::ConfigRepositoryImpl) { bind<ConfigRepository>() }
    factoryOf(::AssetRepositoryImpl) { bind<AssetRepository>() }

    singleOf(::Gson)
}