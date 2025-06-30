package socket.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketApp {
    public static void main(String[] args) {
        String serverHost = "127.0.0.1"; // localhost
        int port = 9000;
        try (Socket socket = new Socket(serverHost, port);) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(outputStream);
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your message, or type exit for quit");
            String message = scanner.nextLine();
            while (!"exit".equalsIgnoreCase(message)){
                socketWriter.println(message);
                socketWriter.flush();
                String response = socketReader.readLine();
                System.out.println(response);
                System.out.println("Enter your message, or type exit for quit");
                message = scanner.nextLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
