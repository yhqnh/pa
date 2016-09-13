import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/23.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        String url = "http://zxcv.3g.qq.com/sdk/beacon.jsp?type=real&size=976";

        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "c5e5fbfb-09e5-4c60-8d2d-0010239b1e6b");
        cookie.setDomain("192.168.1.235:8090");
        cookieStore.addCookie(cookie);
        HttpHost proxy = new HttpHost("127.0.0.1", 8888);
        //得到HttpClient对象
        CloseableHttpClient client = HttpClients.custom().setProxy(proxy).setDefaultCookieStore(cookieStore).build();
        //得到HttpGet对象
        HttpPost request = new HttpPost(url);
//        request.addHeader(new BasicHeader("Cookie", "JSESSIONID=b67a882d-e144-4580-9f29-6a68d522ee85"));
        request.addHeader(new BasicHeader("Host", "localhost:8090"));
//        request.addHeader(new BasicHeader("Content-Length", "976"));
//        request.addHeader(new BasicHeader("key", "我是key"));
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        //提交两个参数及值
//        nvps.add(new BasicNameValuePair("age", "20"));
        nvps.add(new BasicNameValuePair("k", "张三"));
        //设置表单提交编码为UTF-8

        request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
//        client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
//        client.getParams().setParameter("http.protocol.single-cookie-header", true);
        //获得输入流
        //客户端使用GET方式执行请教，获得服务器端的回应response
        HttpResponse response = client.execute(request);
        Header[] headers = response.getAllHeaders();
        for (int i = 0; i < headers.length; i++) {
            System.out.println(headers[i].getName());
            System.out.println(headers[i].getValue());
        }
        List<Cookie> cookieList = cookieStore.getCookies();
        for (Cookie cookie1 : cookieList) {
            System.out.println(cookie1.getName() + "=" + cookie.getValue());
        }
        InputStream inStrem = response.getEntity().getContent();
        String json = new String(readInputStream(inStrem));
        System.out.println("getCode json2:" + json);
    }


    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();//网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }
}
