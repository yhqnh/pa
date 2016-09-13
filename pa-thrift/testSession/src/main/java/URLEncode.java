import org.apache.commons.codec.Decoder;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
public class URLEncode {

    public static void main(String[] args) throws Exception {
        String s = "张三";
        byte[] temp = s.getBytes("ISO-8859-1");
        String ss = new String(temp, "gbk");
        System.out.println(ss);

        byte[] temp2 = ss.getBytes("ISO-8859-1");
        System.out.println(new String(temp2, "gbk"));

        String hexString = "90154f1b050d4487068564b679add148";
        byte[] bytes = Hex.decodeHex(hexString.toUpperCase().toCharArray());
        System.out.println(new String(bytes, "GB18030"));

        String hexString2 = "1bf704016bb183588f4221cde77a13937dcc9b9aa47f25bd79bd5b2fb830be450467d2a4322fe7c468e82360b0466cb04f9cde24db6fe793f549aa4425bedef809c767891a2e6b9e2b3d8db881a36c4cc474d95b2e7473dc35ebbb7cb7bf8bd67dfced2042b5da8f224094798c7e90bafe01973bf8a4642eeb1d20915e18e1fa0e16a52af608d1cb74eeea9163d44e4d74ee0b841fe08ec238b6c1b9bc27258df02dcf237a02f70d16836be90132df84126078e01eec81f9e0c8181fe39d6b9287a01ffcde04bb7cf4bf095c2dbd08e275d16645ab9f32db680acfc164da83a5858c0df76ed597296a599ba1255dad08d2006f5198e646119c4f5dc71809d1c8206103b8ff5813c9881ca10d23c0eb7294611cb8f64f3b888deeeaf750bd42da3a4fd25cc95f387683743d5af8a497a9ef77be7ccaa9dbd3841d97f1758d26c5f1032a199d09267c8f3f4925b49e5e3844e8507139cb7c9d1a3fbf7a3d57864ea0b532c3d26b48ca2af86c9efbbfe6a5a26e9194b424ac2d68450ef3da9cbc45f274a105fbed5360789e0ddd572f3f70ff89e089b1a0e1ffc11195d8f4b0104a69b8b4868f048ab898d28e7d1618d8b41b6ad67cb2840b74d57e38dec6632eb2f83698bb9978f053d925ce4001be83de";
        byte[] bytes2 = Hex.decodeHex(hexString2.toUpperCase().toCharArray());
        System.out.println(new String(bytes2, "gbk"));

    }
}
