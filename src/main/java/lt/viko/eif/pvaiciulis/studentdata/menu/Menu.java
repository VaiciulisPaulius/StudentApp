package lt.viko.eif.pvaiciulis.studentdata.menu;

import lt.viko.eif.pvaiciulis.studentdata.db.DBLoader;
import lt.viko.eif.pvaiciulis.studentdata.util.JaxbUtil;

import java.util.Scanner;

public class Menu {

    private StudentData data;
    public static int displayMenu(Scanner input) {
        System.out.println("Make a selection");
        System.out.println("-------------");
        System.out.printf("| 1) %10s \n", "Load students");
        System.out.printf("| 7) + %7s \n", "Quit");
        return input.nextInt();
    }
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            int userChoice = displayMenu(scanner);
            switch(userChoice) {
                case 1:
                    studentData.setStudents(DBLoader.loadStudents());
                    //DBLoader.loadStudents();
                    break;
                case 2:
                    studentData.getStudents().forEach(student -> JaxbUtilGeneric.convertToXML(student));
                    //DBLoader.loadStudents();
                    break;
                case 7:
                    System.out.println("Thank you and goodbye!");
                    break;
            }
        }
        while(userChoice != 7);
    }
}
