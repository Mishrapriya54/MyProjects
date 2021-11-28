package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Orders;
import java.io.IOException;
import java.util.List;

public interface FlooringMasteryOrderDao {

//public List<Orders> getListOfOrders()throws PersistenceException;
    public List<Orders> getOrders(String Date) throws PersistenceException, InvalidInputException;

    public Orders addOrder(Integer OrderNum, Orders order) throws PersistenceException, IOException;

    public Orders RemoveOrder(String date, Orders order) throws PersistenceException, InvalidInputException;

    public void editOrder(int orderNum, Orders order, String dateInStr) throws PersistenceException, InvalidInputException;

}
