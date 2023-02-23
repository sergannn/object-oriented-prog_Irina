package client.interactive.comands;

import domain.http.request.Method;
import domain.http.request.Request;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteCommand extends Command{

    public DeleteCommand(String title) {
        super(title);
    }

    @Override
    public Request action(Scanner scanner) {
        Request request;
        while (true) {

            System.out.print("Введите ID студента: ");
            try {
                HashMap<String, Object> params = new HashMap<>();
                int id = scanner.nextInt();
                params.put("id", id);
                request = new Request(
                        Method.DELETE,
                        params,
                        "text/plain",
                        0,
                        null
                );
                request.computeContentLength();
                return request;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("ID студента должно быть числом");
                scanner.nextLine();
            }
        }
    }
}
