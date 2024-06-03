package lt.viko.eif.pvaiciulis.storedata;

import lt.viko.eif.pvaiciulis.storedata.db.DBLoader;
import lt.viko.eif.pvaiciulis.storedata.menu.Menu;
import lt.viko.eif.pvaiciulis.storedata.server.Server;
import lt.viko.eif.pvaiciulis.storedata.util.JaxbUtilGeneric;

public class Main {
    public static void main(String[] args) {
        new DBLoader();
        Server.openServer(10000);
    }
}