package org.easydarwin.easyplayer;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.apache.http.ContentTooLongException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostUtil {
    public final static String IP = "192.168.0.117";
    public final static String Port = "5555";
    public static final String ApiRoute = "/api/API";

    public static void post(final String postStr, final Handler handler, Context AlertContext) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    String strurl = "http://" + IP + ":" + Port + ApiRoute;
                    HttpPost request = new HttpPost(strurl);
                    request.addHeader("Content-Type", "application/json");
                    StringEntity se = new StringEntity(postStr);
                    request.setEntity(se);
                    HttpResponse httpResponse = httpClient.execute(request);
                    String result = EntityUtils.toString(httpResponse.getEntity());
                    if (result == null || "".equals(result)) {
                        Bundle data = new Bundle();
                        data.putString("result", Boolean.FALSE.toString());
                        data.putString("cause", "远程调用失败:" + httpResponse.toString());
                        Message msg = new Message();
                        msg.setData(data);
                        handler.handleMessage(msg);
                    } else {
                        Bundle data = new Bundle();
                        data.putString("result", Boolean.TRUE.toString());
                        data.putString("value", result);
                        Message msg = new Message();
                        msg.setData(data);
                        handler.handleMessage(msg);
                    }
                } catch (Exception ex) {
                    Bundle data = new Bundle();
                    data.putString("result", Boolean.FALSE.toString());
                    data.putString("cause", ex.getMessage());
                    Message msg = new Message();
                    msg.setData(data);
                    handler.handleMessage(msg);
                }
            }
        });
        t.start();
        try {
            t.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String Alertstr = handler.toString();
        if (Alertstr != null && !Alertstr.equals("")) {
            UiComponents.Alert(Alertstr, AlertContext);
        }


    }


    public static void Login(final Map m, final Handler handler, Context AlertContext) {
        String poststring = "{\t\n" +
                "    \"lid\": \"" + m.get("lid") + "\",\n" +
                "    \"pwd\": \"" + m.get("pwd") + "\",\n" +
                "    \"operation\": \"Login\",\n" +
                "    \"Params\": {\n" +
                "    }\n" +
                "}";
        post(poststring, handler, AlertContext);
    }


    public static void Regist(final Map m, final Handler handler, Context AlertContext)  {
        String tt = m.containsKey("teacher") ? "teacher" : "temp";
        String str = "{\n" +
                "    \"lid\": \"" + m.get("lid") + "\",\n" +
                "    \"pwd\": \"" + m.get("pwd") + "\",\n" +
                "    \"operation\": \"regist\",\n" +
                "    \"Params\": {\n" +
                "        \"" + tt + "\": true,\n" +
                "        \"name\":\"" + m.get("name") + "\"\n" +
                "    }\n" +
                "}";
        post(str,handler, AlertContext);

    }

    public static void InfoModify(final Map m, final Handler handler, Context AlertContext) {
        String poststring = "{\t\n" +
                "    \"lid\": \"fff\",\n" +
                "    \"pwd\": \"fff\",\n" +
                "    \"operation\": \"InfoModify\",\n" +
                "    \"Token\":\""+ m.get("token") + "\",\n" +
                "    \"Params\": {\n" +
                "    \t\"lid\": \""+ m.get("lid") +"\",\n" +
                "    \t\"pwd\": \""+ m.get("pwd") +"\",\n" +
                "        \"name\": \""+ m.get("name") +"\"\n" +
                "    }\n" +
                "}";

        post(poststring, handler, AlertContext);
    }
}
