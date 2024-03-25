package lt.viko.eif.pvaiciulis.storedata.model.product;

import lt.viko.eif.pvaiciulis.storedata.model.product.AmountType;
import lt.viko.eif.pvaiciulis.storedata.model.product.EntityProduct;

import javax.persistence.*;

@Entity
@Table(name = "QuantifiableProduct")
public class QuantifiableProduct extends EntityProduct {
    @Enumerated(EnumType.STRING)

    protected AmountType amountType;
    protected double amount;

    public QuantifiableProduct(String name, int barCode, double price, AmountType amountType, double amount) {
        super(name, barCode, price);
        this.id = id;
        this.amountType = amountType;
        this.amount = amount;
    }

    public QuantifiableProduct() {
        super();
    }

    public AmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double calculatePrice() {
        return price * amount;
    }
}
