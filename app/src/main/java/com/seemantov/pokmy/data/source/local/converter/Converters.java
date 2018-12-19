package com.seemantov.pokmy.data.source.local.converter;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Converters {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Date stringToDate(String dateStr)
    {
        Date date=null;
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            date = (Date)formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("el date",date+"");
        return date;
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static List<Long> toList(String str) {
        List<Long> list;
        try {
            list = new ObjectMapper().readValue(str, new TypeReference<List<Long>>() {
            });
        } catch (IOException error) {
            list = null;
        }
        return list;
    }

    @TypeConverter
    public static String toString(List<Long> list) {
        String decoded;
        if (list != null) {
            try {
                decoded = new ObjectMapper().writeValueAsString(list);
            } catch (IOException e) {
                decoded = null;
            }
        } else {
            decoded = null;
        }
        return decoded;
    }



   
}
