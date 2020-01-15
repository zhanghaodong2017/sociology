package com.zhd.ultimate.sociology.utils;


import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.Map;

public class HttpAndHttpsProxy {

    /**
     * url:外网测试地址 param：请求报文 proxy：代理地址（内网IP地址：10.0.100.00） port ：端口号（22）
     **/
    public static String httpProxy(String url, String ip,
                                   int port, Map<String, String> headers) {
        HttpURLConnection httpConn = null;
        OutputStreamWriter out1 = null;
        InputStream in = null;
        String result = "";
        try {
            URL urlClient = new URL(url);
            // 创建代理
            Proxy proxy1 = new Proxy(Type.HTTP, new InetSocketAddress(ip, port));
            // 设置代理
            httpConn = (HttpURLConnection) urlClient.openConnection(proxy1);
            // 设置通用的请求属性
            if (headers != null) {
                for (String name : headers.keySet()) {
                    httpConn.setRequestProperty(name, headers.get(name));
                }
            }
            // 发送POST请求必须设置如下两行
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            //使请求报文不中文乱码
            in = urlClient.openStream();
            result = IOUtils.toString(in, "utf-8");
            // 断开连接
            httpConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(in);
            close(out1);
        }

        return result;
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String uri, String ip, int port, Map<String, String> headers) {
        System.setProperty("https.proxySet", "true");
        System.getProperties().put("https.proxyHost", ip);
        System.getProperties().put("https.proxyPort", port);

        Document doc = null;

        String agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)  Chrome/56.0.2924.87 Safari/537.36";


        try {
            doc = Jsoup.connect(uri).ignoreContentType(true)
                    .headers(headers)
                    // ignoreHttpErrors
                    //这个很重要 否则会报HTTP error fetching URL. Status=404
                    .ignoreHttpErrors(true)  //这个很重要
                    .timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            return doc.body().text();
        }
        return null;
    }
}