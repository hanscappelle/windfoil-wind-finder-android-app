package be.hcpl.android.speedrecords

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::MainViewModel)

    //factoryOf(::LocalProgramRepository)
}