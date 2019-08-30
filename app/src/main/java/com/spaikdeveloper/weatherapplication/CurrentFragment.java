package com.spaikdeveloper.weatherapplication;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spaikdeveloper.weatherapplication.converter.UnixTimeToReadeableTime;
import com.spaikdeveloper.weatherapplication.currentweather.Weather;
import com.spaikdeveloper.weatherapplication.currentweather.CurrentWeatherResponse;
import com.squareup.picasso.Picasso;





import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends Fragment {
    private TextView temTV,weatherMainAndDescriptionTV,dateDayLocationTV,
            minTempTV,maxTempTV,humidityTV,pressureTV,sunriseTV,sunsetTV;
    private ImageView   weatherIconIV;

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private String cityNames = "Dhaka";


    public CurrentFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            String cityNamesc = bundle.getString("params");
            Toast.makeText(getContext(), "City: "+cityNamesc, Toast.LENGTH_SHORT).show();
           // temTV.setText(cityNamesc);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CurrentService service = retrofit.create(CurrentService.class);


        String lu = "weather?q="+cityNames+"&appid=e2427aff21dd55c166ce4fdd9c1177df&units=Metric";
        service.getResponseByCity(lu).enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                if(response.code()==200){
                    CurrentWeatherResponse wr = response.body();

                    Double tempDouble =  wr.getMain().getTemp();
                    int tempInt = (int) Math.round(tempDouble);
                    String temp = String.valueOf(tempInt);


                    Double minTempInt = wr.getMain().getTempMin();
                    String minTemp = String.valueOf(minTempInt);

                    Double maxTempInt = wr.getMain().getTempMax();
                    String maxTemp = String.valueOf(maxTempInt);

                    Double pressureINT = wr.getMain().getPressure();
                    String pressure = String.valueOf(pressureINT);

                    Double humidityInt = wr.getMain().getHumidity();
                    String humidity = String.valueOf(humidityInt);


                    String sunrise =  UnixTimeToReadeableTime.unixTimeConverter(wr.getSys().getSunrise());
                    String sunset = UnixTimeToReadeableTime.unixTimeConverter(wr.getSys().getSunset());


                    String dateAndDayName = UnixTimeToReadeableTime.timezoneToDateConverter(wr.getTimezone());






                    List<Weather> weathers = wr.getWeather();
                    for (Weather w: weathers) {
                        String weatherMain = w.getMain();
                        String weatherDescription = w.getDescription();
                        String weatherIcon = w.getIcon();
                        weatherMainAndDescriptionTV.setText(weatherMain+"\n"+weatherDescription);
                        String iconUrl = "https://openweathermap.org/img/wn/"+weatherIcon+"@2x.png";
                        Picasso.get().load(iconUrl).into(weatherIconIV);


                    }


                    String city = wr.getName();

                    temTV.setText(temp+"°C");
                    dateDayLocationTV.setText(dateAndDayName+"\n"+city);
                    minTempTV.setText("Min\n"+minTemp+"°C");
                    maxTempTV.setText("Max\n"+maxTemp+"°C");
                    humidityTV.setText("Humidity\n"+humidity+"%");
                    pressureTV.setText("Pressure\n"+pressure+"hPa");
                    sunriseTV.setText("Sunrise\n"+sunrise);
                    sunsetTV.setText("Sunset\n"+sunset);




                }else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                Toast.makeText(getContext(), "error failure", Toast.LENGTH_SHORT).show();
            }
        });


        View v = inflater.inflate(R.layout.fragment_current, container, false);
        temTV = v.findViewById(R.id.temTV);
        weatherMainAndDescriptionTV = v.findViewById(R.id.weatherMainAndDescriptionTV);
        weatherIconIV = v.findViewById(R.id.weatherIconIV);
        dateDayLocationTV = v.findViewById(R.id.dateDayLocationTV);
        minTempTV = v.findViewById(R.id.minTemp);
        maxTempTV = v.findViewById(R.id.maxTemp);
        humidityTV = v.findViewById(R.id.humidityTV);
        pressureTV = v.findViewById(R.id.pressureTV);
        sunriseTV = v.findViewById(R.id.sunriseTV);
        sunsetTV = v.findViewById(R.id.sunsetTV);





        return v;
    }





}
