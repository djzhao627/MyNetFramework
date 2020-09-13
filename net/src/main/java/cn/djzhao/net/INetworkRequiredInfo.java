package cn.djzhao.net;

import android.content.Context;

/**
 * 使用网络模块需要的信息
 *
 * @author djzhao
 * @date 20/09/14 7:11
 * @email djzhao627@gmail.com
 */
public interface INetworkRequiredInfo {
    String getAppVersionName();

    Context getApplicationContext();

    boolean isDebug();
}
