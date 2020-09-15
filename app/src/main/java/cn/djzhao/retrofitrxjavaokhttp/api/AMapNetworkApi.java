package cn.djzhao.retrofitrxjavaokhttp.api;

import cn.djzhao.net.NetworkApi;
import cn.djzhao.net.bean.BaseResponse;
import cn.djzhao.net.error.ErrorHandler;
import io.reactivex.functions.Function;

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
}
