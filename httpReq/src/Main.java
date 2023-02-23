import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Main {
    public static void main(String[] args) {
        String query = "https://github.com/sgsoul/object-oriented-prog";

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);

            connection.connect();

            StringBuilder stringBuilder = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }

                System.out.println(stringBuilder.toString());
            } else {
                System.out.println("Ошибка: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}