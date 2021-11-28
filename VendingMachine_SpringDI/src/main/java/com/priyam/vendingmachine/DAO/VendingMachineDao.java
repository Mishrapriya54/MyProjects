package com.priyam.vendingmachine.DAO;

import com.priyam.vendingmachine.DTO.Items;
import com.priyam.vendingmachine.ServiceLayer.PersistenceException;
import java.util.List;

public interface VendingMachineDao  {
public List<Items> getListOfItems()throws PersistenceException;
public Items getItem(int ItemId)throws PersistenceException;
public void updateQuantity(int quantity)throws PersistenceException;
public Items addItem(int ItemId,Items item)throws PersistenceException;
public Items removeItem(int itemId)throws PersistenceException;


}