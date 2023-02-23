package client.interactive;

import client.interactive.comands.*;
import domain.http.request.Request;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Interactive {

    private Scanner scanner;
    private List<Command> commandList;

    public Interactive(Scanner scanner) {
        this.scanner = scanner;
        commandList = new ArrayList<>();

        commandList.add(new GetAllCommand("Посмотреть список всех студентов"));
        commandList.add(new GetCommand("Посмотреть студента по ID"));
        commandList.add(new CreateCommand("Добавить студента"));
        commandList.add(new DeleteCommand("Удалить студента"));
        commandList.add(new UpdateCommand("Обновить студента"));
    }

    public Request chooseCommand(Scanner scanner) {
        while (true) {
            System.out.println("----------------------------------------------------");
            for (int i = 0; i < commandList.size(); i++) {
                System.out.printf("%d. %s", i + 1, commandList.get(i).getTitle());
                System.out.println();
            }
            System.out.println("----------------------------------------------------");
            try {
                System.out.println();
                System.out.print("Введите номер команды: ");
                int commandNumber = scanner.nextInt();
                Command command = commandList.get(commandNumber - 1);
                return command.action(scanner);
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Команда не найдена");
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Для выбора команды введите число");
                scanner.nextLine();

            }
        }

    }
}
