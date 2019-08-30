package com.spaikdeveloper.weatherapplication.converter;

public class CityCollect {

    private String cityName;

    public CityCollect() {
    }

    public CityCollect(String cityName) {
        this.cityName = cityName;
    }

    public  String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
