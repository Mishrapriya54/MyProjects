package com.priyam.addressbook.dao;

import com.priyam.addressbook.dto.AddressBook;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookDaoImpl implements AddressBookDao {

    private Map<String, AddressBook> address = new HashMap<>();
    private final String Address_FILE;
    public static final String DELIMITER = "::";

    public AddressBookDaoImpl() {
        Address_FILE = "addresses";
    }

    public AddressBookDaoImpl(String addressTextFile) {
        Address_FILE = "addressTextFile";
    }

    @Override
    public AddressBook addAddress(String key, AddressBook addresses) throws AddressBookDaoException {
        loadFile();
        AddressBook prevAddress = address.put(key, addresses);
        writeAddress();
        return prevAddress;
    }

    @Override
    public List<AddressBook> getAllAddress() throws AddressBookDaoException {
        loadFile();
        return new ArrayList<>(address.values());
    }

    @Override
    public AddressBook getAddress(String lastName) throws AddressBookDaoException {
        loadFile();
        return address.get(lastName);
    }

    @Override
    public AddressBook deleteAddress(String lastName) throws AddressBookDaoException {
        loadFile();
        AddressBook removedAddress = address.remove(lastName);
        writeAddress();
        return removedAddress;
    }

    @Override
    public int getNumberOfAddress() {
        return address.size();
    }
    private AddressBook unmarshallAddressBook(String addressAsText) {
        String[] addressTokens = addressAsText.split(DELIMITER);    ///////*********************/////////
        String addressId = addressTokens[1];
        AddressBook addressFromFile = new AddressBook(addressId);
        addressFromFile.setFirstName(addressTokens[0]);
        //addressFromFile.setlasttName(addressTokens[1]);
        addressFromFile.setHouseNum(addressTokens[2]);
        addressFromFile.setStreet(addressTokens[3]);
        addressFromFile.setCity(addressTokens[4]);
        addressFromFile.setCountry(addressTokens[5]);
        addressFromFile.setZipCode(addressTokens[6]);
        return addressFromFile;
        //  String dvdId = dvdFields[0];
        //   DvdLibDto dvdFromFile = new DvdLibDto(dvdId);
    }

    private void loadFile() throws AddressBookDaoException {
        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(Address_FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookDaoException(
                    "-_- Could not load data into memory.", e);
        }
        String currentLine;
        AddressBook currentAddress;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentAddress = unmarshallAddressBook(currentLine);
            address.put(currentAddress.getLastName(), currentAddress);
        }

        scanner.close();
    }

    private String marshallAddress(AddressBook anAddress) {

        String addressAsText = anAddress.getFirstName() + DELIMITER;
        addressAsText += anAddress.getLastName() + DELIMITER;
        addressAsText += anAddress.getHouseNum() + DELIMITER;
        addressAsText += anAddress.getStreet() + DELIMITER;
        addressAsText += anAddress.getCity() + DELIMITER;
        addressAsText += anAddress.getCountry() + DELIMITER;
        addressAsText += anAddress.getZipCode();
        return addressAsText;
    }

    private void writeAddress() throws AddressBookDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(Address_FILE));
        } catch (IOException e) {
            throw new AddressBookDaoException(
                    "Could not save data.", e);
        }
        String addressAsText;
        List<AddressBook> addressList = this.getAllAddress();
        for (AddressBook currentAddress : addressList) {

            addressAsText = marshallAddress(currentAddress);

            out.println(addressAsText);
            out.flush();
        }
        // Clean up
        out.close();
    }

}
