package cn.djzhao.net.error;

import android.net.ParseException;
import android.support.annotation.Nullable;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import javax.net.ssl.SSLException;

import retrofit2.HttpException;

public class ErrorHandler {

    public static class ERROR {

        public static final int HTTP_ERROR = 1;
        public static final int NETWORK_ERROR = 2;
        public static final int SSL_ERROR = 3;
        public static final int TIMEOUT_ERROR = 4;
        public static final int PARSE_ERROR = 5;
        public static final int UNKNOWNS_ERROR = 6;
    }

    /**
     * 服务器异常
     */
    public static class ServerThrowable extends Exception {
        public int code;
        public String message;

        public ServerThrowable(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    public static class ResponseThrowable extends Throwable {
        public int code;
        public String message;

        public ResponseThrowable(int code, @Nullable Throwable cause) {
            super(cause);
            this.code = code;
        }
    }

    public static ResponseThrowable handleException(Throwable e) {
        ResponseThrowable ex;
        if (e instanceof HttpException) {
            ex = new ResponseThrowable(ERROR.HTTP_ERROR, e);
            ex.message = "网络错误";
        } else if (e instanceof ServerThrowable) {
            ServerThrowable serverThrowable = (ServerThrowable) e;
            ex = new ResponseThrowable(serverThrowable.code, e);
            ex.message = serverThrowable.message;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ResponseThrowable(ERROR.PARSE_ERROR, e);
            ex.message = "解析错误";
        } else if (e instanceof ConnectException) {
            ex = new ResponseThrowable(ERROR.NETWORK_ERROR, e);
            ex.message = "解析错误";
        }  else if (e instanceof SSLException) {
            ex = new ResponseThrowable(ERROR.SSL_ERROR, e);
            ex.message = "证书验证失败";
        }  else if (e instanceof ConnectTimeoutException) {
            ex = new ResponseThrowable(ERROR.TIMEOUT_ERROR, e);
            ex.message = "连接超时";
        } else {
            ex = new ResponseThrowable(ERROR.UNKNOWNS_ERROR, e);
            ex.message = "未知错误";
        }
        return ex;
    }
}
