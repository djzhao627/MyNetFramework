package cn.djzhao.net;

import android.util.Log;

import cn.djzhao.net.api.WeatherApiInterface;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkApi {

    private static final String TAG = "NetworkApi";

    public static void getWeather(String city, String key) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restapi.amap.com")
                .build();

        WeatherApiInterface weatherApiInterface = retrofit.create(WeatherApiInterface.class);
        Call<ResponseBody> call = weatherApiInterface.getWeather(city, key);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor);
    }
}
