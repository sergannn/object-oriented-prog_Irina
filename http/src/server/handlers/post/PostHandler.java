package server.handlers.post;

import domain.http.request.Request;
import domain.http.response.Response;
import domain.http.response.StatusCode;
import domain.models.Student;
import server.dto.StudentDTO;
import server.handlers.Handler;

public class PostHandler implements Handler {
    @Override
    public Response action(Request request, StudentDTO studentDTO) {
        Response response;
        try {
            Student student = (Student) request.getObject();
            studentDTO.create(student);
            response = new Response(
                    StatusCode.OK,
                    "text/plain",
                    0,
                    "Студент успешно добавлен"
            );
        } catch (NullPointerException nullPointerException) {
            response = new Response(
                StatusCode.BAD_REQUEST,
                "text/plain",
                0,
                "Не удалось добавить студента"
            );
        }
        return response;
    }
}
