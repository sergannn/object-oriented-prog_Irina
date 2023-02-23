package client.interactive.comands;

import domain.http.request.Method;
import domain.http.request.Request;
import domain.models.Student;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateCommand extends Command{
    public UpdateCommand(String title) {
        super(title);
    }

    @Override
    public Request action(Scanner scanner) {
        Request request;
        while (true) {

            int id;

            HashMap<String, Object> params = new HashMap<>();
            while (true) {
                System.out.print("Введите ID студента: ");
                try {

                    id = scanner.nextInt();
                    params.put("id", id);
                    break;
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("ID студента должно быть числом");
                    scanner.nextLine();
                }
            }


            Student student = new Student();
            student.setId(id);

            while (student.getFirstName().isEmpty() ||
                    student.getFirstName().isBlank()) {
                System.out.print("Введите имя: ");
                student.setFirstName(scanner.next());
            }

            while (student.getLastName().isEmpty() ||
                    student.getLastName().isBlank()) {
                System.out.print("Введите фамилию: ");
                student.setLastName(scanner.next());
            }

            while(student.getAge() <= 0) {
                try {
                    System.out.print("Введите возраст: ");
                    student.setAge(scanner.nextInt());
                }
                catch (InputMismatchException inputMismatchException) {
                    System.out.println("Возраст должен быть числом");
                    scanner.nextLine();
                }
            }


            while(student.getGroup() <= 0) {
                try {
                    System.out.print("Введите номер группы: ");
                    student.setGroup(scanner.nextInt());
                }
                catch (InputMismatchException inputMismatchException) {
                    System.out.println("Номер группы должен быть числом");
                    scanner.nextLine();
                }
            }

            request = new Request(
                    Method.PUT,
                    params,
                    "application/json",
                    0,
                    student
            );
            request.computeContentLength();
            return request;
        }
    }
}
