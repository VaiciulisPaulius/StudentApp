package lt.viko.eif.pvaiciulis.storedata.server;

import lt.viko.eif.pvaiciulis.storedata.menu.Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void openServer(int portNumber) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                Menu.displayMenu(out);

                Menu.showMenu(inputLine, out);
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
