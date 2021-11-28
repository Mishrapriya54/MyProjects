/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Products;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlooringMasteryProductsDaoImplTest {

    private FlooringMasteryProductsDao testProductDao;

    public FlooringMasteryProductsDaoImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        String testFile = "C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\TestOrdersFiles\\testProducts.txt";
        new FileWriter(testFile);
        testProductDao = new FlooringMasteryProductsDaoImpl(testFile);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetProductsList() throws PersistenceException, IOException {
        Products firstProduct = new Products();
        int ProductIdOne = 1;
        firstProduct.setProductId(ProductIdOne);
        firstProduct.setProductType("Laminate");
        firstProduct.setCostPerSquareFoot(new BigDecimal("2.65"));
        firstProduct.setLaborCostPerSquareFoot(new BigDecimal("2.35"));

        Products secondProduct = new Products();
        int ProductIdTwo = 2;
        secondProduct.setProductId(ProductIdTwo);
        secondProduct.setProductType("Wood");
        secondProduct.setCostPerSquareFoot(new BigDecimal("3.65"));
        secondProduct.setLaborCostPerSquareFoot(new BigDecimal("4.00"));

        testProductDao.addProduct(ProductIdOne, firstProduct);
        testProductDao.addProduct(ProductIdTwo, secondProduct);

        List<Products> retrievedProductList = testProductDao.getListOfProducts();
        assertFalse(retrievedProductList.isEmpty());
        assertEquals(retrievedProductList.size(), 2, "check if size is 2");

        // Then the specifics
        assertTrue(retrievedProductList.contains(firstProduct),
                "The list should contain first product Laminate");
        assertTrue(retrievedProductList.contains(secondProduct),
                "The list should contain second product wood.");

    }

}
