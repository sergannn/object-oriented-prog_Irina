package domain.http.response;

import domain.http.HttpConfig;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Response implements Serializable {
    private final String version = HttpConfig.HTTP_VERSION;
    private final StatusCode statusCode;
    private final String server = HttpConfig.SERVER;
    private final String acceptLanguage = HttpConfig.ACCEPT_LANGUAGE;
    private final String connection = HttpConfig.CONNECTION;
    private final LocalDateTime time = LocalDateTime.now();
    private final String contentType;
    private int contentLength;
    private final Object object;

    public Response(StatusCode statusCode, String contentType, int contentLength, Object object) {
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.object = object;
        this.contentLength = contentLength;
    }

    public String getVersion() {
        return version;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getServer() {
        return server;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public String getConnection() {
        return connection;
    }

    public String getContentType() {
        return contentType;
    }

    public Object getObject() {
        return object;
    }

    public int getContentLength() {
        return contentLength;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void computeContentLength() {
        this.contentLength = getResponse().length();
    }
    public String getResponse() {
        return ResponseHeaders.getResponseHeaders(this);
    }
}
