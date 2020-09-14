package cn.djzhao.net;

import android.util.Log;

import cn.djzhao.net.api.WeatherApiInterface;
import cn.djzhao.net.bean.Weather;
import cn.djzhao.net.interceptor.CommonRequestInterceptor;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkApi {

    private static final String TAG = "NetworkApi";

    public static INetworkRequiredInfo networkRequiredInfo;
    private static OkHttpClient okHttpClient;

    public static void init(INetworkRequiredInfo iNetworkRequiredInfo) {
        networkRequiredInfo = iNetworkRequiredInfo;
    }

    public static void getWeather(String city, String key) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restapi.amap.com")
                .callFactory(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApiInterface weatherApiInterface = retrofit.create(WeatherApiInterface.class);
        Observable<Weather> weather = weatherApiInterface.getWeather(city, key);
        weather
                // 在IO线程订阅
                .subscribeOn(Schedulers.io())
                // 在主线程处理
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weather>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Weather value) {
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    /**
     * 获取OkHttpClient
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient != null) {
            return okHttpClient;
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 添加日志输出拦截器
        if (networkRequiredInfo.isDebug()) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addNetworkInterceptor(httpLoggingInterceptor);
        }
        // 添加通用请求拦截器
        builder.addInterceptor(new CommonRequestInterceptor(networkRequiredInfo));
        okHttpClient = builder.build();
        return okHttpClient;
    }
}
