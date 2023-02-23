package client;

import client.interactive.Interactive;
import domain.connection.ConnectionConfig;

import domain.http.HttpLogger;
import domain.http.request.Method;
import domain.http.request.Request;
import domain.http.response.Response;
import domain.http.response.StatusCode;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interactive interactive = new Interactive(scanner);

        try {

            while (true) {

                Socket client = new Socket(ConnectionConfig.SERVER_IP, ConnectionConfig.SERVER_PORT);
                Request request = interactive.chooseCommand(scanner);


                if (request == null) {
                    continue;
                }
                HttpLogger.requestLog(request);
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);
                try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                     ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                    oos.writeObject(request);
                    out.write(bos.toByteArray());
                }

                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                byte[] bytes = new byte[65000];
                in.read(bytes);

                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));

                Response response = (Response) objectInputStream.readObject();
                HttpLogger.responseLog(response);

            }


        } catch (IOException e) {
            System.out.println("Ошибка во время подключения клиента...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
