package client.interactive.comands;

import domain.http.request.Method;
import domain.http.request.Request;

import java.util.Scanner;

public class GetAllCommand extends Command {
    public GetAllCommand(String title) {
        super(title);
    }

    @Override
    public Request action(Scanner scanner) {
        Request request = new Request(Method.GET, null, "text/plan", 0, null);
        request.computeContentLength();
        return request;
    }
}
