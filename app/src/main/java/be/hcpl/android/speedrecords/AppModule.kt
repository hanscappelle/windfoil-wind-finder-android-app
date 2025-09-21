package be.hcpl.android.speedrecords

import be.hcpl.android.speedrecords.api.OpenWeatherService
import be.hcpl.android.speedrecords.api.RetrofitInstance
import be.hcpl.android.speedrecords.api.transformer.WeatherTransformer
import be.hcpl.android.speedrecords.api.transformer.WeatherTransformerImpl
import be.hcpl.android.speedrecords.domain.repository.AssetRepository
import be.hcpl.android.speedrecords.domain.repository.AssetRepositoryImpl
import be.hcpl.android.speedrecords.domain.repository.ConfigRepository
import be.hcpl.android.speedrecords.domain.repository.ConfigRepositoryImpl
import be.hcpl.android.speedrecords.domain.repository.LocationRepository
import be.hcpl.android.speedrecords.domain.repository.LocationRepositoryImpl
import be.hcpl.android.speedrecords.domain.repository.WeatherRepository
import be.hcpl.android.speedrecords.domain.repository.WeatherRepositoryImpl
import be.hcpl.android.speedrecords.domain.usecase.CreateLocationUseCase
import be.hcpl.android.speedrecords.domain.usecase.DeleteLocationUseCase
import be.hcpl.android.speedrecords.domain.usecase.RenameLocationUseCase
import be.hcpl.android.speedrecords.domain.usecase.RetrieveForecastUseCase
import be.hcpl.android.speedrecords.domain.usecase.ShowLocationUseCase
import be.hcpl.android.speedrecords.ui.activity.DetailViewModel
import be.hcpl.android.speedrecords.ui.activity.MainViewModel
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

    factoryOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() }
    singleOf(::LocationRepositoryImpl) { bind<LocationRepository>() }
    singleOf(::ConfigRepositoryImpl) { bind<ConfigRepository>() }
    factoryOf(::AssetRepositoryImpl) { bind<AssetRepository>() }

    factoryOf(::RetrieveForecastUseCase)
    factoryOf(::DeleteLocationUseCase)
    factoryOf(::RenameLocationUseCase)
    factoryOf(::CreateLocationUseCase)
    factoryOf(::ShowLocationUseCase)

    singleOf(::Gson)
}