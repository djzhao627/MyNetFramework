package cn.djzhao.retrofitrxjavaokhttp;

import android.app.Application;
import android.content.Context;

import cn.djzhao.net.INetworkRequiredInfo;

/**
 * 网络模块需要的信息
 *
 * @author djzhao
 * @date 20/09/14 7:15
 * @email djzhao627@gmail.com
 */
class NetworkRequiredInfo implements INetworkRequiredInfo {

    private final Application application;

    public NetworkRequiredInfo(Application application) {
        this.application = application;
    }

    @Override
    public String getAppVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    public Context getApplicationContext() {
        return application;
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
