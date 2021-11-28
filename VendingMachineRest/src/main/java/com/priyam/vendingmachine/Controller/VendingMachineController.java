package com.priyam.vendingmachine.Controller;

import com.priyam.vendingmachine.DTO.Items;
import com.priyam.vendingmachine.ServiceLayer.InsufficientFundsException;
import com.priyam.vendingmachine.ServiceLayer.NoItemInventoryException;
import com.priyam.vendingmachine.ServiceLayer.PersistenceException;
import com.priyam.vendingmachine.ServiceLayer.VendingMachineService;
import com.priyam.vendingmachine.ServiceLayer.VendingMachineServiceImpl;
import com.priyam.vendingmachine.UI.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {
private VendingMachineService service;
private VendingMachineView view;
VendingMachineServiceImpl serviceImpl=new VendingMachineServiceImpl(); 
   
public VendingMachineController(VendingMachineService myService, VendingMachineView myView) {
        this.service=myService;
         this.view=myView;
    }
public void execute() {
    boolean keepGoing=true;
  try{
       while(keepGoing){
        int choice=getUserChoice();
        switch(choice){
        
            case 1:listItems();
                   break;
            case 2:getItemDetails();
                   break;
            case 3:addMoney();
                   break;
            case 4:try{buyItem();}
                   catch (InsufficientFundsException e){
                   view.displayErrorMessage(e.getMessage());
                   //redisplay the balance amount after the insufficient amount.  
                   view.displayBalance(serviceImpl.getBalance());
                   }
                   catch (NoItemInventoryException e){
                   view.displayErrorMessage(e.getMessage());
                   //redisplay the balance amount after the Out of stock exception.  
                   view.displayBalance(serviceImpl.getBalance());
                   }
                   break;
            case 5: 
                   keepGoing=false;
                   break;
            default: 
                   unknownCommand();
            }
       }
       }catch(PersistenceException e)
            { view.displayErrorMessage(e.getMessage());}
       exit();
 }
    private int getUserChoice() {
        return view.printMenu();
    }
    private void listItems()throws PersistenceException{
    List<Items> list=service.getListOfItems();
    view.displayList(list);
    }
    private Items getItemDetails()throws PersistenceException {
    int userChoice=view.displayGetItemPrompt();
    Items item=service.getItem(userChoice);
    view.displayItem(item);
    return item;
    }
    private void addMoney() {
    BigDecimal money=view.displayAddMoneyPrompt();
    BigDecimal balance= service.deposit(money);
    view.displayBalance(balance);
    }
    private void exit() {
    view.displayExitBanner();
    }
    private void  unknownCommand() {
    view.displayUnknownCommandBanner();
    }
    private void buyItem()throws PersistenceException,InsufficientFundsException,NoItemInventoryException {
    Items item= getItemDetails();
    String change=service.checkBalance(item);
    if(change.equals("0")){
    view.displayNoChange(change);
    }else{
        view.displayChange(change);}
    }
}
