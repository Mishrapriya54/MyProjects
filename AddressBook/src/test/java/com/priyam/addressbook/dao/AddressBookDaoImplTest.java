package com.priyam.addressbook.dao;
import com.priyam.addressbook.dto.AddressBook;
import java.io.FileWriter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressBookDaoImplTest {
AddressBookDaoImpl testDao;
public AddressBookDaoImplTest() {
    }
  @BeforeEach
    public void setUp() throws Exception {
        String testFile="addressTextFile";
        new FileWriter(testFile);
        testDao=new AddressBookDaoImpl(testFile);
    }
@Test
public void testCreateAddress()throws Exception{
String lastName="priya";
AddressBook address =new AddressBook(lastName);
address.setFirstName("mishra");
address.setHouseNum("223");
address.setStreet("loman street");
address.setCity("chicago");
address.setCountry("usa");
address.setZipCode("22365");
//  Add the address to the DAO
testDao.addAddress(lastName, address);
// Get the address from the DAO
AddressBook retrievedAddress = testDao.getAddress(lastName);
    // Check the data is equal
    assertEquals(address.getLastName(),
                retrievedAddress.getLastName(),
                "Checking LastName");
    assertEquals(address.getFirstName(),
                retrievedAddress.getFirstName(),
                "Checking FirstName");
    assertEquals(address.getHouseNum(),
                retrievedAddress.getHouseNum(),
                "Checking HouseNum");
    assertEquals(address.getStreet(),
                retrievedAddress.getStreet(),
                "Checking Street");
    assertEquals(address.getCity(),
                retrievedAddress.getCity(),
                "Checking City");
    assertEquals(address.getCountry(),
                retrievedAddress.getCountry(),
                "Checking Country");
    assertEquals(address.getZipCode(),
                retrievedAddress.getZipCode(),
                "Checking ZipCode");
}
@Test
public void testDeleteAddress()throws Exception{
String lastName1="shreya";
AddressBook firstAddress =new AddressBook(lastName1);
firstAddress.setFirstName("mist");
firstAddress.setHouseNum("256");
firstAddress.setStreet("lomananderst street");
firstAddress.setCity("florida");
firstAddress.setCountry("usa");
firstAddress.setZipCode("99999");
//  Add the address to the DAO
String lastName2="riya";
AddressBook secondAddress =new AddressBook(lastName2);
secondAddress.setFirstName("rish");
secondAddress.setHouseNum("888");
secondAddress.setStreet("new street");
secondAddress.setCity("milwakee");
secondAddress.setCountry("usa");
secondAddress.setZipCode("55555");
//  Add the address to the DAO
testDao.addAddress(lastName1, firstAddress);  
testDao.addAddress(lastName2, secondAddress);  

AddressBook removedAddress=testDao.deleteAddress(secondAddress.getLastName());
assertEquals(removedAddress,secondAddress,"The removed address first and last name should be rish riya");

List<AddressBook> allAddress=testDao.getAllAddress();
assertNotNull( allAddress, "All students list should be not null.");
assertEquals(1,allAddress.size(),"All address should have only 1 address.");

assertFalse(allAddress.contains(secondAddress),"All addresses should not include rish riya");
assertTrue(allAddress.contains(firstAddress),"All addresses should include shreya mist");

removedAddress=testDao.deleteAddress(firstAddress.getLastName());
assertEquals(removedAddress,firstAddress,"The removed address first and last name should be shreya mist");

allAddress=testDao.getAllAddress();
assertTrue(allAddress.isEmpty(),"The retrived list of address should be empty.");

AddressBook retrievedAddress=testDao.getAddress(firstAddress.getLastName());
assertNull(retrievedAddress, "Shreya was removed should be null.");

retrievedAddress = testDao.getAddress(secondAddress.getLastName());
assertNull(retrievedAddress, "riya was removed, should be null.");
}

@Test
public void testGetTotalNumberOfAddress()throws Exception{
String lastName1="asher";
AddressBook firstAddress =new AddressBook(lastName1);
firstAddress.setFirstName("piller");
firstAddress.setHouseNum("777");
firstAddress.setStreet("richmond street");
firstAddress.setCity("minneapolis");
firstAddress.setCountry("usa");
firstAddress.setZipCode("85854");

String lastName2="hasan";
AddressBook secondAddress =new AddressBook(lastName2);
secondAddress.setFirstName("josh");
secondAddress.setHouseNum("5566");
secondAddress.setStreet("very new street");
secondAddress.setCity("duluth");
secondAddress.setCountry("usa");
secondAddress.setZipCode("26266");

//  Add the address to the DAO
testDao.addAddress(lastName1, firstAddress);  
testDao.addAddress(lastName2, secondAddress);  
List<AddressBook> savedAddresses=testDao.getAllAddress();
assertEquals(2,savedAddresses.size(),"Saved addresses have two entries of addresses");

}
  @Test
public void testAddGetAllStudents() throws Exception {
    // Create first address
    String lastName1="asher";
AddressBook firstAddress =new AddressBook(lastName1);
firstAddress.setFirstName("piller");
firstAddress.setHouseNum("777");
firstAddress.setStreet("richmond street");
firstAddress.setCity("minneapolis");
firstAddress.setCountry("usa");
firstAddress.setZipCode("85854");

// Create our second address
String lastName2="hasan";
AddressBook secondAddress =new AddressBook(lastName2);
secondAddress.setFirstName("josh");
secondAddress.setHouseNum("5566");
secondAddress.setStreet("very new street");
secondAddress.setCity("duluth");
secondAddress.setCountry("usa");
secondAddress.setZipCode("26266");

    // Add both to the DAO
    testDao.addAddress(firstAddress.getLastName(), firstAddress);
    testDao.addAddress(secondAddress.getLastName(), secondAddress);

    // Retrieve the list of all address within the DAO
    List<AddressBook> allAddress = testDao.getAllAddress();

    // First check the general contents of the list
    assertNotNull(allAddress, "The list must not be null");
    assertEquals(2, allAddress.size(),"List should have 2 addresses.");

    // Then the specifics
    assertTrue(testDao.getAllAddress().contains(firstAddress),
                "The list should include asher.");
   assertTrue(testDao.getAllAddress().contains(secondAddress),
                "The list should include hasan.");

}
    
   
  
    
}



   
    

