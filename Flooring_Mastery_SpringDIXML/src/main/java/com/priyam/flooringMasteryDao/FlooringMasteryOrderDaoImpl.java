package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Orders;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlooringMasteryOrderDaoImpl implements FlooringMasteryOrderDao {

    private Map<Integer, Orders> orders = new HashMap<>();
    public static final String DELIMITER = ",";
    private final String folderPath;

    public FlooringMasteryOrderDaoImpl() {
        folderPath = "Orders";
    }

    FlooringMasteryOrderDaoImpl(String testFile) {
        folderPath = "TestOrdersfiles";
    }

    @Override
    public List<Orders> getOrders(String Date) throws PersistenceException, InvalidInputException {
        //calling getDate method to go through all the orders and get the txt file for the desired date and returning the orders for the same.
        List<Orders> orders = getOrderOfDate(Date);

        return orders;
    }

    private List<Orders> getOrderOfDate(String Date) throws PersistenceException, InvalidInputException {
        //Instantiating File and intializing constructor with desired path,where folderPath has all txt files.
        File folderName = new File(folderPath);
        //saving all files in an array.
        File files[] = folderName.listFiles();
        Scanner scanner;
        //declaring and Initializing list with null as an instance variable to make it scope available for the for block and return.
        List<Orders> orderOfDate = new ArrayList<>();
        //looping through all files saved in Dir array
        for (File fileName : files) {
            //converting a fileName to string and saving in a variable         
            String strfileName = fileName.toString();
            //splitting file name on "_" and accessing index 1 to access date.txt format 
            String splittedNameValue[] = strfileName.split("_");
            String orderFileDateTxtString = splittedNameValue[1];
            //splitting file name againg on "." and accessing index 0 to access date value,as split takes regex and . in regex shows any character so for that reason using \\ to show the splitting character here.
            String orderFileDate[] = orderFileDateTxtString.split("\\.");
            String dateOfOrder = orderFileDate[0];
            //check if entered date and file name dates are equal
            if (dateOfOrder.equals(Date)) {
                //if equal,calling loadFile,passing fileName for which dates are equal to get the saved data from the file  
                orderOfDate = loadFile(fileName);
                break;
            }
        }
        return orderOfDate;
    }

    private List<Orders> loadFile(File fileName) throws PersistenceException {

        Scanner scanner;
        //clearing Map order as just to have values present in the current file.

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException(
                    "-_- Could not load data into memory.", e);
        }
        orders.clear();
        String currentLine;
        Orders currentOrder;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentOrder = unmarshallOrder(currentLine);

            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }

        scanner.close();
        //returning list of orders from the file of the given date to the calling service class
        return new ArrayList(orders.values());

    }

    private Orders unmarshallOrder(String OrderAsText) throws PersistenceException {

        String[] OrderTokens = OrderAsText.split(DELIMITER);

        Orders OrderFromFile = new Orders();
        OrderFromFile.setOrderNumber(OrderTokens[0]);
        OrderFromFile.setCustomerName(OrderTokens[1]);
        OrderFromFile.setState(OrderTokens[2]);
        OrderFromFile.setTaxRate(new BigDecimal(OrderTokens[3]));
        OrderFromFile.setProductType(OrderTokens[4]);
        OrderFromFile.setArea(new BigDecimal(OrderTokens[5]));
        OrderFromFile.setCostPerSquareFoot(new BigDecimal(OrderTokens[6]));
        OrderFromFile.setLaborCostPerSquareFoot(new BigDecimal(OrderTokens[7]));
        OrderFromFile.setMaterialCost(new BigDecimal(OrderTokens[8]));
        OrderFromFile.setLaborCost(new BigDecimal(OrderTokens[9]));
        OrderFromFile.setTax(new BigDecimal(OrderTokens[10]));
        OrderFromFile.setTotal(new BigDecimal(OrderTokens[11]));
        return OrderFromFile;
    }

    private void writeFile(File fileName, Orders order) throws PersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            throw new PersistenceException(
                    "Could not save data.", e);
        }
        String OrderText;
        OrderText = marshallOrder(order);
        out.println(OrderText);

        out.flush();
        out.close();

    }

    private void writeFile(File fileName) throws PersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(fileName, false));
        } catch (IOException e) {
            throw new PersistenceException(
                    "Could not save data.", e);
        }

        String OrderText;

        for (Orders currentOrder : this.orders.values()) {

            OrderText = marshallOrder(currentOrder);

            out.println(OrderText);

            out.flush();
        }
        out.close();
    }

    private String marshallOrder(Orders anOrder) {
        String OrderText = anOrder.getOrderNumber() + DELIMITER;

        OrderText += anOrder.getCustomerName() + DELIMITER;

        OrderText += anOrder.getState() + DELIMITER;

        OrderText += anOrder.getTaxRate() + DELIMITER;

        OrderText += anOrder.getProductType() + DELIMITER;
        OrderText += anOrder.getArea() + DELIMITER;
        OrderText += anOrder.getCostPerSquareFoot() + DELIMITER;
        OrderText += anOrder.getLaborCostPerSquareFoot() + DELIMITER;
        OrderText += anOrder.getMaterialCost() + DELIMITER;
        OrderText += anOrder.getLaborCost() + DELIMITER;
        OrderText += anOrder.getTax() + DELIMITER;
        OrderText += anOrder.getTotal();

        return OrderText;
    }

    private int getOrderNum(File fileName) throws PersistenceException {
        //lastOrderNum is given 0 and 1 starting order number as if for a file have only one order and if its one order gets deleted, file will be existed in orders folder 
        //and first order number will be started as 1,which will be incremented and returned otherwise starting point will be 1,and loop through all the orders.
        int lastOrderNum;
        if (fileName.length() != 0) {
            lastOrderNum = 1;

            List<Orders> anOrder = loadFile(fileName);
            for (Orders countOrders : anOrder) {
                lastOrderNum = countOrders.getOrderNumber();

            }
            return (lastOrderNum + 1);

        }
        return lastOrderNum = 1;
    }

    @Override
    public Orders addOrder(Integer OrderNum, Orders order) throws PersistenceException, IOException {

        orders.put(OrderNum, order);
        createFile(order);
        return order;
    }

    private void createFile(Orders order) throws PersistenceException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String convertedOrderDate = sdf.format(order.getOrderDate());
        File fileName = new File(folderPath + "\\Order_" + convertedOrderDate + ".txt");

        if (fileName.exists()) {

            int i = getOrderNum(fileName);
            //additional setOrderNumber method to accept int as a parameter
            order.setOrderNumber(i);
            orders.put(i, order);
            writeFile(fileName, order);
        } else {
            //fileName.createNewFile();
            writeFile(fileName, order);
        }
    }

    @Override
    public Orders RemoveOrder(String dateStr, Orders order) throws PersistenceException, InvalidInputException {

        File fileName = getOrderFromFile(dateStr);

        loadFile(fileName);
        orders.remove(order.getOrderNumber());

        writeFile(fileName);
        return order;

    }

    private File getOrderFromFile(String date) throws PersistenceException, InvalidInputException {
        //Instantiating File and intializing constructor with desired path,where folderPath has all txt files.
        File folderName = new File(folderPath);
        //saving all files in an array.
        File files[] = folderName.listFiles();
        Scanner scanner;
        File orderFile = null;
        //declaring and Initializing list with null as an instance variable to make it scope available for the for block and return.
        List<Orders> orderDate = null;
        //looping through all files saved in Dir array
        for (File fileName : files) {
            //converting a fileName to string and saving in a variable         
            String strfileName = fileName.toString();
            //splitting file name on "_" and accessing index 1 to access date.txt format 
            String splittedNameValue[] = strfileName.split("_");
            String orderFileDateTxtString = splittedNameValue[1];
            //splitting file name againg on "." and accessing index 0 to access date value,as split takes regex and . in regex shows any character so for that reason using \\ to show the splitting character here.
            String orderFileDate[] = orderFileDateTxtString.split("\\.");
            String dateOfOrder = orderFileDate[0];
            //check if entered date and file name dates are equal
            if (dateOfOrder.equals(date)) {
                //if equal,calling loadFile,passing fileName for which dates are equal to get the saved data from the file  
                orderDate = loadFile(fileName);
                orderFile = fileName;
                break;
            }
        }
        return orderFile;
    }

    @Override
    public void editOrder(int orderNum, Orders order, String dateInStr) throws PersistenceException, InvalidInputException {

        File fileNameEdit = new File(folderPath + "\\Order_" + dateInStr + ".txt");

        loadFile(fileNameEdit);

        orders.remove(orderNum);
        writeFile(fileNameEdit);
        orders.put(orderNum, order);

        writeFile(fileNameEdit, order);

    }

}
