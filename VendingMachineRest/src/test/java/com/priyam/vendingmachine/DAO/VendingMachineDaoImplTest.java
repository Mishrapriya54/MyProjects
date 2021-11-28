package com.priyam.vendingmachine.DAO;

import com.priyam.vendingmachine.DTO.Items;
import com.priyam.vendingmachine.ServiceLayer.PersistenceException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendingMachineDaoImplTest {

    VendingMachineDao testDao;

    public VendingMachineDaoImplTest() {
    }

    @BeforeEach
    public void setUp() throws PersistenceException, IOException {
        String testFile = "itemTestFile";
        new FileWriter("itemTestFile");
        testDao = new VendingMachineDaoImpl("itemTestFile");
    }

    @Test
    public void testUpdateQuantity() throws PersistenceException {
        int id1 = 001;
        Items firstItem = new Items(id1);

        firstItem.setItemName("Coke");
        firstItem.setItemCost("2.50");
        firstItem.setItemQuantity("5");
        //adding item to the DAO
        testDao.addItem(id1, firstItem);
        //getting item in a variable
        Items item = testDao.getItem(id1);
        item.updateQuantity();
        //testDao.addItem(id1, firstItem);
        Items item1 = testDao.getItem(id1);
        assertEquals(item.getItemQuantity(), 4, "checking if quantity is updated");
        assertEquals(testDao.addItem(id1, firstItem).getItemQuantity(), item1.getItemQuantity(), "cehck q");
    }

    @Test
    public void testGetAllItems() throws PersistenceException {
        //first item
        int id1 = 001;
        Items firstItem = new Items(id1);
        firstItem.setItemName("Lays Chips");
        firstItem.setItemCost("2.50");
        firstItem.setItemQuantity("5");
        //second item
        int id2 = 002;
        Items secondItem = new Items(id2);
        secondItem.setItemName("Energy Bar");
        secondItem.setItemCost("3.50");
        secondItem.setItemQuantity("10");
        //add both objects on DAO
        testDao.addItem(id1, firstItem);
        testDao.addItem(id2, secondItem);
        //retrieve list of both items using getAll method
        List<Items> allItems = testDao.getListOfItems();
        //assert if list is not empty.
        assertNotNull(allItems, "Checking if empty");
        //check if list size is have same number of data added.
        assertEquals(2, allItems.size(), "Checking if size is equal to the data added.");
        assertFalse((allItems.size() != 2), "Checking if size is equal to the data added.");
        //checking if it has Lays Chips
        assertTrue(testDao.getListOfItems().contains(firstItem), "checking if it contains first Item");
        //checking if it has Energy Bar
        assertTrue(testDao.getListOfItems().contains(secondItem), "checking if it contains second Item");
    }

    @Test
    public void testAddItem() throws PersistenceException {
        int id = 001;
        Items item = new Items(id);
        item.setItemName("Cup Cakes");
        item.setItemCost("1.5");
        item.setItemQuantity("5");
        //adding first item using defined id as a key.
        testDao.addItem(id, item);
        //getting data of the item id 001 and saving in a variable.
        Items retrievedItem = testDao.getItem(id);
        //checking fields of the object same as retrievedmonster object. 
        assertEquals(item.getItemName(), retrievedItem.getItemName(), "Checking if both item's names are same.");
        assertEquals(item.getItemCost(), retrievedItem.getItemCost(), "Checking if both item's cost is same.");
        assertEquals(item.getItemQuantity(), retrievedItem.getItemQuantity(), "Checking if both have same quantity.");
    }
}
