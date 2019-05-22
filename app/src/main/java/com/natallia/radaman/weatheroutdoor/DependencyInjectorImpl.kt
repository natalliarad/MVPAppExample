package com.natallia.radaman.weatheroutdoor

class DependencyInjectorImpl : HandMadeDependencyInjector {
    override fun weatherRepository(): WeatherRepository {

        return WeatherRepositoryImpl()
    }
}
