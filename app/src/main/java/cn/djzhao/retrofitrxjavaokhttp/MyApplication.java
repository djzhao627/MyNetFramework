package cn.djzhao.retrofitrxjavaokhttp;

import android.app.Application;

import cn.djzhao.net.NetworkApi;

/**
 * @author djzhao
 * @date 20/09/14 7:25
 * @email djzhao627@gmail.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkApi.init(new NetworkRequiredInfo(this));
    }
}
