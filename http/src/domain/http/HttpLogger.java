package domain.http;

import domain.http.request.Request;
import domain.http.response.Response;

public class HttpLogger {
    public static void responseLog(Response response) {
        System.out.println("----------------------RESPONSE START-----------------------------");
        System.out.println();
        System.out.print(response.getResponse());
        System.out.println();
        System.out.println("-------------------------RESPONSE END----------------------------");

    }

    public static void requestLog(Request request) {
        System.out.println("----------------------REQUEST START-----------------------------");
        System.out.println();
        System.out.print(request.getRequest());
        System.out.println();
        System.out.println("----------------------REQUEST END-------------------------------");
    }
}
