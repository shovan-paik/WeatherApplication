package com.spaikdeveloper.weatherapplication;

import com.spaikdeveloper.weatherapplication.forecastweather.ForecastWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ForecastService {

    @GET("forecast?id=1336135&appid=e2427aff21dd55c166ce4fdd9c1177df&units=metric&cnt=40")
    Call<ForecastWeatherResponse> getResponse();
}
