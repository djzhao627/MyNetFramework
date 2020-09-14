package cn.djzhao.net.api;


import cn.djzhao.net.bean.Weather;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterface {
        @GET("/v3/weather/weatherInfo")
        Observable<Weather> getWeather(@Query("city") String city, @Query("key") String key);
    }
