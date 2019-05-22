package com.natallia.radaman.weatheroutdoor

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    private lateinit var mockMainActivity: MainContract.View

    private val dependencyInjector: HandMadeDependencyInjector = StubDependencyInjector()

    private var presenter: MainPresenter? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(mockMainActivity, dependencyInjector)
    }

    @After
    fun tearDown() {
        presenter?.onDestroy()
    }

    @Test
    fun testOnViewCreatedFlow() {
        presenter?.onViewCreated()
        verify(mockMainActivity).displayWeatherState(WeatherState.RAIN)
    }
}

class StubDependencyInjector : HandMadeDependencyInjector {
    override fun weatherRepository(): WeatherRepository {
        return StubWeatherRepository()
    }
}

class StubWeatherRepository : WeatherRepository {
    override fun loadWeather(): Weather {
        val weather = Weather("xxx")
        val rain = Rain()
        rain.amount = 10
        weather.rain = rain
        return weather
    }
}
