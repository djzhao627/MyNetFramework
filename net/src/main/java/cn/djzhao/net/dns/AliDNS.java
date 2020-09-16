package cn.djzhao.net.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.Dns;

public class AliDNS implements Dns {

    /*private HttpDnsService httpDnsService;

    public AliDNS(Context context) {
        this.httpDnsService = HttpDns.getService(context, "111614");
    }*/

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        /*String ipByHostAsync = httpDnsService.getIpByHostAsync(hostname);
        if (ipByHostAsync != null) {
            List<InetAddress> inetAddresses = Arrays.asList(InetAddress.getAllByName(ipByHostAsync));
            return inetAddresses;
        }*/
        return Dns.SYSTEM.lookup(hostname);
    }
}
