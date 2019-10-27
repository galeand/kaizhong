package com.sse.kaizhong.uitl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @name: HttpUtil
 * @author: xiangyf
 * @create: 2019-10-26 17:28
 * @description:
 */
public class HttpUtil {

    public static class KeyValuePair {
        private String key;
        private Object value;

        public KeyValuePair() {
            super();
        }

        public KeyValuePair(String key, Object value) {
            super();
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    private static final String DEFAULT_CHARSET = "utf-8";

    public static final Map<String, Object> emptyParamMap = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static ThreadLocal<HttpClient> client = new ThreadLocal<HttpClient>() {
        protected synchronized HttpClient initialValue() {
            HttpClient httpClient = HttpClients.createDefault();
            return httpClient;
        }
    };

    public static String get(String url, int timeout) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
        httpGet.setConfig(requestConfig);
        return getResponseStr(httpGet);
    }

    public static String get(String url, Map<String, String> headers, int timeout) throws IOException {
        HttpGet httpGet = new HttpGet(url);

        if (null != headers) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
        httpGet.setConfig(requestConfig);
        return getResponseStr(httpGet);
    }
//
//    public static String get(String baseUrl, List<KeyValuePair> keyValuePairs, Map<String, String> headers, int timeout) throws IOException, URISyntaxException {
//
//        URIBuilder uriBuilder = new URIBuilder(baseUrl);
//        if (null != keyValuePairs) {
//            for (KeyValuePair keyValuePair : keyValuePairs) {
//                uriBuilder.addParameter(keyValuePair.getKey(), keyValuePair.getValue().toString());
//            }
//        }
//
//        HttpGet httpGet = new HttpGet(uriBuilder.build());
//        if (null != headers) {
//            for (Map.Entry<String, String> entry : headers.entrySet()) {
//                httpGet.setHeader(entry.getKey(), entry.getValue());
//            }
//        }
//
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
//        httpGet.setConfig(requestConfig);
//        return getResponseStr(httpGet);
//    }
//
//    public static byte[] getBytes(String url, int timeout) throws IOException {
//        HttpGet httpGet = new HttpGet(url);
//        RequestConfig requestConfig = RequestConfig.custom().
//                setSocketTimeout(timeout).
//                setConnectTimeout(timeout).
//                build();//设置请求和传输超时时间
//        httpGet.setConfig(requestConfig);
//        return getResponseBytes(httpGet);
//    }
//
//    public static byte[] getBytes(String baseUrl, List<KeyValuePair> keyValuePairs, int timeout) throws IOException, URISyntaxException {
//        URIBuilder uriBuilder = new URIBuilder(baseUrl);
//        if (null != keyValuePairs) {
//            for (KeyValuePair keyValuePair : keyValuePairs) {
//                uriBuilder.addParameter(keyValuePair.getKey(), keyValuePair.getValue().toString());
//            }
//        }
//
//        HttpGet httpGet = new HttpGet(uriBuilder.build());
//
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
//        httpGet.setConfig(requestConfig);
//        return getResponseBytes(httpGet);
//    }
//
    private static String post(String url, HttpEntity requestEntity, Map<String, String> headers, int timeout) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(requestEntity);

        if (null != headers) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        return getResponseStr(httpPost);
    }

    public static String post(String url, int timeout) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        return getResponseStr(httpPost);
    }
