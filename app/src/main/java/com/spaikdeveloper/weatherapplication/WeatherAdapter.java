package com.spaikdeveloper.weatherapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.StudentViewHolder> {
    private Context context;
    private ArrayList<Double> maxTempArrayList;
    private ArrayList<Double> minTempArrayList;
    private ArrayList<String> iconArraylist;
    private ArrayList<String> dtArraylist;


    public WeatherAdapter(Context context, ArrayList<Double> maxTempArrayList, ArrayList<Double> minTempArrayList,ArrayList<String> iconArraylist,ArrayList<String> dtArraylist) {
        this.context = context;
        this.maxTempArrayList = maxTempArrayList;
        this.minTempArrayList = minTempArrayList;
        this.iconArraylist = iconArraylist;
        this.dtArraylist = dtArraylist;
    }

    public ArrayList<Double> getMaxTempArrayList() {
        return maxTempArrayList;
    }

    public void setMaxTempArrayList(ArrayList<Double> maxTempArrayList) {
        this.maxTempArrayList = maxTempArrayList;
    }

    public ArrayList<Double> getMinTempArrayList() {
        return minTempArrayList;
    }

    public void setMinTempArrayList(ArrayList<Double> minTempArrayList) {
        this.minTempArrayList = minTempArrayList;
    }

    public ArrayList<String> getIconArraylist() {
        return iconArraylist;
    }

    public void setIconArraylist(ArrayList<String> iconArraylist) {
        this.iconArraylist = iconArraylist;
    }

    public ArrayList<String> getDtArraylist() {
        return dtArraylist;
    }

    public void setDtArraylist(ArrayList<String> dtArraylist) {
        this.dtArraylist = dtArraylist;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cardview_forecast_layout,parent,false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, final int position) {
        holder.minTempTV.setText("Min: "+getMinTempArrayList().get(position)+"°C");
        holder.maxTempTV.setText("Max: "+getMaxTempArrayList().get(position)+"°C");

        String iconUrl = "https://openweathermap.org/img/wn/"+getIconArraylist().get(position)+"@2x.png";
        Picasso.get().load(iconUrl).into(holder.iconIV);

        holder.dayNameTV.setText(getDtArraylist().get(position));


    }


    @Override
    public int getItemCount() {
        return maxTempArrayList.size();
    }



    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView minTempTV,maxTempTV,dayNameTV;
        private ImageView iconIV;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            minTempTV = itemView.findViewById(R.id.minTempTV);
            maxTempTV = itemView.findViewById(R.id.maxTempTV);

            iconIV = itemView.findViewById(R.id.iconIV);
            dayNameTV = itemView.findViewById(R.id.dayNameTV);


        }
    }
}
