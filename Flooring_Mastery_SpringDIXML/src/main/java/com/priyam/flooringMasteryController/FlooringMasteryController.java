
package com.priyam.flooringMasteryController;

import com.priyam.flooringMasteryDao.InvalidInputException;
import com.priyam.flooringMasteryDao.PersistenceException;
import com.priyam.flooringMasteryDto.Orders;
import com.priyam.flooringMasteryDto.Products;
import com.priyam.flooringMasteryDto.Taxes;
import com.priyam.flooringMasteryServiceLayer.FlooringMasteryServiceLayer;
import com.priyam.flooringMasteryUI.FlooringMasteryView;
import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;

public class FlooringMasteryController {
   private FlooringMasteryServiceLayer service;
   private FlooringMasteryView consoleView;

    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView consoleView) {
        this.service = service;
        this.consoleView = consoleView;
    }
   
   public void execute() throws InputMismatchException {
        boolean keepGoing=true;
        int choice=0;
 try{
       while(keepGoing){
           try{
        choice=getUserChoice();
           }catch(InvalidInputException msg) 
            {consoleView.displayErrorMessage(msg.getMessage()); execute();}
        switch(choice){
        case 1:try{
                displayOrders();
            }catch(InvalidInputException msg) 
            {consoleView.displayErrorMessage(msg.getMessage());}
                   break;
            case 2:try{addOrder();}
            catch(InvalidInputException msg) 
            {consoleView.displayErrorMessage(msg.getMessage());}
                   break;
            case 3:editOrder();
                   break;
            case 4:removeOrder();
                   break;
            case 5: exportOrder();
                   break;
            case 6: keepGoing=false;
            break;
            default: 
                   unknownCommand();
            }
       }
 }catch(PersistenceException | ParseException | InvalidInputException | IOException |InputMismatchException e)
            { consoleView.displayErrorMessage(e.getMessage());}
       exit();
    }
    //Method to show prompt and to get user choice
   private int getUserChoice() throws InputMismatchException, InvalidInputException {
     return consoleView.displayMenu();
        
    }
    //Method to display Orders filtered by the date of order
    private void displayOrders() throws PersistenceException,ParseException,InvalidInputException {
     //getting value of date entered by the user 
     String orderDate=consoleView.propmtDisplayOrders();
     //calling service layer to get the orders of the entered date by the user in a list
     if(orderDate!=null){
     List<Orders> orders=service.getOrders(orderDate);
     //calling view class to display the orders returned by the service class
     if(orders.isEmpty()){
         consoleView.displayEmptyOrderMessage();}
    else{
  consoleView.displayOrders(orders);}
     }
     else{
     execute();
    }
   }
    private void addOrder() throws ParseException,InvalidInputException, PersistenceException ,IOException{
        //calling service class to get list of tax for the states
        List<Taxes> stateTax= service.getListOfStateTax();
        //calling service class to get list of products
        List<Products> products=service.getListOfProducts();
        //calling method in view class to show promt to get values from the user, passing tax and products list to choose from the list
       Orders newOrder= consoleView.addNewOrder(stateTax,products);
       
       if(newOrder!=null){
       
     service.addOrder(newOrder.getOrderNumber(),newOrder);
        consoleView.displayOrderSuccess();
    }
       else{
           
           execute();
           
       }}

    private void editOrder() throws ParseException, PersistenceException, InvalidInputException, IOException {
       
        List<Taxes> stateTax= service.getListOfStateTax();
        List<Products> products=service.getListOfProducts();
        String dateInStr= consoleView.PromptRemoveEditOrderGetDate();
      int orderNum= consoleView.promptRemoveEditOrderGetNumber();
      Orders toEdit=null;
       List<Orders> orderToEdit=service.getOrders(dateInStr);
        if(orderToEdit==null){
   consoleView.displayAnOrderError();
    }
    else{
          if(orderToEdit!=null){
                                 for(Orders ordersInList:orderToEdit){
                                                                       if(ordersInList.getOrderNumber()==orderNum){
                                                                                                                  toEdit=ordersInList;
                                                                                                                  break;
                                                                                                                  }
                                 }
                     
    if(toEdit==null){
        consoleView.displayOrderNotPresent(dateInStr);
    }else {
   Orders order= consoleView.displayAnOrderToEdit(toEdit,stateTax,products);
    
    consoleView.displayAnOrder(order);
   String userEditSaveChoice=consoleView.promptSaveEditOrder();
    if(userEditSaveChoice.equalsIgnoreCase("yes")){
    service.editOrder(orderNum,order,dateInStr);
    consoleView.editSuccessBanner();
    }
    else{
   execute();
    }}}}}
    private void removeOrder() throws ParseException, PersistenceException, InvalidInputException {
        //Calling method in view class to get input values of date and ordernumber of an order to delete.
       String dateStr=consoleView.PromptRemoveEditOrderGetDate();
       int orderNum=consoleView.promptRemoveEditOrderGetNumber();
       //Intialize a variable of type Orders.
       Orders toDelete=null;
      //Calling service class method getorders to get all the orders of the entered Date.
      List<Orders> orderToDelete=service.getOrders(dateStr);
     // Checking if returned list of orders is empty 
    if(orderToDelete==null){
   consoleView.displayAnOrderError();
    }
    //checking if list is not empty
    else{
          if(orderToDelete!=null){
                                 //Looping through all the returned orders and check if list has same orderNumber entered by the user. 
                                 for(Orders ordersInList:orderToDelete){
                                                                       if(ordersInList.getOrderNumber()==orderNum){
                                                                                                                  //assign Order of the matched order number to the initialized toDelete variable.
                                                                                                                  toDelete=ordersInList;
                                                                                                                  }
                                 }
    //if order number not found display message.                 
    if(toDelete==null){
        consoleView.displayOrderNotPresent(dateStr);
    }
    //if order number found prompt to display for confirmation to delete
    else {String userChoice= consoleView.displayOrderDeletePrompt(toDelete);
 //if yes is entered call service layer method to delete the order,given date and order number as parameter    
if(userChoice.equalsIgnoreCase("yes")){
service.removeOrder(dateStr,toDelete);
//display order delete success
consoleView.displayOrderDeleteSuccess();
  }
//if no is entered show menu to choose options.
else{execute();}
    }
    }
    //end of first else       
    }
    //end of method
    }
    private void exportOrder() throws PersistenceException, ParseException,InvalidInputException  {
       service.export();
  }

    private void unknownCommand() {
            consoleView.displayUnknownCommandBanner();
}

    private void exit() {
       consoleView.displayExitBanner();
    }
}
