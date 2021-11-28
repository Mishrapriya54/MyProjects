package com.priyam.vendingmachine.ServiceLayer;

import com.priyam.vendingmachine.DAO.VendingMachineDao;
import com.priyam.vendingmachine.DTO.Items;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao {
public Items onlyItem;

public VendingMachineDaoStubImpl(){
onlyItem=new Items(1000);
onlyItem.setItemName("Sprite");
onlyItem.setItemCost("1.5");
onlyItem.setItemQuantity("5");
}
public VendingMachineDaoStubImpl(Items testItem){

this.onlyItem=testItem;
}
@Override
    public List<Items> getListOfItems() throws PersistenceException {
        List<Items> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    
    }

    @Override
    public Items getItem(int ItemId) throws PersistenceException {
    
        
        if (ItemId==onlyItem.getItemId()) {
            return onlyItem;
        } else {
            return null;
        }       
    }

    @Override
    public void updateQuantity(int quantity) throws PersistenceException {
            
    }

    @Override
    public Items addItem(int ItemId, Items item) throws PersistenceException {
        if (ItemId==onlyItem.getItemId()) {
            return onlyItem;
        } else {
            return null;
        }
    }
    @Override
    public Items removeItem(int itemId) throws PersistenceException {
        
        if (itemId==onlyItem.getItemId()) {
            return onlyItem;
        } else {
            return null;
        }
    }
    
   
    
    
    
    

    
}
