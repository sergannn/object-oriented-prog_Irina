package server;

import domain.connection.ConnectionConfig;
import domain.http.HttpLogger;
import domain.http.request.Request;
import domain.http.response.Response;
import server.dto.StudentDTO;
import server.handlers.HandlersManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static void serverReady() {
        System.out.println("Сервер готов к работе");
        System.out.println();
        System.out.println();
    }
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(ConnectionConfig.SERVER_PORT)) {


            serverReady();
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.initialize();
            HandlersManager handlersManager = new HandlersManager();

            while (true) {
                Socket socket = serverSocket.accept();

                DataInputStream in = new DataInputStream(socket.getInputStream());
                byte[] bytes = new byte[65000];
                in.read(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));

                Request request = (Request) objectInputStream.readObject();
                HttpLogger.requestLog(request);
                Response response = handlersManager.activeHandle(request, studentDTO);
                HttpLogger.responseLog(response);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                     ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                    oos.writeObject(response);
                    out.write(bos.toByteArray());
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка во время подключения сервера...");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка во время работы сервера...");
        }
    }
}
