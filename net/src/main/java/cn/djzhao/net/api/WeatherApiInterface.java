package cn.djzhao.net.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterface {
        @GET("/v3/weather/weatherInfo")
        retrofit2.Call<ResponseBody> getWeather(@Query("city") String city, @Query("key") String key);
    }
