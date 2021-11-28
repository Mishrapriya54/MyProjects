package com.priyam.addressbook.app;

import com.priyam.addressbook.controller.AddressBookController;
import com.priyam.addressbook.dao.AddressBookDao;
import com.priyam.addressbook.dao.AddressBookDaoImpl;
import com.priyam.addressbook.ui.AddressBookIO;
import com.priyam.addressbook.ui.AddressBookIOImpl;
import com.priyam.addressbook.ui.AddressBookView;

public class App {
    //1. starts from here calling controller from main

    public static void main(String[] args) {
        AddressBookIO myIo = new AddressBookIOImpl();
        AddressBookView myView = new AddressBookView(myIo);
        AddressBookDao myDao = new AddressBookDaoImpl();
        AddressBookController controller = new AddressBookController(myDao, myView);
        controller.run();
    }
}
