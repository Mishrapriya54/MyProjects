/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.priyam.addressbook.dao;

import com.priyam.addressbook.dto.AddressBook;
import java.util.List;

/**
 *
 * @author prade
 */
public interface AddressBookDao {

    AddressBook addAddress(String key, AddressBook address) throws AddressBookDaoException;

    List<AddressBook> getAllAddress() throws AddressBookDaoException;

    AddressBook getAddress(String lastName) throws AddressBookDaoException;

    AddressBook deleteAddress(String lastName) throws AddressBookDaoException;

    int getNumberOfAddress() throws AddressBookDaoException;
}
