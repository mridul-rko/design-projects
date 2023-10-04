import java.io.*;
import java.net.*;

public class MridulWebServer {
    public static void main(String[] args) {
        int port = 8080; // Port to listen on

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept incoming connection
                handleRequest(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket clientSocket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        String line;
        StringBuilder request = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            request.append(line).append("\r\n");
            if (line.isEmpty()) {
                break; // End of request header
            }
        }

        // Parse the request and route it to the appropriate handler
        String httpRequest = request.toString();
        if (httpRequest.startsWith("GET")) {
            handleGetRequest(writer);
        } else if (httpRequest.startsWith("POST")) {
            handlePostRequest(writer);
        } else {
            // Handle other HTTP methods as needed
            handleUnsupportedMethod(writer);
        }

        // Close the sockets
        writer.close();
        reader.close();
        clientSocket.close();
    }

    private static void handleGetRequest(BufferedWriter writer) throws IOException {
        // Handle GET request here
        String response = "HTTP/1.1 200 OK\r\nContent-Length: 19\r\n\r\nGET Request Handled";
        writer.write(response);
        writer.flush();
    }

    private static void handlePostRequest(BufferedWriter writer) throws IOException {
        // Handle POST request here
        String response = "HTTP/1.1 200 OK\r\nContent-Length: 20\r\n\r\nPOST Request Handled";
        writer.write(response);
        writer.flush();
    }

    private static void handleUnsupportedMethod(BufferedWriter writer) throws IOException {
        // Handle unsupported HTTP methods here
        String response = "HTTP/1.1 405 Method Not Allowed\r\nContent-Length: 27\r\n\r\nUnsupported HTTP Method";
        writer.write(response);
        writer.flush();
    }
}