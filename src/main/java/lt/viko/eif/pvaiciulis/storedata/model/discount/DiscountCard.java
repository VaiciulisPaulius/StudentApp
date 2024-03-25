package lt.viko.eif.pvaiciulis.storedata.model.discount;

import lt.viko.eif.pvaiciulis.storedata.db.DBLoader;
import lt.viko.eif.pvaiciulis.storedata.model.Person;
import lt.viko.eif.pvaiciulis.storedata.model.product.EntityProduct;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "DiscountCard")
public class DiscountCard extends EntityProduct {
    private DiscountCardCategory category;

    private static final int DISCOUNT_CARD_MIN_BARCODE = 1000000;
    private static final int DISCOUNT_CARD_MAX_BARCODE = 9999999;

    @OneToOne(targetEntity = Person.class, cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    @XmlTransient
    private Person person;

    public DiscountCard(DiscountCardCategory category, Person person) {
        super("Discount card", generateUniqueBarcode(), 2);
        this.category = category;
        this.person = person;
    }

    public DiscountCard() {
    }

    private static int generateUniqueBarcode() {
        int barcode;
        boolean evaluate;
        do {
            barcode = (int) (Math.random() * (DISCOUNT_CARD_MAX_BARCODE - DISCOUNT_CARD_MIN_BARCODE + 1)) + DISCOUNT_CARD_MIN_BARCODE;
            evaluate = DBLoader.barcodeExists(barcode);
        } while (evaluate);
        return barcode;
    }

    public DiscountCardCategory getCategory() {
        return category;
    }

    public void setCategory(DiscountCardCategory category) {
        this.category = category;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
