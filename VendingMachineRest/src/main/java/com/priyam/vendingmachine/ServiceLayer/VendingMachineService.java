
package com.priyam.vendingmachine.ServiceLayer;

import com.priyam.vendingmachine.DTO.Items;
import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineService{
public List<Items> getListOfItems()throws PersistenceException;
public BigDecimal deposit(BigDecimal money);
public Items getItem(int ItemId)throws PersistenceException;
public String buyItem(Items item )throws PersistenceException,InsufficientFundsException,NoItemInventoryException;
public String CashChange(BigDecimal balance,Items Item)throws PersistenceException,InsufficientFundsException,NoItemInventoryException;
public Items removeItems(int ItemId)throws PersistenceException;
public String checkBalance(Items item)throws PersistenceException,InsufficientFundsException,NoItemInventoryException;
}

