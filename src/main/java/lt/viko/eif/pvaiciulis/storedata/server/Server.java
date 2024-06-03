package lt.viko.eif.pvaiciulis.storedata.server;

import lt.viko.eif.pvaiciulis.storedata.menu.Menu;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final String OUTPUT_ENDPOINT = "@123!^589";
    private static BufferedReader in;
    private static PrintWriter out;
    public static void openServer(int portNumber) {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                } catch (IOException e) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
    private static void handleClient(Socket clientSocket) {
        try (clientSocket;
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("Client connected");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Menu.showMenu(inputLine, out);
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            System.out.println("Client disconnected");
        }
    }
    public static void sendXMLFile(PrintWriter out) {
        try {
            File file = new File("generated.xml");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
