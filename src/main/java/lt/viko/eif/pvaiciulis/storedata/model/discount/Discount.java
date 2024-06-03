package lt.viko.eif.pvaiciulis.storedata.model.discount;

import lt.viko.eif.pvaiciulis.storedata.model.product.EntityProduct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

/**
 * Class model that represents discounts some products might have.
 *
 * @author Paulius Vaičiulis
 *
 * @version 1.0
 */
@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected int id;
    protected double discountPrice;
    protected double percentOff;
    @Enumerated(EnumType.STRING)
    protected DiscountCardCategory category;

    @OneToOne(targetEntity = EntityProduct.class, cascade= CascadeType.ALL)
    @XmlTransient
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    protected EntityProduct discountedProduct;

    /**
     * @param discountPrice     The new price the product is going for.
     * @param category          The type of discount card required for the discount to be eligible.
     * @param discountedProduct The product that is being discounted.
     */
    public Discount(double discountPrice, DiscountCardCategory category, EntityProduct discountedProduct) {
        this.discountPrice = discountPrice;
        this.percentOff = ((discountedProduct.getPrice() - discountPrice) / discountedProduct.getPrice()) * 100;
        this.category = category;
        this.discountedProduct = discountedProduct;
    }

    /**
     *
     * @param category          The type of discount card required for the discount to be eligible.
     * @param percentOff        Discount price that is calculated from the original product price.
     * @param discountedProduct The product that is being discounted.
     */
    public Discount(DiscountCardCategory category, double percentOff, EntityProduct discountedProduct) {
        this.discountPrice = discountedProduct.getPrice() - (discountedProduct.getPrice() * percentOff / 100);
        this.percentOff = percentOff;
        this.category = category;
        this.discountedProduct = discountedProduct;
    }

    /**
     * Returns a string representation of the object 'Discount'.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("\n\t\t\t\tdiscountPrice: %s\n" + "\t\t\t\tPercentOff: %s\n" + "\t\t\t\tcategory: %s",
                discountPrice, percentOff, category.toString());
    }

    /**
     * Default Discount constructor.
     */
    public Discount() {}

    /**
     * Get new discounted price for discounted product.
     * @return a discounted price in double.
     */
    public double getDiscountPrice() {
        return discountPrice;
    }

    /**
     * Set a new discount price
     * @param discountPrice The new discount price to be set.
     */
    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * Get the percentage difference between the original and discounted price.
     * @return a percentage in the range of 0-100
     */
    public double getPercentOff() {
        return percentOff;
    }

    /**
     * Set the percentage difference between the original and discounted price.
     * @param percentOff a percentage in the range of 0-100
     */
    public void setPercentOff(double percentOff) {
        this.percentOff = percentOff;
    }

    /**
     * Get the required category for the discount to be eligible (member, pro, worker, etc).
     * @return an enum that represents the different types of discount cards.
     */
    public DiscountCardCategory getCategory() {
        return category;
    }

    /**
     * Set the required category for the discount to be eligible (member, pro, worker, etc).
     * @param category A discount card category enum
     */
    public void setCategory(DiscountCardCategory category) {
        this.category = category;
    }
}
