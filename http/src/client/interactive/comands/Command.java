package client.interactive.comands;

import domain.http.request.Request;

import java.util.Scanner;

public abstract class Command {

    private String title;

    public Command(String title) {
        this.title = title;
    }

    public abstract Request action(Scanner scanner);

    public String getTitle() {
        return title;
    }
}
