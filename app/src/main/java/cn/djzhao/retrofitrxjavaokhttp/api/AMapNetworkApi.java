package cn.djzhao.retrofitrxjavaokhttp.api;

import java.io.IOException;

import cn.djzhao.net.NetworkApi;
import cn.djzhao.net.bean.BaseResponse;
import cn.djzhao.net.error.ErrorHandler;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 高德地图API
 */
public class AMapNetworkApi extends NetworkApi {

    public static volatile AMapNetworkApi sInstance;

    public static AMapNetworkApi getInstance() {
        if (sInstance == null) {
            synchronized (AMapNetworkApi.class) {
                if (sInstance == null) {
                    sInstance = new AMapNetworkApi();
                }
            }
        }
        return sInstance;
    }

    /**
     * 获取retrofit服务
     *
     * @param service 服务实例
     * @param <T>     服务实例类
     * @return retrofit服务
     */
    public static <T> T getService(Class<T> service) {
        return getInstance().getService().create(service);
    }

    @Override
    protected String getBaseUrl() {
        return "https://restapi.amap.com";
    }

    /**
     * 错误处理
     *
     * @param <T>
     * @return
     */
    @Override
    protected <T> Function<T, T> getAppErrorHandler() {
        return t -> {
            if (t instanceof BaseResponse) {
                BaseResponse response = (BaseResponse) t;
                if (response.status != 1) {
                    throw new ErrorHandler.ServerThrowable(response.status, response.info);
                }
            }
            return t;
        };
    }

    @Override
    protected Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();
                builder.addHeader("os", "android");
                builder.addHeader("version", networkRequiredInfo.getAppVersionName());
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        };
    }


}
