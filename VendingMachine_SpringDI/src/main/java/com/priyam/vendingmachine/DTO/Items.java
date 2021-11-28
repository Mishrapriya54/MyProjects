package com.priyam.vendingmachine.DTO;

import java.math.BigDecimal;
import java.util.Objects;

public class Items {

    private int ItemId;
    private String ItemName;
    private BigDecimal ItemCost;
    private int ItemQuantity;

    public Items(String itemId) {
        int ItemId = Integer.parseInt(itemId);
        this.ItemId = ItemId;
    }
    
    public Items(int itemId) {
       
        this.ItemId = itemId;
    }

    @Override
    public String toString() {
        return "Items{" + "ItemId=" + ItemId + ", ItemName=" + ItemName + ", ItemCost=" + ItemCost + ", ItemQuantity=" + ItemQuantity + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.ItemId;
        hash = 23 * hash + Objects.hashCode(this.ItemCost);
        hash = 23 * hash + this.ItemQuantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Items other = (Items) obj;
        return true;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public void setItemCost(String ItemCost) {
        BigDecimal cost = new BigDecimal(ItemCost);
        this.ItemCost = cost;
    }

    public void setItemQuantity(String ItemNumber) {
        int itemNmbr = Integer.parseInt(ItemNumber);

        this.ItemQuantity = itemNmbr;
    }

    public int getItemId() {
        return ItemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public BigDecimal getItemCost() {
        return ItemCost;
    }

    public int getItemQuantity() {
        return ItemQuantity;
    }

    public int updateQuantity() {
        ItemQuantity = ItemQuantity - 1;
        return ItemQuantity;
    }

}
