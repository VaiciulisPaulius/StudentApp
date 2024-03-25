package lt.viko.eif.pvaiciulis.storedata.old;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;


    private String firstName;
    private String lastName;
    private String code;

    @OneToMany(targetEntity = Subject.class, cascade=CascadeType.ALL)
    List<Subject> subjects = new ArrayList<>();

    @OneToOne(targetEntity = Account.class, cascade=CascadeType.ALL)
    private Account account;

    public Student() {
    }

    public Student(String firstName, String lastName, String code) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("Student:\n" + "\tFirst name: %s\n" + "\tLast name: %s\n" + "\tCode: %s\n" + account + "\tSubjects: \n%s", firstName, lastName, code, constructSubjectList());
    }
    private String constructSubjectList() {
        String result = "";
        for(Subject subject : subjects)
            result += subject;
        return result;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubjects(Subject... subjects) {
        for(Subject subject : subjects) {
            this.subjects.add(subject);
        }
    }
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
