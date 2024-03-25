package lt.viko.eif.pvaiciulis.storedata.util;

import lt.viko.eif.pvaiciulis.storedata.old.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class JaxbUtilGeneric {
    public static <T> void convertToXML(T obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();
            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            OutputStream os = new FileOutputStream("generated.xml");
            //marshaller.marshal(obj, System.out);
            marshaller.marshal(obj, os);
        } catch(FileNotFoundException | JAXBException e) {
            System.out.println(e.getMessage());
        }
    }
    public static <T> T convertToPOJO(T obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Unmarshaller unmarshaller = null;
            File file = new File("generated.xml");
            T instance = (T) unmarshaller.unmarshal(file);
            System.out.println(instance);
            return instance;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
