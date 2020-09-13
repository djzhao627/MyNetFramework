package cn.djzhao.net.bean;

import java.util.List;

public class Weather extends BaseResponse {

    public String count;
    public int infocode;
    public List<Lives> lives;

    public class Lives {
        public String province;
        public String city;
        public String adcode;
        public String weather;
        public String temperature;
        public String winddirection;
        public String windpower;
        public String humidity;
        public String reporttime;

        @Override
        public String toString() {
            return "Lives{" +
                    "province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", adcode='" + adcode + '\'' +
                    ", weather='" + weather + '\'' +
                    ", temperature='" + temperature + '\'' +
                    ", winddirection='" + winddirection + '\'' +
                    ", windpower='" + windpower + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", reporttime='" + reporttime + '\'' +
                    '}';
        }
    }



}
