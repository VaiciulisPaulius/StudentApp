package lt.viko.eif.pvaiciulis.storedata.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class model that represents data about a person.
 */
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    //@XmlJavaTypeAdapter(DateAdapter.class)
    private Date birthDate;

    /**
     * Returns a string representation of the object 'Person'.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("\n\t\t\tfirst name: %s\n" + "\t\t\tlast name: %s\n" + "\t\t\tphoneNumber: %s\n" + "\t\t\tbirth date: %s",
                firstName, lastName, phoneNumber, birthDate);
    }

    /**
     *
     * @param firstName     the first name of the person
     * @param lastName      the last name of the person
     * @param phoneNumber   the phone number of the person
     * @param birthDate     the birthdate of the person
     */

    public Person(String firstName, String lastName, String phoneNumber, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    /**
     * Default constructor for Person
     */
    public Person() {}

    public String getFullName() {
        return firstName + " " + lastName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
