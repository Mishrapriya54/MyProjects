package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Orders;
import com.priyam.flooringMasteryDto.Products;
import java.io.IOException;
import java.util.List;

public interface FlooringMasteryProductsDao {

    public List<Products> getListOfProducts() throws PersistenceException;

    public Products getProduct(int productId) throws PersistenceException;

    public void addProduct(int productId, Products product) throws PersistenceException, IOException;

}
