package cn.djzhao.net.interceptor;

import java.io.IOException;

import cn.djzhao.net.INetworkRequiredInfo;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 通用请求拦截器
 */
public class CommonRequestInterceptor implements Interceptor {

    private INetworkRequiredInfo networkRequiredInfo;

    public CommonRequestInterceptor(INetworkRequiredInfo networkRequiredInfo) {
        this.networkRequiredInfo = networkRequiredInfo;
    }



    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("os", "android");
        builder.addHeader("version", networkRequiredInfo.getAppVersionName());
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}
