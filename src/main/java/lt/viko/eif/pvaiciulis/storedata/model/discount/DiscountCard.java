package lt.viko.eif.pvaiciulis.storedata.model.discount;

import lt.viko.eif.pvaiciulis.storedata.db.DBLoader;
import lt.viko.eif.pvaiciulis.storedata.db.DBOperations;
import lt.viko.eif.pvaiciulis.storedata.model.Person;
import lt.viko.eif.pvaiciulis.storedata.model.product.EntityProduct;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class for representing discount card entities.
 */
@Entity
@Table(name = "DiscountCard")
public class DiscountCard extends EntityProduct {
    private DiscountCardCategory category;

    private static final int DISCOUNT_CARD_MIN_BARCODE = 1000000;
    private static final int DISCOUNT_CARD_MAX_BARCODE = 9999999;

    @OneToOne(targetEntity = Person.class, cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @XmlTransient
    private Person person;

    /**
     *
     * @param category  the discount card category (member, worker, etc)
     * @param person    the person the discount card is associated with
     */
    public DiscountCard(DiscountCardCategory category, Person person) {
        super("Discount card", generateUniqueBarcode(), 2);
        this.category = category;
        this.person = person;
    }

    /** converts discount card object into a string representing all of its states.
     *
     * @return a string representation of discount card object.
     */
    @Override
    public String toString() {
        return String.format("\t\tcategory: %s\n" + "\t\tperson: %s\n",
                category, person);
    }

    /**
     * Default DiscountCard constructor
     */
    public DiscountCard() {
    }

    /** Generates a unique barcode among other products.
     *
     * @return a unique barcode
     */
    private static int generateUniqueBarcode() {
        int barcode;
        boolean evaluate;
        do {
            barcode = (int) (Math.random() * (DISCOUNT_CARD_MAX_BARCODE - DISCOUNT_CARD_MIN_BARCODE + 1)) + DISCOUNT_CARD_MIN_BARCODE;
            evaluate = DBOperations.barcodeExists(barcode);
        } while (evaluate);
        return barcode;
    }

    /** Gets the current discount card category
     *
     * @return DiscountCardCategory enum
     */
    public DiscountCardCategory getCategory() {
        return category;
    }

    /** Sets the discount card's category
     *
     * @param category
     */
    public void setCategory(DiscountCardCategory category) {
        this.category = category;
    }

    /** Gets the person that's associated with this discount card.
     *
     * @return returns the person which this discount card is associated with.
     */
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
