package lt.viko.eif.pvaiciulis.storedata.model;

import lt.viko.eif.pvaiciulis.storedata.db.DBLoader;
import lt.viko.eif.pvaiciulis.storedata.db.DBOperations;
import lt.viko.eif.pvaiciulis.storedata.model.discount.Discount;
import lt.viko.eif.pvaiciulis.storedata.model.discount.DiscountCard;
import lt.viko.eif.pvaiciulis.storedata.model.product.EntityProduct;
import lt.viko.eif.pvaiciulis.storedata.model.product.QuantifiableProduct;
import lt.viko.eif.pvaiciulis.storedata.util.LocalDateTimeAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
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
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime timeOfPurchase;
    @XmlElement
    private double subtotal;
    @XmlElement
    private double total;

    /**
     * Returns a string representation of the object 'Receipt'.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("Receipt:\n" + "\tid: %s\n" + "\tProducts: \n%s\n" + "\tDiscount card: \n%s\n" + "\tTime of purchase: %s\n" + "\tsubtotal: %s\n" + "\ttotal: %s\n",
                id, constructProductsList(), discountCard, timeOfPurchase.toString(), subtotal, total);
    }

    private String constructProductsList() {
        String result = "";
        for(QuantifiableProduct product : products)
            result += product;
        return result;
    }

    public void scanProduct(int barCode) {
        EntityProduct product = DBOperations.getProduct(barCode);
        if (product instanceof QuantifiableProduct) {
            products.add((QuantifiableProduct) product);
            QuantifiableProduct quantProduct = (QuantifiableProduct) product;
            subtotal += quantProduct.calculatePrice();
            applyDiscount(quantProduct);
        }
        else if(product instanceof DiscountCard) {
            discountCard = (DiscountCard) product;

            total = 0;
            for(QuantifiableProduct quantifiableProduct : products) applyDiscount(quantifiableProduct);
        }
    }
    public void applyDiscount(EntityProduct product) {
        Discount discount = DBOperations.checkDiscount(product);
        if(discount == null) {
            if(product instanceof QuantifiableProduct) {
                total += ((QuantifiableProduct) product).calculatePrice();
            }
            else total += product.getPrice();
            return;
        }

        if(discount.getCategory() != null) {
            if(discountCard == null) {
                if(product instanceof QuantifiableProduct) {
                    total += ((QuantifiableProduct) product).calculatePrice();
                }
                else total += product.getPrice();
                return;
            };
            if(discountCard.getCategory() == discount.getCategory()) {
                if(product instanceof QuantifiableProduct) {
                    total += discount.getDiscountPrice() * ((QuantifiableProduct) product).calculatePrice();
                }
                total += discount.getDiscountPrice();
            }
        }
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
        return timeOfPurchase;
    }

    public void setTimestamp(LocalDateTime  timestamp) {
        this.timeOfPurchase = timestamp;
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
        timeOfPurchase = LocalDateTime.now();
    }
}
