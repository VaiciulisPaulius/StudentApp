package lt.viko.eif.pvaiciulis.studentdata;

import lt.viko.eif.pvaiciulis.studentdata.db.DBLoader;
import lt.viko.eif.pvaiciulis.studentdata.menu.Menu;

public class Main {
    public static void main(String[] args) {
        new DBLoader();
        Menu.showMenu();
    }
}