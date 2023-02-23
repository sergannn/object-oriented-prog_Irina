package domain.http.response;

import com.google.gson.Gson;
import domain.http.HttpConfig;
import domain.http.request.Request;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ResponseHeaders {
    private static final String separator = ":";
    private static String getHeaderWithSeparator(String title, Object value) {
        return title + ResponseHeaders.separator + " " + value;
    }

    private static String getVersionAndStatus(Response response) {
        return response.getVersion() + " " + response.getStatusCode().getStatus();
    }

    private static String getServer(Response response) {
        return getHeaderWithSeparator("Server", response.getServer());
    }

    private static String getContentType(Response Response) {
        return getHeaderWithSeparator("Content-Type", Response.getContentType());
    }

    private static String getConnection(Response Response) {
        return getHeaderWithSeparator("Connection", Response.getConnection());
    }
    private static String getContentLength(Response response) {
        return getHeaderWithSeparator("Content-Length", response.getContentLength());
    }

    private static String getLocalTime(Response response) {
        return getHeaderWithSeparator("Date",
                response.getTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }

    public static String getResponseHeaders(Response response) {

        String responseHeaders = getVersionAndStatus(response) + System.lineSeparator() +
                getServer(response) + System.lineSeparator() +
                getLocalTime(response) + System.lineSeparator() +
                getContentType(response) + System.lineSeparator() +
                getConnection(response) + System.lineSeparator() +
                getContentLength(response) + System.lineSeparator() +
                System.lineSeparator() +
                System.lineSeparator();


        if (response.getObject() != null) {
            Gson gson = new Gson();
            responseHeaders += gson.toJsonTree(response.getObject());
        }

        return responseHeaders;
    }
}
