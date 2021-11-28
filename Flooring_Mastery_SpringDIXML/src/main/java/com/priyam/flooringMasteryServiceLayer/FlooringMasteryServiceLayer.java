package com.priyam.flooringMasteryServiceLayer;

import com.priyam.flooringMasteryDao.InvalidInputException;
import com.priyam.flooringMasteryDao.PersistenceException;
import com.priyam.flooringMasteryDto.Orders;
import com.priyam.flooringMasteryDto.Products;
import com.priyam.flooringMasteryDto.Taxes;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

public interface FlooringMasteryServiceLayer {

    public List<Orders> getOrders(String date) throws PersistenceException, ParseException, InvalidInputException;

    public Orders getProduct(int productId) throws PersistenceException;

    public void export() throws PersistenceException, ParseException;

    public List<Taxes> getListOfStateTax() throws PersistenceException;

    public BigDecimal getTax(String stateAbbr) throws PersistenceException;

    public void addTax(String stateAbbr, Taxes newTax) throws PersistenceException, IOException;

    public Orders addOrder(Integer OrderNum, Orders anOrder) throws PersistenceException, IOException;

    public List<Products> getListOfProducts() throws PersistenceException;

    public Orders removeOrder(String Date, Orders orderTodelete) throws PersistenceException, InvalidInputException;

    public void editOrder(int orderNum, Orders order, String dateInStr) throws PersistenceException, IOException, InvalidInputException;

    public void addProduct(int productId, Products product) throws PersistenceException, IOException;
}
