package domain.http.request;

import com.google.gson.Gson;
import domain.http.HttpConfig;

import java.util.Map;

public class RequestHeaders {

    private static final String separator = ":";
    private static String getHeaderWithSeparator(String title, Object value) {
        return title + RequestHeaders.separator + " " + value;
    }


    private static String getMethodAndVersion(Request request) {
        return request.getMethodRequest().getMethod() + " "
                + HttpConfig.URL + getParameters(request) + " " + request.getVersion();
    }

    private static String getParameters(Request request) {
        StringBuilder parameters = new StringBuilder("/");

        if (request.getParameters() != null) {
            for (Map.Entry<String, Object> entry : request.getParameters().entrySet()) {
                parameters.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        return parameters.toString();
    }

    private static String getHost(Request request) {
        return getHeaderWithSeparator("Host", request.getHost());
    }

    private static String getAcceptLanguage(Request request) {
        return getHeaderWithSeparator("Accept-Language", request.getAcceptLanguage());
    }

    private static String getContentType(Request request) {
        return getHeaderWithSeparator("Content-Type", request.getContentType());
    }

    private static String getContentLength(Request request) {
        return getHeaderWithSeparator("Content-Length", request.getContentLength());
    }

    private static String getConnection(Request request) {
        return getHeaderWithSeparator("Connection", request.getConnection());
    }

    public static String getRequestHeaders(Request request) {

        String requestHeaders = getMethodAndVersion(request) + System.lineSeparator() +
                getHost(request) + System.lineSeparator() +
                getAcceptLanguage(request) + System.lineSeparator() +
                getConnection(request) + System.lineSeparator() +
                getContentType(request) + System.lineSeparator() +
                getContentLength(request) + System.lineSeparator() +
                System.lineSeparator() +
                System.lineSeparator();


        if (request.getObject() != null) {
            Gson gson = new Gson();
            requestHeaders += gson.toJson(request.getObject());
        }

        return requestHeaders;
    }

}
