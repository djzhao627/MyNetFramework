package cn.djzhao.retrofitrxjavaokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import cn.djzhao.net.error.ErrorHandler;
import cn.djzhao.net.observer.BaseObserver;
import cn.djzhao.retrofitrxjavaokhttp.api.AMapNetworkApi;
import cn.djzhao.retrofitrxjavaokhttp.api.WeatherApiInterface;
import cn.djzhao.retrofitrxjavaokhttp.bean.Weather;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.request_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: YES");
                AMapNetworkApi.getService(WeatherApiInterface.class).getWeather("南京", "1ae6c53e2186f33bbf240a12d80672d1b")
                        .compose(AMapNetworkApi.getInstance().subscribe(new BaseObserver<Weather>() {
                            @Override
                            public void onSuccess(Weather weather) {
                                Log.d(TAG, "onSuccess: " + weather);
                            }

                            @Override
                            public void onFailure(ErrorHandler.ResponseThrowable e) {
                                Log.e(TAG, "onFailure: " + e.getMessage());
                                e.printStackTrace();
                            }
                        }));
            }
        });
    }
}