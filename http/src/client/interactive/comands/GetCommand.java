package client.interactive.comands;

import domain.http.request.Method;
import domain.http.request.Request;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GetCommand extends Command{

    public GetCommand(String title) {
        super(title);
    }

    @Override
    public Request action(Scanner scanner) {

        while (true) {
            System.out.print("Введите ID студента: ");

            try{
                int id = scanner.nextInt();
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("id", id);
                Request request = new Request(Method.GET, parameters, "text/plan", 0, null);
                request.computeContentLength();
                return request;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Для выбора студента по ID введите число");
                scanner.nextLine();
            }
        }

    }
}
