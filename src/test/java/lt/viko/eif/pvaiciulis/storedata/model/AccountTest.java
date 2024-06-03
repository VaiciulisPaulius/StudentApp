package lt.viko.eif.pvaiciulis.storedata.model;

import lt.viko.eif.pvaiciulis.storedata.old.Account;

public class AccountTest {
    public static final String USER_NAME1 = "paulius";
    public static final String PASSWORD1 = "vaiciulis";

    private Account account;

    @Before
    public void init() {
        account = new Account(USER_NAME1, PASSWORD1);
    }

    public void testAccountObjectCreation() {
        Assert.assertEquals("Username is not expected.", "paulius", account.getUsername());
        Assert.assertEquals("Password is not expected.", "vaiciulis", account.getPassword());
    }

//    @Test
//    public void TestAcccountToString() {
//        assertTrue(account.toString()
//                .contains(USER_NAME1));
//        assertTrue(account.toString()
//                .contains(PASSWORD1));
//    }
}
