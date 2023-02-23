package domain.http.request;

public enum Method {
    GET("GET"),
    PUT("PUT"),
    DELETE("DELETE"),
    POST("POST");

    private final String method;
    Method(String method) {
        this.method = method;
    }
    public String getMethod(){
        return this.method;
    }
}
