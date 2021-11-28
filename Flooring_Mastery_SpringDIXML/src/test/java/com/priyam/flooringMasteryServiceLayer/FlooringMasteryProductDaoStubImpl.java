package com.priyam.flooringMasteryServiceLayer;

import com.priyam.flooringMasteryDao.FlooringMasteryProductsDao;
import com.priyam.flooringMasteryDao.PersistenceException;
import com.priyam.flooringMasteryDto.Products;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlooringMasteryProductDaoStubImpl implements FlooringMasteryProductsDao {

    List<Products> productsList = new ArrayList<>();
    public Products firstProduct;

    public FlooringMasteryProductDaoStubImpl() {
        Products firstProduct = new Products();
        firstProduct.setProductId(1);
        firstProduct.setProductType("Laminate");
        firstProduct.setCostPerSquareFoot(new BigDecimal("2.65"));
        firstProduct.setLaborCostPerSquareFoot(new BigDecimal("2.35"));

    }

    public FlooringMasteryProductDaoStubImpl(Products testProduct) {
        this.firstProduct = testProduct;
    }

    @Override
    public List<Products> getListOfProducts() throws PersistenceException {
        return productsList;

    }

    @Override
    public Products getProduct(int productId) throws PersistenceException {
        Products retrievedProduct = productsList.get(productId);
        return retrievedProduct;
    }

    @Override
    public void addProduct(int productId, Products product) throws PersistenceException, IOException {
        productsList.add(product);

    }
}
