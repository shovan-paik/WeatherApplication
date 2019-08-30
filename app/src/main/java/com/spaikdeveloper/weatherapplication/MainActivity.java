package com.spaikdeveloper.weatherapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.spaikdeveloper.weatherapplication.converter.CityCollect;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayoutID;
    private ViewPager viewPagerID;
    private AppCompatAutoCompleteTextView autoCompleteTV;
    private String[] cityNames;
    private String city;
    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("");

        autoCompleteTV = findViewById(R.id.autoCompleteTV);
        cityNames = getResources().getStringArray(R.array.city_name);
        ArrayAdapter<String> cityNameAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cityNames);
        autoCompleteTV.setThreshold(1);
        autoCompleteTV.setAdapter(cityNameAdapter);

       autoCompleteTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               city = autoCompleteTV.getText().toString();
               //CityCollect cityCollect = new CityCollect(city);

               //Toast.makeText(MainActivity.this, "City Name: "+city, Toast.LENGTH_SHORT).show();
               fragmentManager = getSupportFragmentManager();
               CurrentFragment myObj = new CurrentFragment();
               Bundle bundle = new Bundle();
               bundle.putString("params", city);
                // set MyFragment Arguments
               myObj.setArguments(bundle);
           }
       });


        tabLayoutID = findViewById(R.id.tabLayoutID);
        viewPagerID = findViewById(R.id.viewPagerID);

        viewPagerID.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayoutID.setupWithViewPager(viewPagerID);

        tabLayoutID.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerID.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });





    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        String[] text = {"CURRENT","7 DAYS FORECAST"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
               return new CurrentFragment();
            }

            if(position==1){
                return new ForecastFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return text.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return text[position];
        }
    }
}
