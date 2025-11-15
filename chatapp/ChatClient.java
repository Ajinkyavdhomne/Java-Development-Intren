import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to the chat server!");

            // Thread for receiving messages
            new Thread(new MessageReceiver(socket)).start();

            // Send messages to server
            Scanner sc = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String msg = sc.nextLine();
                out.println(msg);
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}

class MessageReceiver implements Runnable {
    private Socket socket;

    public MessageReceiver(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Message: " + msg);
            }
        } catch (IOException e) {
            System.out.println("Disconnected from server.");
        }
    }
}
