package lt.viko.eif.pvaiciulis.storedata.menu;

import lt.viko.eif.pvaiciulis.storedata.db.DBLoader;
import lt.viko.eif.pvaiciulis.storedata.util.JaxbUtilGeneric;

import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

    //private StudentData data;
    public static void displayMenu(PrintWriter out) {
        out.println("Make a selection");
        out.println("-------------");
        out.printf("| 1) %10s \n", "Load students");
        out.printf("| 2) %10s \n", "Convert receipt to XML");
        out.printf("| 7) + %7s \n", "Quit");
    }
    public static void showMenu(String input, PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        //do {
            switch(input) {
                case "1":
                    DBLoader.loadStudents();
                    break;
                case "2":
                    JaxbUtilGeneric.convertToXML(DBLoader.getReceipts().get(0));
                    break;
                case "7":
                    out.println("Thank you and goodbye!");
                    break;
            }
        //}
        //while(userChoice != 7);
    }
}
