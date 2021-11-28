package com.priyam.addressbook.ui;

import com.priyam.addressbook.dto.AddressBook;
import java.util.List;

public class AddressBookView {

    private AddressBookIO io;

    public AddressBookView(AddressBookIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Please select the operation you wish to perform:");
        io.print("1.Add Address");
        io.print("2.Delete Address");
        io.print("3.Find Address");
        io.print("4.List Address Count");
        io.print("5.List All Addresses");
        io.print("6.Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public AddressBook getNewAddressInfo() {

        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String houseNum = io.readString("Please house Number");
        String street = io.readString("Please enter Street Name");
        String city = io.readString("Please enter City");
        String country = io.readString("Please enter Country");
        String zipCode = io.readString("Please enter zip code");        //from int to string
        AddressBook address = new AddressBook(lastName);
        address.setFirstName(firstName);
        address.setHouseNum(houseNum);
        address.setStreet(street);
        address.setCity(city);
        address.setZipCode(zipCode);              //from int to string
        address.setCountry(country);
        return address;
    }

    public void getAddress(AddressBook Address) {
        if (Address != null) {
            io.print(Address.getFirstName());
            io.print(Address.getLastName());
            io.print(Address.getHouseNum());
            io.print(Address.getStreet());
            io.print(Address.getCity());
            io.print(Address.getCountry());
            io.print(Address.getZipCode());
        } //from int to string
        else {
            io.print("No Address found associated with the provided Last Name Please try again.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayAddressList(List<AddressBook> AddressList) {
        for (AddressBook newItem : AddressList) {
            String addressInfo = String.format("#%s %s %s %s %s %s %s", newItem.getFirstName(), newItem.getLastName(), newItem.getHouseNum(), newItem.getStreet(), newItem.getCity(), newItem.getZipCode(), newItem.getCountry());
            io.print(addressInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public String getRemoveAddressLastName() {
        return io.readString("Please Enter the Last Name to remove the address");
    }

    public String getAddressLastName() {
        return io.readString("Please Enter the Last Name to find the address");
    }

    public void totalNumOfAddress(int num) {
        io.print("Below are the total number of address saved in DataBase");
        io.printInt(num);

    }

////////////////////////////////////////////////////////All Display Banner Methods//////////////////////////////////////////
    public void displayCreateAddressBanner() {
        io.print("=== Create Address ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Address successfully created.  Please hit enter to continue");
    }

    public void displayRemoveSuccessBanner(AddressBook removedAddress) {
        io.print("Address " + removedAddress + "Removed successfully ");
    }

    public int displayRemoveAddressBanner() {
        int deleteChoice = io.readInt("=== Really Delete?? == Press 0 for Yes / Press any other number for No");
        return deleteChoice;
    }

    public void displayAddressBanner() {
        io.readString("List of all addresses");
    }

    public void displaytotalNumberOfAddress() {
        io.readString("\nBelow are the total number of address saved in DataBase :");

    }

    public void displayAllAddress() {
        io.readString("List of All Addresses");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
