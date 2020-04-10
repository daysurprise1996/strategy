package com.daysurprise.strategy.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangjie
 * @version V1.0
 * @date 2020/4/10 16:10
 * @desc: 模拟登录
 * @className com.daysurprise.strategy.utils.ImitateLoginUtils
 */
public class ImitateLoginUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static void main(String[] args) {
        loginCSDN();
    }

    public static void loginCSDN() {

    }

    /**
     * 根据给定的url地址访问网络，得到响应内容（这里为GET方式访问）
     * 
     * @param url 指定的url地址
     * @return web服务器响应的内容，为<code>String</code>类型，当访问失败时,返回null
     */
    @SuppressWarnings("deprecation")
    public String getWebContent(String url) {
        // 创建一个Http请求对象
        final HttpGet request = new HttpGet(url);
        HttpParams params = new BasicHttpParams();
        // 设置连接超时或响应超时
        // HttpConnectionParams.setConnectionTimeout(params, 3000);
        // HttpConnectionParams.setSoTimeout(params, 5000);
        // 创建一个网络访问对象
        final HttpClient httpClient = new DefaultHttpClient(params);

        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

            @Override
            public String call() throws Exception {
                try {
                    // 执行请求参数项
                    HttpResponse response = httpClient.execute(request);
                    // 判断是否请求成功
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        // 获取响应信息
                        // noinspection UnnecessaryLocalVariable
                        String content = getResponseString(response.getEntity());
                        return content;
                    } else {
                        // 网连接失败，使用Toast显示提示信息
                        // Toast.makeText(context, "网络访问失败，请检查您机器的联网设备!", Toast.LENGTH_LONG).show();
                        logger.error("网络访问失败，请检查您机器的联网设备!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // 释放网络连接资源
                    httpClient.getConnectionManager().shutdown();
                }
                return null;
            }
        });
        new Thread(task).start();
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析网络响应信息，如果为gzip格式，先解压再转换
     * 
     * @param entity 网络返回的HttpEntity信息
     * @return 返回网络响应信息，数据类型为<code>String</code>
     */
    @SuppressWarnings("deprecation")
    private String getResponseString(HttpEntity entity) {
        try {
            if ((entity.getContentEncoding() != null) && entity.getContentEncoding().getValue().contains("gzip")) {
                GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(EntityUtils.toByteArray(entity)));
                InputStreamReader isr = new InputStreamReader(gzip);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String temp;
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                    sb.append("\r\n");
                }
                isr.close();
                gzip.close();

                return sb.toString();
            } else {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
