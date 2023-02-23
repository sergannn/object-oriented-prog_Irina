package client.interactive.comands;

import domain.http.request.Method;
import domain.http.request.Request;
import domain.models.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateCommand extends Command{

    public CreateCommand(String title) {
        super(title);
    }

    @Override
    public Request action(Scanner scanner) {
        Student student = new Student();

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

        Request request = new Request(Method.POST, null,
                "application/json", 0, student);
        request.computeContentLength();
        return request;

    }
}
