package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Orders;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FlooringMasteryOrderDaoImplTest {

    private FlooringMasteryOrderDao testOrderDao;

    public FlooringMasteryOrderDaoImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\TestOrdersFiles\\testFile.txt";
        new FileWriter(testFile);
        testOrderDao = new FlooringMasteryOrderDaoImpl(testFile);
    }

    @AfterEach
    public void tearDown() throws IOException {
        File testFile = new File("C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\TestOrdersFiles");
        //PrintWriter writer = new PrintWriter(testFile);
        File[] entries = testFile.listFiles();
        if (entries != null) {
            for (File entry : entries) {
                entry.deleteOnExit();
            }
        }
    }

    @Test
    public void testAddOrder() throws PersistenceException, IOException, InvalidInputException, ParseException {
        int orderNumOne = 001;
        Orders firstOrder = new Orders(orderNumOne);
        // Date orderDate= new Date(12-3-2022);

        String dateString = "11-02-2022";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date dateObject = sdf.parse(dateString);

        firstOrder.setOrderDate(dateObject);

        firstOrder.setCustomerName("Alia");
        firstOrder.setState("mn");
        firstOrder.setTaxRate(new BigDecimal(3.0));
        firstOrder.setProductType("Vinayl");
        firstOrder.setArea(new BigDecimal(2000));
        firstOrder.setCostPerSquareFoot(new BigDecimal(2.10));
        firstOrder.setLaborCostPerSquareFoot(new BigDecimal(3.0));
        firstOrder.setMaterialCost(new BigDecimal(6.2));
        firstOrder.setLaborCost(new BigDecimal(3.0));
        firstOrder.setTax(new BigDecimal(2.25));
        firstOrder.setTotal(new BigDecimal(3000));
        testOrderDao.addOrder(orderNumOne, firstOrder);

        // List<Orders> retrievedOrderOne=testDao.getOrders(dateString);
        //  assertEquals(retrievedOrderOne.size(),1,"Checking size of list after first order");
        int orderNumTwo = 002;
        Orders secondOrder = new Orders(orderNumTwo);
        // Date orderDate= new Date(12-3-2022);
        secondOrder.setOrderDate(dateObject);
        secondOrder.setCustomerName("Sam");
        secondOrder.setState("Ca");
        secondOrder.setTaxRate(new BigDecimal(8.0));
        secondOrder.setProductType("Wood");
        secondOrder.setArea(new BigDecimal(5000));
        secondOrder.setCostPerSquareFoot(new BigDecimal(3.35));
        secondOrder.setLaborCostPerSquareFoot(new BigDecimal(4.0));
        secondOrder.setMaterialCost(new BigDecimal(7.5));
        secondOrder.setLaborCost(new BigDecimal(3.35));
        secondOrder.setTax(new BigDecimal(6.25));
        secondOrder.setTotal(new BigDecimal(10000));
        testOrderDao.addOrder(orderNumTwo, secondOrder);
        List<Orders> retrievedOrderSecond = testOrderDao.getOrders(dateString);

        assertEquals(retrievedOrderSecond.size(), 2, "Checking size of list");
        assertFalse(retrievedOrderSecond.isEmpty());

//     retrievedOrderSecond.forEach(or -> {
//         assertEquals(or.getCustomerName(),firstOrder.getCustomerName());
//        });
    }

    @Test
    public void testRemoveOrder() throws PersistenceException, IOException, InvalidInputException, ParseException {

        int orderNumOne = 001;
        Orders firstOrder = new Orders(orderNumOne);
        // Date orderDate= new Date(12-3-2022);
        String dateString = "12-06-2029";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date dateObject = sdf.parse(dateString);
        firstOrder.setOrderDate(dateObject);
        firstOrder.setCustomerName("Wells Fargo");
        firstOrder.setState("mn");
        firstOrder.setTaxRate(new BigDecimal(3.0));
        firstOrder.setProductType("Vinayl");
        firstOrder.setArea(new BigDecimal(2000));
        firstOrder.setCostPerSquareFoot(new BigDecimal(2.10));
        firstOrder.setLaborCostPerSquareFoot(new BigDecimal(3.0));
        firstOrder.setMaterialCost(new BigDecimal(6.2));
        firstOrder.setLaborCost(new BigDecimal(3.0));
        firstOrder.setTax(new BigDecimal(2.25));
        firstOrder.setTotal(new BigDecimal(3000));
        //Add one order in Dao.
        testOrderDao.addOrder(orderNumOne, firstOrder);
        //Retrieve added order in the list variable of Type Orders.
        List<Orders> retrievedOrderToDelete = testOrderDao.getOrders(dateString);
        //check if list size is same as added number of orders.
        assertEquals(retrievedOrderToDelete.size(), 1, "Checking size of list after adding the order");
        //Check if list is not empty.
        assertFalse(retrievedOrderToDelete.isEmpty());

        for (Orders onlyOneOrder : retrievedOrderToDelete) {
            //check if retrieved order have same customer name as set in the first order.    
            assertTrue(onlyOneOrder.getCustomerName().equalsIgnoreCase("Wells fargo"));
            assertTrue(onlyOneOrder.getOrderNumber().equals(1));

        }
        //Remove order by calling removeOrder method in orderDao.
        testOrderDao.RemoveOrder("12-06-2029", firstOrder);
        ////Retrieve order in the list variable of Type Orders after removal.
        List<Orders> retrievedOrderAfterDelete = testOrderDao.getOrders(dateString);
        //check if list size is 0 after remving only order.
        assertEquals(retrievedOrderAfterDelete.size(), 0, "Checking size of list after deleting the order");
        //check if list is empty after removing single order 
        assertTrue(retrievedOrderAfterDelete.isEmpty());
    }

    @Test
    public void testEditOrder() throws PersistenceException, IOException, InvalidInputException, ParseException {

        int orderNumOne = 001;
        Orders firstOrder = new Orders(orderNumOne);
        // Date orderDate= new Date(12-3-2022);
        String dateString = "10-31-2023";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date dateObject = sdf.parse(dateString);
        firstOrder.setOrderDate(dateObject);
        firstOrder.setCustomerName("Fragoman Inc.");
        firstOrder.setState("ca");
        firstOrder.setTaxRate(new BigDecimal(3.0));
        firstOrder.setProductType("Wood");
        firstOrder.setArea(new BigDecimal(2000));
        firstOrder.setCostPerSquareFoot(new BigDecimal(2.10));
        firstOrder.setLaborCostPerSquareFoot(new BigDecimal(3.0));
        firstOrder.setMaterialCost(new BigDecimal(6.2));
        firstOrder.setLaborCost(new BigDecimal(3.0));
        firstOrder.setTax(new BigDecimal(2.25));
        firstOrder.setTotal(new BigDecimal(3000));
        //Add one order in Dao.
        testOrderDao.addOrder(orderNumOne, firstOrder);
        //Retrieve added order in the list variable of Type Orders.

        List<Orders> retrievedOrderAfterAdd = testOrderDao.getOrders(dateString);

        //check if list size is same as added number of orders.
        assertEquals(retrievedOrderAfterAdd.size(), 1, "Checking size of list after adding the order");
        //Check if list is not empty.
        assertFalse(retrievedOrderAfterAdd.isEmpty());

        assertFalse(retrievedOrderAfterAdd.isEmpty());

        for (Orders onlyOneOrder : retrievedOrderAfterAdd) {
            //check if retrieved order have same customer name as set in the first order.    
            assertTrue(onlyOneOrder.getCustomerName().equalsIgnoreCase("Fragoman Inc."));
            assertTrue(onlyOneOrder.getOrderNumber().equals(1));
        }
        firstOrder.setCustomerName("Fragoman Inc. Ltd.");
        firstOrder.setArea(new BigDecimal(5000));

        testOrderDao.editOrder(orderNumOne, firstOrder, dateString);
        List<Orders> retrievedOrderAfterEdit = testOrderDao.getOrders(dateString);

        //check if list size is same as added number of orders.
        assertEquals(retrievedOrderAfterEdit.size(), 1, "Checking size of list after adding the order");
        //Check if list is not empty.
        assertFalse(retrievedOrderAfterEdit.isEmpty());

        for (Orders editedOrder : retrievedOrderAfterEdit) {
            //check if retrieved order have same customer name as set in the first order.    
            assertTrue(editedOrder.getCustomerName().equalsIgnoreCase("Fragoman Inc. Ltd."));
            assertTrue(editedOrder.getOrderNumber().equals(1));
        }

    }

}
