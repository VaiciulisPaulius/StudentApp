package lt.viko.eif.pvaiciulis.studentdata.db;

import lt.viko.eif.pvaiciulis.studentdata.model.Account;
import lt.viko.eif.pvaiciulis.studentdata.model.Student;
import lt.viko.eif.pvaiciulis.studentdata.model.Subject;
import lt.viko.eif.pvaiciulis.studentdata.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DBLoader {
    static Server server = null;

    static {
        Student studentA = new Student("Paulius", "Vaiciulis", "s046598");

        Account studentAAccount = new Account("user1", "user321");
        studentA.setAccount(studentAAccount);

        Subject math = new Subject("Math", 6);
        Subject english = new Subject("English", 6);
        Subject psychology = new Subject("Psychology", 3);

        studentA.addSubjects(math, english, psychology);

        System.out.println(studentA);
        System.out.println(studentA);

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            server = Server.createTcpServer("-tcpPort", "9092").start();
            transaction = session.beginTransaction();
            session.save(studentA);
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            server.shutdown();
        }
    }
    public static void loadStudents() {
        List<Student> result = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Student> students = session.createQuery("from Student", Student.class).list();
            students.forEach(stud -> System.out.println(stud));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
