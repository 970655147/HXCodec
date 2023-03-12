package com.hx.codec.utils;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * HttpResponse
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/5/5 11:35
 */
public class HttpResponse {
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
