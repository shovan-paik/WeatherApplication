package com.spaikdeveloper.weatherapplication.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class UnixTimeToReadeableTime {

    public static String unixTimeConverter(int second){




            int seconds=second;
            Date date = new Date(seconds*1000L); // convert seconds to milliseconds
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // the format of your date
            String formattedDate = dateFormat.format(date);




            String _24HourTime = formattedDate;
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm:ssa");
        Date _24HourDt = null;
        try {
            _24HourDt = _24HourSDF.parse(_24HourTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String time12 = _12HourSDF.format(_24HourDt);

        return time12;

    }



   public static String timezoneToDateConverter(int timezones){
        int inmezone = timezones;

       int hours = timezones / 3600;
       Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
       DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy\nEEEE");
       formatter.setTimeZone(TimeZone.getTimeZone("GMT+"+hours));
       String dateAndTime = formatter.format(calendar.getTime());

       return dateAndTime;
   }


   public static String forecastWeatherTimeDetail(int dt){

       int seconds=dt;
       Date date = new Date(seconds*1000L); // convert seconds to milliseconds
       SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE\nMMM dd, yyyy\nhh:mm:ss a"); // the format of your date
       String formattedDate = dateFormat.format(date);




       String _24HourTime = formattedDate;
       SimpleDateFormat _24HourSDF = new SimpleDateFormat("EEEE\nMMM dd, yyyy\nhh:mm:ss a");
       SimpleDateFormat _12HourSDF = new SimpleDateFormat("EEEE\nMMM dd, yyyy\nhh:mm:ss a");
       Date _24HourDt = null;
       try {
           _24HourDt = _24HourSDF.parse(_24HourTime);
       } catch (ParseException e) {
           e.printStackTrace();
       }
       String time = _12HourSDF.format(_24HourDt);
       return time;

   }



}
