
package com.priyam.addressbook.controller;

import com.priyam.addressbook.dao.AddressBookDao;
import com.priyam.addressbook.dao.AddressBookDaoException;
import com.priyam.addressbook.dao.AddressBookDaoImpl;
import com.priyam.addressbook.dto.AddressBook;
import com.priyam.addressbook.ui.AddressBookIO;
import com.priyam.addressbook.ui.AddressBookIOImpl;
import com.priyam.addressbook.ui.AddressBookView;
import java.util.List;

public class AddressBookController {
   
        private AddressBookView view;
  private AddressBookDao dao;

  public AddressBookController(AddressBookDao dao, AddressBookView view) {
    this.dao = dao;
    this.view = view;
}
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
        while (keepGoing) {
            
    menuSelection = getMenuSelection();


      switch (menuSelection) {
    case 1:
         createAddress();
        break;
    case 2:
       removeAddress();
        break;
    case 3:
        getAddress();
        break;
    case 4:
        getToatlNumber();
        break;
    case 5:
        listAllAddress();
        break;
    case 6:
       keepGoing = false;
           break; 
    default:
        unknownCommand();
          }
        }
exitMessage();
    }catch (AddressBookDaoException e){
        view.displayErrorMessage(e.getMessage());
    }}
    private void createAddress()throws AddressBookDaoException {
    view.displayCreateAddressBanner();
    AddressBook newaddress = view.getNewAddressInfo();
    dao.addAddress(newaddress.getLastName(),newaddress);
        view.displayCreateSuccessBanner();
    }
    private void removeAddress()throws AddressBookDaoException{
    
    String lastNameToRemove=view.getRemoveAddressLastName();
    int deleteChoice=view.displayRemoveAddressBanner();
    if(deleteChoice==1){
    AddressBook removedAddress=dao.deleteAddress(lastNameToRemove);
    
    view.displayRemoveSuccessBanner(removedAddress);}
    
    }
    private void getAddress()throws AddressBookDaoException {
    String lastName=view.getAddressLastName();
    AddressBook getAddress=dao.getAddress(lastName);
        view.getAddress(getAddress);
    }
    private void getToatlNumber()throws AddressBookDaoException{
    int num=dao.getNumberOfAddress();
    view.totalNumOfAddress(num);
    }
    private void listAllAddress()throws AddressBookDaoException{
    view.displayAllAddress();
    List <AddressBook> addressList=dao.getAllAddress();
    view.displayAddressList(addressList);
    }
    
    private void unknownCommand(){
    view.displayUnknownCommandBanner();
}

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    private void exitMessage() {
    view.displayExitBanner();
}
}

