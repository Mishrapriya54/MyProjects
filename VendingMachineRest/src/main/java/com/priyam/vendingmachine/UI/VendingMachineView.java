package com.priyam.vendingmachine.UI;

import com.priyam.vendingmachine.DTO.Cash;
import com.priyam.vendingmachine.DTO.Items;
import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO myIo) {
        this.io = myIo;
    }

    public int printMenu() {
        io.print("####Vending Machine#### \n : Main Menu : ");
        io.print("1:List All Items");
        io.print("2:Look for an Item");
        io.print("3:Add Money");
        io.print("4:Buy an item");
        io.print("5:Exit");
        return io.readInt("Please Enter your choice", 1, 5);

    }

    public void displayList(List<Items> list) {
        if (!list.isEmpty()) {
            list.stream().map(i -> String.format("## %s, %s $%s ", i.getItemId(), i.getItemName(), i.getItemCost())).forEachOrdered(listStr -> {
                io.print(listStr);
            });

        }
    }

    public void displayItem(Items item) {
        if (item != null) {

            String listStr = String.format("## %s, %s $%s ", item.getItemId(), item.getItemName(), item.getItemCost());
            io.print(listStr);

        }

    }
    public void displayErrorMessage(String msg) {

        io.print(msg);
    }

    public int displayGetItemPrompt() {
        int userChoice = io.readInt("Please Enter Id");
        return userChoice;
    }

    public BigDecimal displayAddMoneyPrompt() {
        return io.readBigDecimal("Please Enter The amount.", BigDecimal.ZERO, new BigDecimal(1000));
    }

    public void displayExitBanner() {
        io.print("====Good Bye====");
        io.print("====Thank You====");
    }

    public void displayUnknownCommandBanner() {
        io.print("Please Choose from the Given Options");
    }

    public void displayBalance(BigDecimal balance) {
        io.print("Your Balance is $" + balance.setScale(2));
    }

    public int displayBuyItemPrompt() {

        int id = io.readInt("To buy an item please enter its id");
        return id;
    }

    public void displayChange(String change) {
        io.print("Please Collect the change of $" + change);
    }

    public void displayNoChange(String change) {
        io.print("You have " + change + " refund.");
    }

}
