package cn.djzhao.retrofitrxjavaokhttp.api;


import cn.djzhao.retrofitrxjavaokhttp.bean.Weather;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterface {
        @GET("/v3/weather/weatherInfo")
        Observable<Weather> getWeather(@Query("city") String city, @Query("key") String key);
    }
