package com.hx.codec.utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static com.hx.codec.constants.Constants.STRING_UTF8;


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
            String queryString = serializeQueryString(form);
            String queryStringSeprator = urlStr.contains("?") ? "&" : "?";
            String fullUrl = urlStr;
            if (AssertUtils.isNotBlank(queryString)) {
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
                joiner.add(URLEncoder.encode(entry.getKey(), STRING_UTF8) + "=" + URLEncoder.encode(entry.getValue(), STRING_UTF8));
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
//        jobGroup=1&jobDesc=task desc &author=MEIYA&alarmEmail=&scheduleType=CRON&scheduleConf=0 0 * * * ?&cronGen_display=0 0 * * * ?&schedule_conf_CRON=&schedule_conf_FIX_RATE=&schedule_conf_FIX_DELAY=&glueType=BEAN&executorHandler=companyEmergencyRescueAndMaterialJobHandler&executorParam=&executorRouteStrategy=FIRST&childJobId=&misfireStrategy=DO_NOTHING&executorBlockStrategy=SERIAL_EXECUTION&executorTimeout=0&executorFailRetryCount=3&glueRemark=GLUE代码初始化&glueSource=
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
        if (AssertUtils.isCollEmpty(headerValues)) {
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
        if (AssertUtils.isNotBlank(contentType)) {
            String charsetInContentType = "charset=";
            int idxOfCharset = contentType.toLowerCase().indexOf(charsetInContentType);
            if (idxOfCharset >= 0) {
                int idxOfCharsetEnd = contentType.toLowerCase().indexOf(";", idxOfCharset);
                return contentType.substring(idxOfCharset + charsetInContentType.length(), idxOfCharsetEnd < 0 ? contentType.length() : idxOfCharsetEnd);
            }
        }

        return STRING_UTF8;
    }

    /**
     * HttpResponse
     *
     * @author Jerry.X.He
     * @version 1.0
     * @date 2021-10-02 19:43
     */
    public static class HttpResponse {
        private URL url;
        private String method;
        private int responseCode;
        private String responseMessage;
        private Map<String, List<String>> headers;
        private String respCharset;
        private String respFromServer;

        public HttpResponse() {
        }

        public URL getUrl() {
            return url;
        }

        public void setUrl(URL url) {
            this.url = url;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public int getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(int responseCode) {
            this.responseCode = responseCode;
        }

        public String getResponseMessage() {
            return responseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        public Map<String, List<String>> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, List<String>> headers) {
            this.headers = headers;
        }

        public String getRespCharset() {
            return respCharset;
        }

        public void setRespCharset(String respCharset) {
            this.respCharset = respCharset;
        }

        public String getRespFromServer() {
            return respFromServer;
        }

        public void setRespFromServer(String respFromServer) {
            this.respFromServer = respFromServer;
        }
    }


}

