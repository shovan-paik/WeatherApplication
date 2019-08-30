package com.spaikdeveloper.weatherapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.spaikdeveloper.weatherapplication.converter.UnixTimeToReadeableTime;
import com.spaikdeveloper.weatherapplication.forecastweather.ForecastWeatherResponse;
import com.spaikdeveloper.weatherapplication.forecastweather.Weather;


import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private RecyclerView recycleviewRV;
    private  LinearLayoutManager llm;
    private Double maxTempDouble;
    private Double minTempDouble;
    private ArrayList<Double> maxTempArraylist = new ArrayList<>();
    private ArrayList<Double> minTempArraylist = new ArrayList<>();
    private ArrayList<String> iconArraylist = new ArrayList<>();
    private List<Weather> icomReady;
    private String iconString;
    private String dt;
    private ArrayList<String> dtArrayList = new ArrayList<>();




    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();





        ForecastService service = retrofit.create(ForecastService.class);

        service.getResponse().enqueue(new Callback<ForecastWeatherResponse>() {
            @Override
            public void onResponse(Call<ForecastWeatherResponse> call, Response<ForecastWeatherResponse> response) {
                if (response.code()==200){

                    ForecastWeatherResponse wr = response.body();



                    List<com.spaikdeveloper.weatherapplication.forecastweather.List> lists = wr.getList();
                    for (com.spaikdeveloper.weatherapplication.forecastweather.List list: lists) {

                      maxTempDouble = list.getMain().getTempMin();
                      minTempDouble = list.getMain().getTempMin();

                        minTempArraylist.add(minTempDouble);
                        maxTempArraylist.add(maxTempDouble);

                        dt = UnixTimeToReadeableTime.forecastWeatherTimeDetail(list.getDt());
                        dtArrayList.add(dt);


                      icomReady = list.getWeather();

                        for (Weather icon:icomReady) {
                           iconString = icon.getIcon();
                           iconArraylist.add(iconString);

                        }



                    }


                     if(maxTempArraylist.size()>0 && minTempArraylist.size()>0 && iconArraylist.size()>0 && dtArrayList.size()>0){
                         llm = new LinearLayoutManager(getContext());
                         recycleviewRV.setLayoutManager(llm);
                         WeatherAdapter adapter = new WeatherAdapter(getContext(),maxTempArraylist,minTempArraylist,iconArraylist,dtArrayList);
                         recycleviewRV.setAdapter(adapter);

                     }











                }else{
                    Toast.makeText(getContext(), "forecast error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForecastWeatherResponse> call, Throwable t) {
                Toast.makeText(getContext(), "forecast error failure: "+t, Toast.LENGTH_LONG).show();

            }
        });







       View v = inflater.inflate(R.layout.fragment_forecast, container, false);
       recycleviewRV = v.findViewById(R.id.recycleviewRV);

        return v;
    }

}
