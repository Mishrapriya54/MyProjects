package com.priyam.flooringMasteryServiceLayer;

import com.priyam.flooringMasteryDao.FlooringMasteryExportDao;
import com.priyam.flooringMasteryDao.FlooringMasteryExportDaoImpl;
import com.priyam.flooringMasteryDao.FlooringMasteryOrderDao;
import com.priyam.flooringMasteryDao.FlooringMasteryProductsDao;
import com.priyam.flooringMasteryDao.FlooringMasteryProductsDaoImpl;
import com.priyam.flooringMasteryDao.InvalidInputException;
import com.priyam.flooringMasteryDao.PersistenceException;
import com.priyam.flooringMasteryDto.Orders;
import com.priyam.flooringMasteryDto.Products;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlooringMasteryServiceLayerImplTest {

    private FlooringMasteryServiceLayer service;

    public FlooringMasteryServiceLayerImplTest() {

        FlooringMasteryOrderDao dao = new FlooringMasteryOrderDaoStubImpl();
        FlooringMasteryExportDao exportDao = new FlooringMasteryExportDaoImpl();
        FlooringMasteryProductsDao productDao = new FlooringMasteryProductDaoStubImpl();
        service = new FlooringMasteryServiceLayerImpl(dao, exportDao, productDao);

    }

    @Test
    public void testCreateValidOrder() throws ParseException, PersistenceException, IOException, InvalidInputException {
        Orders oneOrder = new Orders(2);

        String dateString = "12-03-2016";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date dateOfOrder = sdf.parse(dateString);
        oneOrder.setOrderDate(dateOfOrder);
        oneOrder.setCustomerName("New Customer");

        oneOrder.setState("ca");
        oneOrder.setTaxRate(new BigDecimal(7.0));
        oneOrder.setProductType("Carpet");
        oneOrder.setArea(new BigDecimal(6000));
        oneOrder.setCostPerSquareFoot(new BigDecimal(2.89));
        oneOrder.setLaborCostPerSquareFoot(new BigDecimal(4.0));
        oneOrder.setMaterialCost(new BigDecimal(6.95));
        oneOrder.setLaborCost(new BigDecimal(3.95));
        oneOrder.setTax(new BigDecimal(4.25));
        oneOrder.setTotal(new BigDecimal(6000));

        service.addOrder(2, oneOrder);

        assertEquals(1, service.getOrders(dateString).size(), "Should only have one order.");

    }

    @Test
    public void testGetAllOrders() throws Exception {
        // ARRANGE

        Orders onlyOrder = new Orders(2);

        String dateString = "12-03-2016";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date dateOfOrder = sdf.parse(dateString);
        onlyOrder.setOrderDate(dateOfOrder);
        onlyOrder.setCustomerName("New Customer");

        onlyOrder.setState("ca");
        onlyOrder.setTaxRate(new BigDecimal(7.0));
        onlyOrder.setProductType("Carpet");
        onlyOrder.setArea(new BigDecimal(6000));
        onlyOrder.setCostPerSquareFoot(new BigDecimal(2.89));
        onlyOrder.setLaborCostPerSquareFoot(new BigDecimal(4.0));
        onlyOrder.setMaterialCost(new BigDecimal(6.95));
        onlyOrder.setLaborCost(new BigDecimal(3.95));
        onlyOrder.setTax(new BigDecimal(4.25));
        onlyOrder.setTotal(new BigDecimal(6000));
        service.addOrder(2, onlyOrder);

        // ACT & ASSERT
        assertEquals(1, service.getOrders(dateString).size(),
                "Should only have one order.");
        assertTrue(service.getOrders(dateString).contains(onlyOrder),
                "The order should be onlyOrder");

        Orders secondOrder = new Orders(3);

        String dateStr = "10-10-2009";
        SimpleDateFormat sdformat = new SimpleDateFormat("MM-dd-yyyy");
        Date dateOfsecondOrder = sdf.parse(dateString);
        secondOrder.setOrderDate(dateOfsecondOrder);
        secondOrder.setCustomerName("second customer");

        secondOrder.setState("il");
        secondOrder.setTaxRate(new BigDecimal(8.0));
        secondOrder.setProductType("wood");
        secondOrder.setArea(new BigDecimal(1000));
        secondOrder.setCostPerSquareFoot(new BigDecimal(1.78));
        secondOrder.setLaborCostPerSquareFoot(new BigDecimal(3.0));
        secondOrder.setMaterialCost(new BigDecimal(5.5));
        secondOrder.setLaborCost(new BigDecimal(2.75));
        secondOrder.setTax(new BigDecimal(3.25));
        secondOrder.setTotal(new BigDecimal(5000));
        service.addOrder(3, secondOrder);

        // ACT & ASSERT
        assertEquals(2, service.getOrders(dateString).size(),
                "Should only have both order.");
        assertTrue(service.getOrders(dateString).contains(secondOrder),
                "The order list should contain secondOrder");

    }

    @Test
    public void testRemoveOrder() throws ParseException, PersistenceException, IOException, InvalidInputException {
        Orders oneOrderToRemove = new Orders(4);

        String dateString = "11-12-1985";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date dateOfOrder = sdf.parse(dateString);
        oneOrderToRemove.setOrderDate(dateOfOrder);
        oneOrderToRemove.setCustomerName("Josh");

        oneOrderToRemove.setState("Il");
        oneOrderToRemove.setTaxRate(new BigDecimal(7.0));
        oneOrderToRemove.setProductType("Carpet");
        oneOrderToRemove.setArea(new BigDecimal(6000));
        oneOrderToRemove.setCostPerSquareFoot(new BigDecimal(2.89));
        oneOrderToRemove.setLaborCostPerSquareFoot(new BigDecimal(4.0));
        oneOrderToRemove.setMaterialCost(new BigDecimal(6.95));
        oneOrderToRemove.setLaborCost(new BigDecimal(3.95));
        oneOrderToRemove.setTax(new BigDecimal(4.25));
        oneOrderToRemove.setTotal(new BigDecimal(6000));

        service.addOrder(2, oneOrderToRemove);
        assertNotNull(service.getOrders(dateString));
        assertEquals(1, service.getOrders(dateString).size(), "Should only have one order.");

        service.removeOrder(dateString, oneOrderToRemove);
        assertEquals(service.getOrders(dateString).size(), 0, "Removing one order, make list empty.");
    }

    @Test
    public void testEditOrder() throws ParseException, PersistenceException, IOException, InvalidInputException {
        Orders oneOrderToEdit = new Orders(5);

        String dateString = "11-12-1985";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date dateOfOrder = sdf.parse(dateString);
        oneOrderToEdit.setOrderDate(dateOfOrder);
        oneOrderToEdit.setCustomerName("Josh");

        oneOrderToEdit.setState("Il");
        oneOrderToEdit.setTaxRate(new BigDecimal(7.0));
        oneOrderToEdit.setProductType("Carpet");
        oneOrderToEdit.setArea(new BigDecimal(6000));
        oneOrderToEdit.setCostPerSquareFoot(new BigDecimal(2.89));
        oneOrderToEdit.setLaborCostPerSquareFoot(new BigDecimal(4.0));
        oneOrderToEdit.setMaterialCost(new BigDecimal(6.95));
        oneOrderToEdit.setLaborCost(new BigDecimal(3.95));
        oneOrderToEdit.setTax(new BigDecimal(4.25));
        oneOrderToEdit.setTotal(new BigDecimal(6000));

        service.addOrder(5, oneOrderToEdit);
        assertNotNull(service.getOrders(dateString));
        assertEquals(1, service.getOrders(dateString).size(), "Should only have one order.");
        assertEquals("Josh", service.getOrders(dateString).get(0).getCustomerName(), "Name should be Josh");

        oneOrderToEdit.setCustomerName("Josh Carlos");
        service.editOrder(5, oneOrderToEdit, dateString);

        assertEquals(1, service.getOrders(dateString).size(), "Should only have one order.");
        assertEquals("Josh Carlos", service.getOrders(dateString).get(0).getCustomerName(), "Name should be Josh Carlos");
    }

    @Test
    public void testGetAllProduct() throws ParseException, PersistenceException, IOException, InvalidInputException {
        Products firstProduct = new Products();
        int ProductIdOne = 1;
        firstProduct.setProductId(ProductIdOne);
        firstProduct.setProductType("Laminate");
        firstProduct.setCostPerSquareFoot(new BigDecimal("2.65"));
        firstProduct.setLaborCostPerSquareFoot(new BigDecimal("2.35"));
        service.addProduct(ProductIdOne, firstProduct);
        Products secondProduct = new Products();
        int ProductIdTwo = 2;
        secondProduct.setProductId(ProductIdTwo);
        secondProduct.setProductType("Wood");
        secondProduct.setCostPerSquareFoot(new BigDecimal("3.65"));
        secondProduct.setLaborCostPerSquareFoot(new BigDecimal("4.00"));
        service.addProduct(ProductIdTwo, secondProduct);
        List<Products> retrievedProductList = service.getListOfProducts();
        assertEquals(2, service.getListOfProducts().size(), "Should have two products");
        assertTrue(retrievedProductList.contains(firstProduct), "List should have first product");
        assertTrue(retrievedProductList.contains(secondProduct), "List should have second product");

    }

}
