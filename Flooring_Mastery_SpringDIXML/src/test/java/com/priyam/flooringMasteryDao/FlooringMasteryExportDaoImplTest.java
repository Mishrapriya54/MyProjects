
package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Orders;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlooringMasteryExportDaoImplTest {
private FlooringMasteryExportDao testExportDao;
private FlooringMasteryOrderDao testOrderDao;
public FlooringMasteryExportDaoImplTest() {
    }
    @BeforeEach
    public void setUp() throws Exception{
      String testFile = "C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\TestOrdersFiles\\testFile.txt";
        new FileWriter(testFile);
        testExportDao = new FlooringMasteryExportDaoImpl(testFile);
    }
    
    @AfterEach
    public void tearDown() throws IOException {
    File testFile = new File( "C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\TestOrdersFiles");
    //PrintWriter writer = new PrintWriter(testFile);
    File[] entries = testFile.listFiles();
    if (entries != null) {
    for (File entry : entries) {
    entry.deleteOnExit();
}
  }
    }
    
    @Test
    public void testExportOrders() throws ParseException, PersistenceException, IOException, InvalidInputException {
    int orderNumOne= 001;
        Orders firstOrder = new Orders(orderNumOne);
      // Date orderDate= new Date(12-3-2022);
      
      
String dateString = "11-02-2022";
SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date dateObject =sdf.parse(dateString);
        
        
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
       firstOrder.setTax(new BigDecimal (2.25));
       firstOrder.setTotal(new BigDecimal(3000));
     //  testOrderDao.addOrder(orderNumOne, firstOrder);
  
     int orderNumTwo= 002;
        Orders secondOrder = new Orders(orderNumTwo);
        
        String dateStringSecond = "12-12-2023";
SimpleDateFormat sdFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date dateSecondOrder =sdFormat.parse(dateStringSecond);
      // Date orderDate= new Date(12-3-2022);
     secondOrder.setOrderDate(dateSecondOrder);
      secondOrder.setCustomerName("Sam");
       secondOrder.setState("Ca");
       secondOrder.setTaxRate(new BigDecimal(8.0));
     secondOrder.setProductType("Wood");
      secondOrder.setArea(new BigDecimal(5000));
       secondOrder.setCostPerSquareFoot(new BigDecimal(3.35));
       secondOrder.setLaborCostPerSquareFoot(new BigDecimal(4.0));
      secondOrder.setMaterialCost(new BigDecimal(7.5));
       secondOrder.setLaborCost(new BigDecimal(3.35));
       secondOrder.setTax(new BigDecimal (6.25));
      secondOrder.setTotal(new BigDecimal(10000));
      // testOrderDao.addOrder(orderNumTwo, secondOrder);
      // Orders testOrderDao.getOrders(dateString);
    // testExportDao.exportData();
   
       
        
       
    }
    
}
