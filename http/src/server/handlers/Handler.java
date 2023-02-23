package server.handlers;

import domain.http.request.Request;
import domain.http.response.Response;
import server.dto.DTO;
import server.dto.StudentDTO;

public interface Handler {
    Response action(Request request, StudentDTO studentDTO);
}
