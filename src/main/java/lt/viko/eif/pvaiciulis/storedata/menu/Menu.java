package lt.viko.eif.pvaiciulis.storedata.menu;

import lt.viko.eif.pvaiciulis.storedata.db.DBLoader;
import lt.viko.eif.pvaiciulis.storedata.db.DBOperations;
import lt.viko.eif.pvaiciulis.storedata.server.Server;
import lt.viko.eif.pvaiciulis.storedata.util.JaxbUtilGeneric;

import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

    public static void displayMenu(PrintWriter out) {
        out.println("Make a selection from server");
        out.println("-------------");
        out.printf("| 1) %10s \n", "Display receipt");
        out.printf("| 2) %10s \n", "Download receipt and display in XML");
        out.printf("| 7) + %7s \n", "Quit");
        out.println(Server.OUTPUT_ENDPOINT);
    }
    public static void showMenu(String input, PrintWriter out) {
        switch(input) {
            case "1":
                out.println(DBOperations.getReceipts().get(0));
                out.println(Server.OUTPUT_ENDPOINT);
                break;
            case "2":
                JaxbUtilGeneric.convertToXML(DBOperations.getReceipts().get(0));
                Server.sendXMLFile(out);
                out.println(Server.OUTPUT_ENDPOINT);
                break;
        }
    }
}
