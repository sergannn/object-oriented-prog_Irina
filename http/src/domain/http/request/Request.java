package domain.http.request;

import domain.http.HttpConfig;

import java.io.Serializable;
import java.util.HashMap;

public class Request implements Serializable {

    private final String version = HttpConfig.HTTP_VERSION;
    private final Method methodRequest;
    private final String host = HttpConfig.HOST;
    private final String acceptLanguage = HttpConfig.ACCEPT_LANGUAGE;
    private final HashMap<String, Object> parameters;
    private final String connection = HttpConfig.CONNECTION;
    private final String contentType;
    private int contentLength;
    private final Object object;

    public Method getMethodRequest() {
        return methodRequest;
    }

    public String getVersion() {
        return version;
    }

    public String getHost() {
        return host;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public String getContentType() {
        return contentType;
    }

    public int getContentLength() {
        return contentLength;
    }

    public Object getObject() {
        return object;
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public String getConnection() {
        return connection;
    }

    public Request(Method methodRequest, HashMap<String, Object> parameters,
                   String contentType, int contentLength, Object object) {
        this.methodRequest = methodRequest;
        this.parameters = parameters;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.object = object;
    }

    public void computeContentLength() {
        this.contentLength = getRequest().length();
    }

    public String getRequest() {
        return RequestHeaders.getRequestHeaders(this);
    }
}
