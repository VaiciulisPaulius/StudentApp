package lt.viko.eif.pvaiciulis.storedata.util;

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
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

            OutputStream os = new FileOutputStream("generated.xml");

            marshaller.marshal(obj, os);
        } catch(FileNotFoundException | JAXBException e) {
            System.out.println(e.getMessage());
        }
    }
}
