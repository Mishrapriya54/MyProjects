package com.priyam.vendingmachine.ServiceLayer;

import com.priyam.vendingmachine.DTO.Items;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author priya
 */
public class VendingMachineServiceImplTest {

    private VendingMachineService service;
    VendingMachineServiceImpl serviceImplTest = new VendingMachineServiceImpl();

    public VendingMachineServiceImplTest() {
//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//        VendingMachineAuditDAO auditDao = new VendingMachineAuditDaoStubImpl();
//        service = new VendingMachineServiceImpl(dao, auditDao);

 ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    service = 
        ctx.getBean("service", VendingMachineServiceImpl.class);
}

    @Test
    public void testGetAllItems() throws PersistenceException {
        // ARRANGE
        Items testClone = new Items(1000);
        testClone.setItemName("Sprite");
        testClone.setItemCost("1.5");
        testClone.setItemQuantity("5");
        assertEquals(1, service.getListOfItems().size(),
                "Should only have one item.");
        assertTrue(service.getListOfItems().contains(testClone),
                "The one student should be Sprite");
    }

    @Test
    public void testGetItem() throws PersistenceException {
        // ARRANGE
        Items testClone = new Items(1000);
        testClone.setItemName("Sprite");
        testClone.setItemCost("1.5");
        testClone.setItemQuantity("5");
        Items shouldBeSprite = service.getItem(1000);
        assertNotNull(shouldBeSprite, "Getting 0001 should be not null.");
        assertEquals(testClone, shouldBeSprite,"Item stored under 0001 should be Sprite.");
        Items shouldBeNull = service.getItem(0042);
        assertNull(shouldBeNull, "Getting 0042 should be null.");
    }

    @Test
    public void testRemoveItem() throws Exception {
        
        Items testClone = new Items(1000);
        testClone.setItemName("Sprite");
        testClone.setItemCost("1.5");
        testClone.setItemQuantity("5");
        Items shouldBeSprite = service.removeItems(1000);
        assertNotNull(shouldBeSprite, "Removing 1000 should be not null.");
        assertEquals(testClone, shouldBeSprite, "Item removed from 1000 should be Sprite.");

        Items shouldBeNull = service.removeItems(0042);
        assertNull(shouldBeNull, "Removing 0042 should be null.");

    }

    @Test
    public void testNoItemInventory() throws PersistenceException, InsufficientFundsException, NoItemInventoryException {
        
        Items testClone = new Items(1000);
        testClone.setItemName("Sprite");
        testClone.setItemCost("1.5");
        testClone.setItemQuantity("0");
        BigDecimal balance = new BigDecimal(6);
        service.deposit(balance);
        try {
            service.checkBalance(testClone);
            //fail("Expected Exception was not thrown.");
        } catch (InsufficientFundsException e1) {
            fail("InsufficientException is thrown here");
        } catch (NoItemInventoryException e) {
            assertEquals(e.getMessage(), "Sorry,Item is not available.");

        } catch (PersistenceException e2) {
            fail("persistenceException is thrown here");
        }

    }

    @Test
    public void testInsufficientFunds() throws PersistenceException, InsufficientFundsException, NoItemInventoryException {
       
        Items testClone = new Items(1000);
        testClone.setItemName("Sprite");
        testClone.setItemCost("2");
        testClone.setItemQuantity("5");
        BigDecimal balance = new BigDecimal(1);
        service.deposit(balance);
        try {
            service.checkBalance(testClone);
            //fail("Expected Exception was not thrown.");
        } catch (InsufficientFundsException e1) {
            assertEquals(e1.getMessage(), "Amount is not sufficient to purchase this item,Please add some money");
        } catch (NoItemInventoryException e) {

            fail("InsufficientException is thrown here");
        } catch (PersistenceException e2) {
            fail("persistenceException is thrown here");
        }
    }

    @Test
    public void testDeposit() throws PersistenceException, InsufficientFundsException {

        Items testClone = new Items(1000);
        testClone.setItemName("Sprite");
        testClone.setItemCost("1.5");
        testClone.setItemQuantity("5");
        BigDecimal Balance = new BigDecimal(6);
        service.deposit(Balance);
        assertEquals(new BigDecimal(6).intValue(), serviceImplTest.getBalance().intValue(), "Check if both balances are Equal");
        service.deposit(new BigDecimal(2));
        assertEquals(serviceImplTest.getBalance().intValue(), new BigDecimal(8).intValue(), "Check if both balances are Equal after depositing");
    }

    @Test
    public void testCashChange() throws PersistenceException, InsufficientFundsException, NoItemInventoryException {

        Items testClone = new Items(1000);
        testClone.setItemName("Sprite");
        testClone.setItemCost("1.5");
        testClone.setItemQuantity("5");
        BigDecimal Balance = new BigDecimal(2);
        service.deposit(Balance);
        String change = service.buyItem(testClone);
        assertTrue(change.contains(".5"), "Check change contains 0.5 after a purchase of 1.5 from a given fund 2");
        BigDecimal Balance_2 = new BigDecimal(1.5);
        service.deposit(Balance_2);
        String change_2 = service.buyItem(testClone);

        assertEquals(change_2, "0", "Check change contains 0 after a purchase of 1.5 from a given fund 1.5");
    }

    @Test
    public void testBuyItem() throws PersistenceException, InsufficientFundsException, NoItemInventoryException {

        Items testClone = new Items(1000);
        testClone.setItemName("Sprite");
        testClone.setItemCost("1.5");
        testClone.setItemQuantity("5");
        BigDecimal Balance = new BigDecimal(2);
        service.deposit(Balance);
        String change = service.buyItem(testClone);
        assertEquals(testClone.getItemQuantity(), 4, "Check Item is purchased and quantity got decreased by 1.");
        assertTrue(change.contains(".5"), "Check change contains 0.5 after a purchase of 1.5 from a given fund 2");
        assertTrue(serviceImplTest.getBalance().compareTo(new BigDecimal(0)) == 0, "Check balance is 0 after collecting change");

    }

    @Test
    public void testcheckBalance() throws PersistenceException, InsufficientFundsException, NoItemInventoryException {
      
        Items testClone = new Items(1000);
        testClone.setItemName("Sprite");
        testClone.setItemCost("1.5");
        testClone.setItemQuantity("5");
        BigDecimal Balance = new BigDecimal(2);
        service.deposit(Balance);
        String change = service.buyItem(testClone);
        assertFalse(change.isEmpty(), "Check change variable is not empty as buy method is called successfully as balance is greater than cost of item");

    }
  
}
