package lt.viko.eif.pvaiciulis.studentdata.model;

import javax.persistence.*;

@Entity
@Table(name = "subject")

public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String name;
    private int credits;

    @Override
    public String toString() {
        return String.format("\t\tSubject:\n" + "\t\t\tName: %s\n" + "\t\t\tCredits: %s\n", name, credits);
    }

    public Subject() {
    }

    public Subject(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
