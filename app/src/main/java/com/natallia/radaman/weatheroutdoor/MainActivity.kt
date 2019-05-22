package com.natallia.radaman.weatheroutdoor

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity(), MainContract.View {
    internal lateinit var imageView: ImageView
    internal lateinit var button: Button

    internal lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)

        setPresenter(MainPresenter(this, DependencyInjectorImpl()))
        presenter.onViewCreated()

        button.setOnClickListener { presenter.onLoadWeatherTapped() }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun displayWeatherState(weatherState: WeatherState) {
        val drawable = resources.getDrawable(
            weatherDrawableResId(weatherState),
            applicationContext.theme
        )
        this.imageView.setImageDrawable(drawable)
    }

    fun weatherDrawableResId(weatherState: WeatherState): Int {
        return when (weatherState) {
            WeatherState.SUN -> R.drawable.ic_sun
            WeatherState.RAIN -> R.drawable.ic_umbrella
        }
    }
}
