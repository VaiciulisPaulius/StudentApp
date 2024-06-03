package lt.viko.eif.pvaiciulis.storedata.model.product;

import javax.persistence.*;

/**
 * Class model that represents products that could be quantified (using the AmountType enum).
 */
@Entity
@Table(name = "QuantifiableProduct")
public class QuantifiableProduct extends EntityProduct {
    @Enumerated(EnumType.STRING)

    protected AmountType amountType;
    protected double amount;

    /**
     *
     * @param name      the name of the entityProduct
     * @param barCode   barcode of the entityProduct, must be unique
     * @param price     the price of the entityProduct
        @param amountType   the amount type the quantifiableproduct is based on
        @param amount      the amount of products quantifiableproduct has.
     */
    public QuantifiableProduct(String name, int barCode, double price, AmountType amountType, double amount) {
        super(name, barCode, price);
        this.amountType = amountType;
        this.amount = amount;
    }

    /**
     * Returns a string representation of the object 'QuantifiableProduct'.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return String.format("\t\tProduct:\n" + "\t\t\tname: %s\n" + "\t\t\tbarCode: %s\n" + "\t\t\tprice: %s\n" + "\t\t\tamountType: %s\n" + "\t\t\tamount: %s\n" + "\t\t\tdiscount: %s\n",
                name, barCode, price, amountType.toString(), amount, discount);
    }
    /**
     * Default constructor for EntityProduct
     */
    public QuantifiableProduct() {
        super();
    }

    /** Gets the amount type of the quantifiableProduct
     *
     * @return the amount type of the quantifiableProduct
     */
    public AmountType getAmountType() {
        return amountType;
    }

    /** Sets the amount type of the quantifiableProduct
     *
     * @param amountType the amountType of the quantifiableProduct
     */
    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }

    /** Gets the number of products in the quantifiableProduct
     *
     * @return the number of products in the quantifiableProduct
     */
    public double getAmount() {
        return amount;
    }

    /** Sets the number of products in the quantifiableProduct
     *
     * @param amount the amount of products in quantifiableProduct
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /** Get the
     *
     * @return
     */
    public double calculatePrice() {
        return price * amount;
    }
}
