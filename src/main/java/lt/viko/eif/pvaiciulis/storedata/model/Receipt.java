package lt.viko.eif.pvaiciulis.storedata.model;

import lt.viko.eif.pvaiciulis.storedata.db.DBLoader;
import lt.viko.eif.pvaiciulis.storedata.model.discount.Discount;
import lt.viko.eif.pvaiciulis.storedata.model.discount.DiscountCard;
import lt.viko.eif.pvaiciulis.storedata.model.product.EntityProduct;
import lt.viko.eif.pvaiciulis.storedata.model.product.QuantifiableProduct;
import lt.viko.eif.pvaiciulis.storedata.old.Subject;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@Entity
@Table(name = "Receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @XmlElement
    private int id;

    @OneToMany(targetEntity = QuantifiableProduct.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<QuantifiableProduct> products = new ArrayList<>();

    @OneToOne(targetEntity = DiscountCard.class, cascade= CascadeType.ALL)
    @XmlElement(name = "discount_card")
    private DiscountCard discountCard;

    public Receipt() {
    }
    @XmlElement
    private LocalDateTime timestamp;
    @XmlElement
    private double subtotal;
    @XmlElement
    private double total;

    public void scanProduct(int barCode) {
        EntityProduct product = DBLoader.getProduct(barCode);
        if (product instanceof QuantifiableProduct) {
            products.add((QuantifiableProduct) product);
            subtotal += product.getPrice();
            applyDiscount(product);
        }
        else if(product instanceof DiscountCard) {
            discountCard = (DiscountCard) product;

            total = 0;
            for(QuantifiableProduct quantifiableProduct : products) applyDiscount(quantifiableProduct);
        }
    }
    public void applyDiscount(EntityProduct product) {
        Discount discount = DBLoader.checkDiscount(product);
        System.out.println("Total: " + total);
        if(discount == null) {
            total += product.getPrice();
            return;
        }

        if(discount.getCategory() != null) {
            System.out.println("Check 1");
            if(discountCard == null) {
                total += product.getPrice();
                return;
            }
            System.out.println("Check 2: " + discount.getDiscountPrice() + " " + discountCard.getCategory() + " " + discount.getCategory());

            if(discountCard.getCategory() == discount.getCategory()) total += discount.getDiscountPrice();
            System.out.println("Check 3");
        }
        System.out.println(total);
    }

    public List<QuantifiableProduct> getProducts() {
        return products;
    }

    public void setProducts(List<QuantifiableProduct> products) {
        this.products = products;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime  timestamp) {
        this.timestamp = timestamp;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public void finishTransaction() {
        timestamp = LocalDateTime.now();
    }
}
