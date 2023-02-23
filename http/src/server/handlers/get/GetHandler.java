package server.handlers.get;

import domain.http.request.Request;
import domain.http.response.Response;
import domain.http.response.StatusCode;
import domain.models.Student;
import server.dto.StudentDTO;
import server.handlers.Handler;

public class GetHandler implements Handler {
    @Override
    public Response action(Request request, StudentDTO studentDTO) {
        Response response;

        if (request.getParameters() == null) {
            response = new Response(
                    StatusCode.OK,
                    "application/json",
                    0,
                    studentDTO.getAll()
            );

        } else {
            try {
                int id = Integer.parseInt(request.getParameters().get("id").toString());
                Student student = studentDTO.get(id);
                if (student == null) {
                    throw new IndexOutOfBoundsException();
                }
                response = new Response(
                    StatusCode.OK,
                        "application/json",
                        0,
                        student
                );
            }
            catch (NumberFormatException numberFormatException) {
                response = new Response(
                        StatusCode.BAD_REQUEST,
                        "text/plain",
                        0,
                        "ID должен быть числом"
                );
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                response = new Response(
                    StatusCode.NOT_FOUND,
                        "text/plain",
                        0,
                        "Студент с таким ID не найден"
                );
            }

        }

        response.computeContentLength();

        return response;
    }
}
