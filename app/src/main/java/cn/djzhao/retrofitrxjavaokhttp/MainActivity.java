package cn.djzhao.retrofitrxjavaokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.djzhao.net.NetworkApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.request_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkApi.getWeather("南京", "ae6c53e2186f33bbf240a12d80672d1b");
            }
        });
    }
}