package lt.viko.eif.pvaiciulis.storedata.util;

import lt.viko.eif.pvaiciulis.storedata.old.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class JaxbUtil {
    public static void convertToXML(Student student) {
        try {
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            OutputStream os = new FileOutputStream("generated.xml");
            marshaller.marshal(student, System.out);
            //marshaller.marshal(student, os);
        } catch(FileNotFoundException | JAXBException e) {
            System.out.println(e.getMessage());
        }
    }
}
