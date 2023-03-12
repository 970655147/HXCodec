package com.hx.codec.utils;

import com.hx.codec.constants.Constants;
import com.hx.codec.utils.IoUtils;

import javax.net.ssl.*;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


/**
 * SockUtils
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/4/16 10:28
 */
public class URLConnectionUtils {

    // disable constructor
    private URLConnectionUtils() {
        throw new RuntimeException(" can't be instantiate ");
    }

    /**
     * get
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/5/5 10:26
     */
    public static HttpResponse get(String urlStr, Map<String, String> headers, Map<String, String> form,
                                   String respCharset) {
        HttpURLConnection conn = null;
        try {
            disableSSLVerification();
            String queryString = serializeQueryString(form);
            String queryStringSeprator = urlStr.contains("?") ? "&" : "?";
            String fullUrl = urlStr;
            if (isNotBlank(queryString)) {
                fullUrl = urlStr + queryStringSeprator + queryString;
            }
            URL url = new URL(fullUrl);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");//POST GET PUT DELETE
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();

            HttpResponse result = new HttpResponse();
            result.setUrl(conn.getURL());
            result.setMethod(conn.getRequestMethod());
            result.setHeaders(conn.getHeaderFields());
            result.setResponseCode(conn.getResponseCode());
            result.setResponseMessage(conn.getResponseMessage());
            // if success, read response from server
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
                if (respCharset == null) {
                    respCharset = getCharsetFromServer(result);
                }
                String respFromServer = IoUtils.readStringFromInputStream(conn.getInputStream(), respCharset);
                result.setRespCharset(respCharset);
                result.setRespFromServer(respFromServer);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static HttpResponse get(String urlStr, Map<String, String> headers, Map<String, String> form) {
        return get(urlStr, headers, form, null);
    }

    public static HttpResponse get(String urlStr, Map<String, String> form) {
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("Content-Type", "application/json;charset=UTF-8");
        return get(urlStr, defaultHeaders, form);
    }

    /**
     * post
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/5/5 10:26
     */
    public static HttpResponse post(String urlStr, Map<String, String> headers, Map<String, String> form,
                                    String respCharset) {
        HttpURLConnection conn = null;
        try {
            disableSSLVerification();
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");//POST GET PUT DELETE
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            OutputStream os = conn.getOutputStream();
            String postBody = serializeQueryString(form);
            os.write(postBody.getBytes());
            os.flush();
            os.close();

            HttpResponse result = new HttpResponse();
            result.setUrl(conn.getURL());
            result.setMethod(conn.getRequestMethod());
            result.setHeaders(conn.getHeaderFields());
            result.setResponseCode(conn.getResponseCode());
            result.setResponseMessage(conn.getResponseMessage());
            // if success, read response from server
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
                if (respCharset == null) {
                    respCharset = getCharsetFromServer(result);
                }
                String respFromServer = IoUtils.readStringFromInputStream(conn.getInputStream(), respCharset);
                result.setRespCharset(respCharset);
                result.setRespFromServer(respFromServer);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static HttpResponse post(String urlStr, Map<String, String> headers, Map<String, String> form) {
        return post(urlStr, headers, form, null);
    }

    public static HttpResponse post(String urlStr, Map<String, String> form) {
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("Content-Type", "application/json;charset=UTF-8");
        return post(urlStr, defaultHeaders, form);
    }

    /**
     * postBody
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/5/5 10:26
     */
    public static HttpResponse postBody(String urlStr, Map<String, String> headers, String postBody,
                                        String respCharset) {
        HttpURLConnection conn = null;
        try {
            disableSSLVerification();
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");//POST GET PUT DELETE
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            OutputStream os = conn.getOutputStream();
            os.write(postBody.getBytes());
            os.flush();
            os.close();

            HttpResponse result = new HttpResponse();
            result.setUrl(conn.getURL());
            result.setMethod(conn.getRequestMethod());
            result.setHeaders(conn.getHeaderFields());
            result.setResponseCode(conn.getResponseCode());
            result.setResponseMessage(conn.getResponseMessage());
            // if success, read response from server
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
                if (respCharset == null) {
                    respCharset = getCharsetFromServer(result);
                }
                String respFromServer = IoUtils.readStringFromInputStream(conn.getInputStream(), respCharset);
                result.setRespCharset(respCharset);
                result.setRespFromServer(respFromServer);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static HttpResponse postBody(String urlStr, Map<String, String> headers, String postBody) {
        return postBody(urlStr, headers, postBody, null);
    }

    public static HttpResponse postBody(String urlStr, String postBody) {
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("Content-Type", "application/json;charset=UTF-8");
        return postBody(urlStr, defaultHeaders, postBody);
    }

    /**
     * 序列化給定的 form 为查询字符串
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/5/5 10:35
     */
    public static String serializeQueryString(Map<String, String> form) {
        StringJoiner joiner = new StringJoiner("&");
        for (Map.Entry<String, String> entry : form.entrySet()) {
            try {
                joiner.add(URLEncoder.encode(entry.getKey(), Constants.STRING_UTF8) + "=" + URLEncoder
                        .encode(entry.getValue(), Constants.STRING_UTF8));
                //                joiner.add(entry.getKey() + "=" + entry.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return joiner.toString();
    }

    /**
     * 序列化給定的字符串为 Map
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/5/5 10:35
     */
    public static Map<String, String> deserializeQueryString(String queryString) {
        //        jobGroup=1&jobDesc=task desc &author=MEIYA&alarmEmail=&scheduleType=CRON&scheduleConf=0 0 * * *
        //        ?&cronGen_display=0 0 * * * ?&schedule_conf_CRON=&schedule_conf_FIX_RATE=&schedule_conf_FIX_DELAY
        //        =&glueType=BEAN&executorHandler=companyEmergencyRescueAndMaterialJobHandler&executorParam
        //        =&executorRouteStrategy=FIRST&childJobId=&misfireStrategy=DO_NOTHING&executorBlockStrategy
        //        =SERIAL_EXECUTION&executorTimeout=0&executorFailRetryCount=3&glueRemark=GLUE代码初始化&glueSource=
        String[] kvList = queryString.split("&");
        Map<String, String> result = new HashMap<>();
        for (String kv : kvList) {
            String[] kvSplits = kv.split("=");
            String value = kvSplits.length > 1 ? kvSplits[1] : "";
            result.put(kvSplits[0], value);
        }
        return result;
    }

    /**
     * 获取给定的请求头, 多个请求头, 只取第一个
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/5/5 15:30
     */
    public static String getFirstHeaderValue(HttpResponse response, String headerKey) {
        if (response == null) {
            return null;
        }

        List<String> headerValues = response.getHeaders().get(headerKey);
        if (headerValues == null || headerValues.isEmpty()) {
            return null;
        }

        return headerValues.get(0);
    }

    /**
     * 获取对方服务器的字符集
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/5/5 15:31
     */
    public static String getCharsetFromServer(HttpResponse response) {
        String contentType = getFirstHeaderValue(response, "Content-Type");
        if (isNotBlank(contentType)) {
            String charsetInContentType = "charset=";
            int idxOfCharset = contentType.toLowerCase().indexOf(charsetInContentType);
            if (idxOfCharset >= 0) {
                int idxOfCharsetEnd = contentType.toLowerCase().indexOf(";", idxOfCharset);
                return contentType.substring(idxOfCharset + charsetInContentType.length(),
                        idxOfCharsetEnd < 0 ? contentType.length() : idxOfCharsetEnd);
            }
        }

        return Constants.STRING_UTF8;
    }

    public static Map<String, String> commonHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "*/*");
        headers.put("connection", "Keep-Alive");
        headers.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        headers.put("Accept-Charset", "utf-8");
        headers.put("contentType", "utf-8");
        return headers;
    }

    // Method used for bypassing SSL verification
    public static void disableSSLVerification() {

        TrustManager[] trustAllCerts = new TrustManager[]{new X509ExtendedTrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) {

            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }

        }};

        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    public static boolean isNotBlank(String str) {
        return (str != null) && (!str.trim().isEmpty());
    }

}

