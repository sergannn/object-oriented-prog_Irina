package server.handlers;

import domain.http.request.Method;
import domain.http.request.Request;
import domain.http.response.Response;
import server.dto.StudentDTO;
import server.handlers.delete.DeleteHandler;
import server.handlers.get.GetHandler;
import server.handlers.post.PostHandler;
import server.handlers.put.PutHandler;

import java.util.HashMap;

public class HandlersManager {
    private HashMap<Method, Handler> handlers;

    public HandlersManager() {
        handlers = new HashMap<>();

        registerHandler(Method.GET, new GetHandler());
        registerHandler(Method.POST, new PostHandler());
        registerHandler(Method.DELETE, new DeleteHandler());
        registerHandler(Method.PUT, new PutHandler());
    }

    public void registerHandler(Method method, Handler handler) {
        handlers.put(method, handler);
    }

    public Response activeHandle(Request request, StudentDTO studentDTO) {
        return handlers.get(request.getMethodRequest()).action(request, studentDTO);
    }
}
