package lt.viko.eif.pvaiciulis.storedata.model.discount;

import lt.viko.eif.pvaiciulis.storedata.model.product.EntityProduct;
import lt.viko.eif.pvaiciulis.storedata.model.product.QuantifiableProduct;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.function.BiFunction;
import javax.xml.bind.annotation.*;
import javax.persistence.*;

@Entity
@Table(name = "discount")
public class Discount {
   // BiFunction

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected int id;

    protected double discountPrice;
    protected double percentOff;

    // Does discount only apply to discount card users only? pro+? workers only?
    @Enumerated(EnumType.STRING)
    protected DiscountCardCategory category;

    @OneToOne(targetEntity = EntityProduct.class, cascade= CascadeType.ALL)
    protected EntityProduct discountedProduct;

    public Discount(double discountPrice, DiscountCardCategory category, EntityProduct discountedProduct) {
        this.discountPrice = discountPrice;
        this.percentOff = ((discountedProduct.getPrice() - discountPrice) / discountedProduct.getPrice()) * 100;
        this.category = category;
        this.discountedProduct = discountedProduct;
    }
    public Discount(DiscountCardCategory category, double percentOff, EntityProduct discountedProduct) {
        this.discountPrice = discountedProduct.getPrice() - (discountedProduct.getPrice() * percentOff / 100);
        this.percentOff = percentOff;
        this.category = category;
        this.discountedProduct = discountedProduct;
    }

    public Discount() {}

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(double percentOff) {
        this.percentOff = percentOff;
    }

    public DiscountCardCategory getCategory() {
        return category;
    }

    public void setCategory(DiscountCardCategory category) {
        this.category = category;
    }
}
