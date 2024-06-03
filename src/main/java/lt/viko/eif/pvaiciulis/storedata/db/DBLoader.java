package lt.viko.eif.pvaiciulis.storedata.db;

import lt.viko.eif.pvaiciulis.storedata.model.Person;
import lt.viko.eif.pvaiciulis.storedata.model.Receipt;
import lt.viko.eif.pvaiciulis.storedata.model.discount.Discount;
import lt.viko.eif.pvaiciulis.storedata.model.discount.DiscountCard;
import lt.viko.eif.pvaiciulis.storedata.model.discount.DiscountCardCategory;
import lt.viko.eif.pvaiciulis.storedata.model.product.EntityProduct;
import lt.viko.eif.pvaiciulis.storedata.model.product.AmountType;
import lt.viko.eif.pvaiciulis.storedata.model.product.QuantifiableProduct;
import lt.viko.eif.pvaiciulis.storedata.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DBLoader {
    static Server server = null;

    static {
        QuantifiableProduct milk = new QuantifiableProduct("Lactose free milk",
                2098765112, 1.99, AmountType.amount, 1);

        QuantifiableProduct steak = new QuantifiableProduct("Meat",
                2098765115, 10.99, AmountType.amount, 1);

        QuantifiableProduct rubiksCube = new QuantifiableProduct("Rubiks cube",
                2098744513, 4.99, AmountType.amount, 1);

        QuantifiableProduct chips = new QuantifiableProduct("Chips",
                2098741285, 4.5, AmountType.amount, 2);

        QuantifiableProduct banana = new QuantifiableProduct("Banana",
                2098744413, 1.2, AmountType.kg, 1.2);

        Person person1 = new Person("Paulius", "Vaiciulis", "+37069744323", Date.valueOf("2002-05-31"));
        DiscountCard discountCard = new DiscountCard(DiscountCardCategory.Member, person1);

        Discount steakDiscount = new Discount(7.99, DiscountCardCategory.Member, steak);
        Discount rubiksCubeDiscount = new Discount(DiscountCardCategory.Member, 50, rubiksCube);

        Receipt receipt = new Receipt();

        //jdbc:h2:tcp://10.0.30.42:9092/mem:test;DB_CLOSE_DELAY=-1;MODE=MySQL

        Transaction transaction = null;
        Transaction transaction2 = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = null;
            server = Server.createTcpServer("-tcpPort", "9093").start();
            transaction = session.beginTransaction();
            session.save(milk);
            session.save(steak);
            session.save(rubiksCube);
            session.save(discountCard);
            session.save(person1);
            session.save(steakDiscount);
            session.save(rubiksCubeDiscount);
            session.save(chips);
            session.save(banana);

            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction2 = null;
            transaction2 = session.beginTransaction();

            receipt.scanProduct(milk.getBarCode());
            receipt.scanProduct(steak.getBarCode());
            receipt.scanProduct(rubiksCube.getBarCode());
            receipt.scanProduct(discountCard.getBarCode());
            receipt.finishTransaction();
            session.save(receipt);

            transaction2.commit();
        } catch (Exception e) {
            if(transaction2 != null) {
                transaction2.rollback();
            }
            e.printStackTrace();
        } finally {
            server.shutdown();
        }
    }
}