//
//    public static String post(String url, String requestBody, int timeout) throws IOException {
//        HttpEntity requestEntity = getRequestEntityByRequestString(requestBody, DEFAULT_CHARSET);
//        return post(url, requestEntity, null, timeout);
//    }
//
//    public static String post(String url, String requestBody, Map<String, String> headers, int timeout) throws IOException {
//        HttpEntity requestEntity = getRequestEntityByRequestString(requestBody, DEFAULT_CHARSET);
//        return post(url, requestEntity, headers, timeout);
//    }
//
//    public static String post(String baseUrl, String requestBody, List<KeyValuePair> keyValuePairs, Map<String, String> headers, int timeout) throws IOException, URISyntaxException {
//        URIBuilder uriBuilder = new URIBuilder(baseUrl);
//        if (null != keyValuePairs) {
//            for (KeyValuePair keyValuePair : keyValuePairs) {
//                uriBuilder.addParameter(keyValuePair.getKey(), keyValuePair.getValue().toString());
//            }
//        }
//
//        HttpEntity requestEntity = getRequestEntityByRequestString(requestBody, DEFAULT_CHARSET);
//        return post(uriBuilder.build().toString(), requestEntity, headers, timeout);
//    }
//
//
//    public static String post(String url, String requestBody, String charset, int timeout) throws IOException {
//        HttpEntity requestEntity = new StringEntity(requestBody, charset);
//        return post(url, requestEntity, null, timeout);
//    }
//
//    public static String post(String url, String requestBody, String charset, Map<String, String> headers, int timeout) throws IOException {
//        HttpEntity requestEntity = new StringEntity(requestBody, charset);
//        return post(url, requestEntity, headers, timeout);
//    }
//
//    public static String post(String url, InputStream requestBody, int timeout) throws IOException {
//        HttpEntity requestEntity = new InputStreamEntity(requestBody);
//        return post(url, requestEntity, null, timeout);
//    }
//
//    public static String post(String url, byte[] requestBody, int timeout) throws IOException {
//        HttpEntity requestEntity = new ByteArrayEntity(requestBody);
//        return post(url, requestEntity, null, timeout);
//    }
//
//    public static String post(String url, List<KeyValuePair> keyValuePairs, int timeout) throws IOException {
//        List<NameValuePair> params = new ArrayList<>();
//        if (null != keyValuePairs) {
//            for (KeyValuePair keyValuePair : keyValuePairs) {
//                params.add(new BasicNameValuePair(keyValuePair.getKey(), keyValuePair.getValue().toString()));
//            }
//        }
//        HttpEntity requestParamsEntity = new UrlEncodedFormEntity(params);
//        return post(url, requestParamsEntity, null, timeout);
//    }
//
//    private static String put(String url, HttpEntity requestEntity, Map<String, String> headers, int timeout) throws IOException {
//        HttpPut httpPut = new HttpPut(url);
//        httpPut.setEntity(requestEntity);
//
//        if (null != headers) {
//            for (Map.Entry<String, String> entry : headers.entrySet()) {
//                httpPut.setHeader(entry.getKey(), entry.getValue());
//            }
//        }
//
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
//        httpPut.setConfig(requestConfig);
//        return getResponseStr(httpPut);
//    }
//
//    public static String put(String url, int timeout) throws IOException {
//        HttpPut httpPut = new HttpPut(url);
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
//        httpPut.setConfig(requestConfig);
//        return getResponseStr(httpPut);
//    }
//
//    public static String put(String url, String requestBody, int timeout) throws IOException {
//        HttpEntity requestEntity = getRequestEntityByRequestString(requestBody, DEFAULT_CHARSET);
//        return put(url, requestEntity, null, timeout);
//    }
//
//    public static String put(String url, String requestBody, String charset, int timeout) throws IOException {
//        HttpEntity requestEntity = getRequestEntityByRequestString(requestBody, charset);
//        return put(url, requestEntity, null, timeout);
//    }
//
//    private static HttpEntity getRequestEntityByRequestString(String requestBody, String charset) {
//        return null == requestBody ? null : new StringEntity(requestBody, charset);
//    }
//
//    public static String put(String url, String requestBody, Map<String, String> headers, int timeout) throws IOException {
//        HttpEntity requestEntity = getRequestEntityByRequestString(requestBody, DEFAULT_CHARSET);
//        return put(url, requestEntity, headers, timeout);
//    }
//
//    public static String put(String url, Map<String, Object> paramMap, int timeout) throws IOException {
//        List<NameValuePair> params = new ArrayList<>();
//        if (null != paramMap) {
//            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
//                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
//            }
//        }
//        HttpEntity requestParamsEntity = new UrlEncodedFormEntity(params);
//        return put(url, requestParamsEntity, null, timeout);
//    }
//
//    public static String put(String url, InputStream requestBody, int timeout) throws IOException {
//        HttpEntity requestEntity = new InputStreamEntity(requestBody);
//        return put(url, requestEntity, null, timeout);
//    }
//
//    public static String put(String url, byte[] requestBody, int timeout) throws IOException {
//        HttpEntity requestEntity = new ByteArrayEntity(requestBody);
//        return put(url, requestEntity, null, timeout);
//    }
//
//    public static String delete(String url, int timeout) throws IOException {
//        return delete(url, null, timeout);
//    }
//
//    public static String delete(String url, Map<String, String> headers, int timeout) throws IOException {
//        HttpDelete httpDelete = new HttpDelete(url);
//        if (null != headers) {
//            for (Map.Entry<String, String> entry : headers.entrySet()) {
//                httpDelete.setHeader(entry.getKey(), entry.getValue());
//            }
//        }
//
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();//设置请求和传输超时时间
//        httpDelete.setConfig(requestConfig);
//        return getResponseStr(httpDelete);
//    }
//
    private static String getResponseStr(HttpRequestBase httpRequest) throws IOException {
        try {
            HttpResponse response = client.get().execute(httpRequest);
            System.out.println(response);
            String result = EntityUtils.toString(response.getEntity());

            //判断Response状态码是否为2XX，若为否则抛出异常。
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode / 100 != 2) {
                logger.debug("Get response code {}. payload: {}", statusCode, result);
                throw new IOException("Server response code: " + statusCode);
            }
            return result;
        } catch (IOException e) {
            throw e;
        } finally {
            httpRequest.abort();
        }
    }

    private static byte[] getResponseBytes(HttpRequestBase httpRequest) throws IOException {
        try {
            HttpResponse response = client.get().execute(httpRequest);
            byte[] result = EntityUtils.toByteArray(response.getEntity());

            //判断Response状态码是否为2XX，若为否则抛出异常。
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode / 100 != 2) {
                throw new IOException("Server response code: " + statusCode);
            }
            return result;
        } catch (IOException e) {
            throw e;
        } finally {
            httpRequest.abort();
        }
    }
}
