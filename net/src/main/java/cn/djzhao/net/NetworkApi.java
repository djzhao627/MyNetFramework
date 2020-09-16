package cn.djzhao.net;

import java.util.HashMap;
import java.util.Map;

import cn.djzhao.net.dns.AliDNS;
import cn.djzhao.net.error.HttpError;
import cn.djzhao.net.interceptor.CommonRequestInterceptor;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class NetworkApi {

    private static final String TAG = "NetworkApi";

    public static INetworkRequiredInfo networkRequiredInfo;
    private static OkHttpClient okHttpClient;

    private static Map<String, Retrofit> retrofitMap = new HashMap<>();

    public static void init(INetworkRequiredInfo iNetworkRequiredInfo) {
        networkRequiredInfo = iNetworkRequiredInfo;
    }

    /**
     * 获取Retrofit服务
     *
     * @return Retrofit
     */
    protected Retrofit getService() {
        Retrofit retrofit = retrofitMap.get(getBaseUrl());
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .callFactory(getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofitMap.put(getBaseUrl(), retrofit);
        }
        return retrofit;
    }

    /**
     * 订阅封装
     *
     * @param observer 订阅者
     * @param <T>      类型
     * @return observable
     */
    public <T> ObservableTransformer<T, T> subscribe(final Observer<T> observer) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream.
                        // io线程订阅
                        subscribeOn(Schedulers.io())
                        // 主线程观察
                        .observeOn(AndroidSchedulers.mainThread());
                // 添加自定义异常处理
                if (getAppErrorHandler() != null) {
                    observable = observable.map(NetworkApi.this.getAppErrorHandler());
                }
                // 统一异常处理
                observable = observable.onErrorResumeNext(new HttpError<T>());
                // 提交订阅
                observable.subscribe(observer);
                return observable;
            }
        };
    }

    /**
     * 获取OkHttpClient
     *
     * @return OkHttpClient
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
        // 添加阿里DNS
        builder.dns(new AliDNS());
        okHttpClient = builder.build();
        return okHttpClient;
    }

    /**
     * 获取BaseUrl
     *
     * @return BaseUrl
     */
    protected abstract String getBaseUrl();

    /**
     * 获取用户的错误处理器
     *
     * @param <T>
     * @return
     */
    protected abstract <T> Function<T, T> getAppErrorHandler();
}
