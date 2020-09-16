package cn.djzhao.net;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.CertificatePinner;

public class CertificateUnitTest {

    @Test
    public void pinTest() {
        String url = "https://restapi.amap.com";
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.connect();
            for (Certificate serverCertificate : connection.getServerCertificates()) {
                X509Certificate x509Certificate = (X509Certificate) serverCertificate;
                System.out.println(CertificatePinner.pin(x509Certificate));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
