package cn.djzhao.retrofitrxjavaokhttp;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws IOException {
        assertEquals(4, 2 + 2);

        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("city", "南京")
                .add("key", "ae6c53e2186f33bbf240a12d80672d1b")
                .build();
        Request request = new Request.Builder()
                .url("https://restapi.amap.com/v3/weather/weatherInfo")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        Response execute = call.execute();
        System.out.println(new String(execute.body().bytes()));
    }

    public interface WeatherApiInterface {
        @FormUrlEncoded
        @POST("/v3/weather/weatherInfo")
        retrofit2.Call<ResponseBody> getWeather(@Field("city") String city, @Field("key") String key);
    }

    @Test
    public void testRetrofit() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restapi.amap.com")
                // todo 添加 适配器/转换器
                .build();

        WeatherApiInterface weatherApiInterface = retrofit.create(WeatherApiInterface.class);
        retrofit2.Call<ResponseBody> weather = weatherApiInterface.getWeather("南京", "ae6c53e2186f33bbf240a12d80672d1b");
        retrofit2.Response<ResponseBody> response = weather.execute();
        System.out.println(response);
    }
}