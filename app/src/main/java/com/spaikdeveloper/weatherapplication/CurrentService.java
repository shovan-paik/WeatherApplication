package com.spaikdeveloper.weatherapplication;

import com.spaikdeveloper.weatherapplication.currentweather.CurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CurrentService {

//    String cityName = "Dhaka";
//    @GET("weather?q="+cityName+"&appid=e2427aff21dd55c166ce4fdd9c1177df&units=Metric")
//    Call<CurrentWeatherResponse> getResponse();


    @GET()
    Call<CurrentWeatherResponse> getResponseByCity(@Url String lastUrl);
}
