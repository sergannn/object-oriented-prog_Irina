package domain.http.response;

public enum StatusCode {
    OK("200 OK"),
    FOUND("302 Found"),
    BAD_REQUEST("400 Bad Request"),
    FORBIDDEN("403 Forbidden"),
    NOT_FOUND("404 Not Found"),
    INTERNAL_ERROR("500 Internal Error");

    private String status;
    StatusCode(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
}
