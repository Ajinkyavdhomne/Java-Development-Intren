import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static Set<Socket> clientSockets = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started on port 5000...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientSockets.add(clientSocket);

                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Handle in separate thread
                new Thread(new ClientHandler(clientSocket)).start();
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    // Broadcast message to all connected clients
    public static void broadcast(String message, Socket excludeSocket) {
        for (Socket socket : clientSockets) {
            if (socket != excludeSocket) {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(message);
                } catch (IOException e) {
                    System.out.println("Broadcast error: " + e.getMessage());
                }
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client says: " + message);
                ChatServer.broadcast(message, clientSocket);
            }

        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }
}


// To run the server, compile and execute the ChatServer class.
//javac ChatServer.java
//java ChatServer
