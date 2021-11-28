package com.priyam.vendingmachine.ServiceLayer;

import com.priyam.vendingmachine.DAO.VendingMachineAuditDAO;
import com.priyam.vendingmachine.DAO.VendingMachineDao;
import com.priyam.vendingmachine.DTO.Cash;
import com.priyam.vendingmachine.DTO.Items;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineServiceImpl implements VendingMachineService {

    private VendingMachineAuditDAO audit;

    private static BigDecimal Balance = new BigDecimal(0);
    private VendingMachineDao dao;

    public VendingMachineServiceImpl(VendingMachineDao myDao, VendingMachineAuditDAO myAudit) {
        this.dao = myDao;
        this.audit = myAudit;
    }

    public VendingMachineServiceImpl() {

    }

    @Override
    public List<Items> getListOfItems() throws PersistenceException {
        //getting list from DAO and filtering the items in inventory which have 0 quantity and returning to the controller
        return dao.getListOfItems().stream().filter((p) -> p.getItemQuantity() > 0).collect(Collectors.toList());

    }

    @Override
    public Items getItem(int ItemId) throws PersistenceException {
        return dao.getItem(ItemId);
    }

    @Override
    public String buyItem(Items item) throws PersistenceException, InsufficientFundsException, NoItemInventoryException {

        Balance = Balance.subtract(item.getItemCost());
        if (Balance.compareTo(new BigDecimal(0)) == 0) {
            return "0";
        }
        //Calling CashChange method to do the calculations
        String change_cash = CashChange(Balance, item);

        //calling DTO method to decrease the quantity. 
        int updatedQ = item.updateQuantity();
        //calling dao to update quantity in the file.
        dao.updateQuantity(updatedQ);

        audit.writeAuditEntry(
                "Item " + item.getItemName() + " is vended");

        return change_cash;
    }

    @Override
    public Items removeItems(int ItemId) throws PersistenceException {
        Items removedItems = dao.removeItem(ItemId);
        // Write to audit log
        audit.writeAuditEntry("Item " + ItemId + " REMOVED.");
        return removedItems;
    }

    public BigDecimal deposit(BigDecimal money) {
        Balance = Balance.add(money);
        return Balance;
    }

    public BigDecimal getBalance() {
        
       // return deposit(new BigDecimal(0));  commented this nd returned balance directly.
return Balance;
    }

    @Override
    public String CashChange(BigDecimal balance, Items item) throws PersistenceException, InsufficientFundsException, NoItemInventoryException {

        Cash cash = new Cash(Balance);
        String change_cash = cash.getBalance();
        Balance = new BigDecimal(0);
        return change_cash;

    }

    @Override
    public String checkBalance(Items item) throws PersistenceException, InsufficientFundsException, NoItemInventoryException {
        //Initializing the returning string to increase its scope across the blocks
        String change = "";
        if (Balance.compareTo(item.getItemCost()) == -1) {
        throw new InsufficientFundsException("Amount is not sufficient to purchase this item,Please add some money");
        } else if (item.getItemQuantity() == 0) {
            throw new NoItemInventoryException("Sorry,Item is not available.");

        } else //This method returns -1 if the BigDecimal is less than val, 1 if the BigDecimal is greater than val and 0 if the BigDecimal is equal to val
        if (Balance.compareTo(item.getItemCost()) == 1 || Balance.compareTo(item.getItemCost()) == 0) {

            change = buyItem(item);
        }
        return change;
    }

}
