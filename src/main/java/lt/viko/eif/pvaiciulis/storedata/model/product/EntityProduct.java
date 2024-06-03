package lt.viko.eif.pvaiciulis.storedata.model.product;
import lt.viko.eif.pvaiciulis.storedata.model.discount.Discount;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class model that represents the essential properties of entities such as products, discount cards.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "product")
public class EntityProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected int id;
    protected String name;
    @Column(unique = true)
    protected int barCode;
    protected double price;


    @OneToOne(mappedBy = "discountedProduct", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @XmlElement(name = "discount")
    protected Discount discount;

    /**
     * Default constructor for EntityProduct
     */
    public EntityProduct() {
    }

    /**
     *
     * @param name      the name of the entityProduct
     * @param barCode   barcode of the entityProduct, must be unique
     * @param price     the price of the entityProduct
     */
    public EntityProduct(String name, int barCode, double price) {
        this.name = name;
        this.barCode = barCode;
        this.price = price;
    }

    /** Gets the name of the entityProduct
     *
     * @return the name of the entityProduct in string
     */
    public String getName() {
        return name;
    }

    /** Sets the name of the entityProduct
     *
     * @param name  the name of the entityProduct
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Gets the unique barcode of the entityProduct
     *
     * @return the uniquebarcode of the entityProduct in int
     */
    public int getBarCode() {
        return barCode;
    }

    /** Sets the unique barcode of the entityProduct
     *
     * @param barCode the unique barcode of the entityProduct
     */
    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    /** Gets the price of the entityProduct
     *
     * @return the price of the entityProduct
     */
    public double getPrice() {
        return price;
    }

    /** Sets the price of the entityProduct
     *
     * @param price the price of the entity
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Gets the id of the entityProduct
     *
     * @return the id of the entityProduct
     */
    public int getId() { return id; }
}
