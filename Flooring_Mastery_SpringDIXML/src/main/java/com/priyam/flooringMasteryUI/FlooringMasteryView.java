package com.priyam.flooringMasteryUI;

import com.priyam.flooringMasteryDao.InvalidInputException;
import com.priyam.flooringMasteryDao.PersistenceException;
import com.priyam.flooringMasteryDto.Orders;
import com.priyam.flooringMasteryDto.Products;
import com.priyam.flooringMasteryDto.Taxes;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public class FlooringMasteryView {

    private UserIO userIo;

    public FlooringMasteryView(UserIO userIo) {
        this.userIo = userIo;
    }

    public void displayErrorMessage(String msg) {

        userIo.print(msg);
    }

    public int displayMenu() throws InputMismatchException, InvalidInputException {
        userIo.print("****************************************************************************");
        userIo.print("***********************");
        userIo.print("*<<Flooring Programs>>");
        userIo.print("* 1:Display Orders");
        userIo.print("* 2:Add an Order");
        userIo.print("* 3:Edit an Order");
        userIo.print("* 4:Remove an Order");
        userIo.print("* 5:Export All Data");
        userIo.print("* 6:Quit");
        userIo.print("****************************************************************************");
        userIo.print("***********************");
        try {
            return userIo.readInt("Please Enter your choice", 1, 6);
        } catch (InputMismatchException e) {
            throw new InvalidInputException("Please enter valid input", e);
        }

    }

//    public void displayExportOrders(List<Orders> orderList) {
//        if (!orderList.isEmpty()) {
//            orderList.stream().map(i -> String.format("## %s, %s %s %s %s %s %s %s %s %s %s %s", i.getOrderNumber(), i.getCustomerName(), i.getState(), i.getTaxRate(), i.getProductType(), i.getArea(), i.getCostPerSquareFoot(), i.getLaborCostPerSquareFoot(), i.getMaterialCost(), i.getLaborCost(), i.getTax(), i.getTotal())).forEachOrdered(listStr -> userIo.print(listStr));
//
//        }
//    }
    public void displayAnOrder(Orders order) {
        if (order != null) {
            String listStr = String.format("## %s, %s %s %s %s %s %s %s %s %s %s %s", order.getOrderNumber(), order.getCustomerName(), order.getState(), order.getTaxRate(), order.getProductType(), order.getArea(), order.getCostPerSquareFoot(), order.getLaborCostPerSquareFoot(), order.getMaterialCost(), order.getLaborCost(), order.getTax(), order.getTotal());
            userIo.print(listStr);
        }

    }

    public Orders displayAnOrderToEdit(Orders order, List<Taxes> stateTax, List<Products> products) {
        if (order != null) {

            //String listStr = String.format("## %s, %s %s %s %s %s %s %s %s %s %s %s", order.getOrderNumber(), order.getCustomerName(), order.getState(),order.getTaxRate(),order.getProductType(),order.getArea(),order.getCostPerSquareFoot(),order.getLaborCostPerSquareFoot(),order.getMaterialCost(),order.getLaborCost(),order.getTax(),order.getTotal());
            //userIo.print(listStr);
            userIo.print("Customer Name :" + order.getCustomerName());
            String userChoiceCustomerName = userIo.readStringNextLine("Edit Customer Names?\n");

            if (!userChoiceCustomerName.isEmpty()) {
                order.setCustomerName(userChoiceCustomerName);
            }

            userIo.print("State: " + order.getState());
            BigDecimal taxRate = new BigDecimal(0);
            String state;
            String userChoiceStateName;

            do {
                userChoiceStateName = userIo.readStringNextLine("Edit State?\n");
                for (Taxes st : stateTax) {
                    if (userChoiceStateName.equalsIgnoreCase(st.getStateAbbreviation()) && !userChoiceStateName.isEmpty()) {
                        taxRate = st.getTaxRate();
                        userIo.print(taxRate + " is the TaxRate for the " + st.getStateName());
                    }
                }
                if (taxRate.compareTo(new BigDecimal(0)) == 0 && (!userChoiceStateName.isEmpty())) {
                    userIo.print("You are not authorized to sell in the entered state,please enter correct state.");
                }
            } while (taxRate.compareTo(new BigDecimal(0)) == 0 && (!userChoiceStateName.isEmpty()));

            if (!userChoiceStateName.isEmpty()) {

                order.setState(userChoiceStateName);

            }

            userIo.print("Product Type :" + order.getProductType());
            String userChoiceProductType = userIo.readStringNextLine("Edit Product Type?\n");

            if (!userChoiceProductType.isEmpty()) {

                order.setProductType(userChoiceProductType);

            }

            userIo.print("Area :" + order.getArea().toString());

            BigDecimal userChoiceArea = userIo.readBigDecimal("Edit Area?\n", new BigDecimal(100), new BigDecimal(10000000));

            if (!userChoiceArea.toString().isEmpty()) {

                order.setArea(userChoiceArea);

            }

        }

        return order;

    }

    public Orders addNewOrder(List<Taxes> stateTax, List<Products> products) throws ParseException, InvalidInputException, PersistenceException {

        String orderDate = userIo.readDate("Please enter Date: in MM-dd-yyyy format.", true);
        if (orderDate == null) {

            return null;
        }

        Date convertedOrderDate = new SimpleDateFormat("MM-dd-yyyy").parse(orderDate);

        String customerName;
        boolean nameValid = false;
        do {
            customerName = userIo.readString("Please enter customer name");
            if (!customerName.isEmpty()) {
                nameValid = isFullname(customerName);
            } else {
                userIo.print("Customer Name is mandatory.");
            }
        } while (nameValid == false);

        //Declaring taxRate and state at instance level so values can be accessed after do while loop.
        BigDecimal taxRate = new BigDecimal(0);
        String state;
        do {
            state = userIo.readString("Please enter state abbreviation to get the tax rate.");

            for (Taxes st : stateTax) {
                if (state.equalsIgnoreCase(st.getStateAbbreviation())) {
                    taxRate = st.getTaxRate();
                    userIo.print(taxRate + " is the TaxRate for the " + st.getStateName());
                }
            }
            if (taxRate.compareTo(new BigDecimal(0)) == 0) {
                userIo.print("You are not authorized to sell in the entered state,please enter correct state.");
            }
        } while (taxRate.compareTo(new BigDecimal(0)) == 0);
        userIo.print("\n##ProductId,ProductType,CostPerSquareFoot,LaborCostPerSquareFoot");
        displayProducts(products);
        //get user input for product Type
        String productType = userIo.readString("Please choose product type from the above product list");
        //assign a variable for while to check if entered product type in the list or not.
        boolean productNotMatch = true;

//check the product type
        while (productNotMatch) {

            for (Products oneProduct : products) {
                //check against each saved product type values in the products 
                if (oneProduct.getProductType().equalsIgnoreCase(productType)) {

                    //assign while loop variable false if product matches
                    productNotMatch = false;
//break for each loop
                    break;
                }
            }
            //if it doesnt match,prompt and get user input.
            if (productNotMatch == true) {
                userIo.print("Entered product is not available");
                productType = userIo.readString("Please choose product type from the above product list");

            }

        }

        BigDecimal area = userIo.readBigDecimal("Please enter area,Minimum size is 100sq ft.", new BigDecimal(100), new BigDecimal(10000000));

        BigDecimal MaterialCost = new BigDecimal(0);
        BigDecimal CostPerSquareFoot = new BigDecimal(0);
        BigDecimal LaborCost = new BigDecimal(0);
        BigDecimal Tax = new BigDecimal(0);
        BigDecimal Total = new BigDecimal(0);
        BigDecimal LaborCostPerSquareFoot = new BigDecimal(0);
        for (Products currentProduct : products) {
            if (productType.equalsIgnoreCase(currentProduct.getProductType())) {
                CostPerSquareFoot = currentProduct.getCostPerSquareFoot();
                LaborCostPerSquareFoot = currentProduct.getLaborCostPerSquareFoot();
                MaterialCost = (area.multiply(LaborCostPerSquareFoot));
                LaborCost = (area.multiply(LaborCostPerSquareFoot));
                Tax = (MaterialCost.add(LaborCost).multiply(taxRate.divide(new BigDecimal(100))));
                Total = MaterialCost.add(LaborCost).add(Tax);
            }
        }
        Orders newOrder = new Orders();
        //newOrder.setOrderNumber();
        newOrder.setOrderDate(convertedOrderDate);
        newOrder.setCustomerName(customerName);
        newOrder.setState(state.toUpperCase());
        newOrder.setTaxRate(taxRate);

        newOrder.setProductType(productType.substring(0, 1).toUpperCase() + productType.substring(1).toLowerCase());
        newOrder.setArea(area);
        newOrder.setCostPerSquareFoot(CostPerSquareFoot);
        newOrder.setLaborCostPerSquareFoot(LaborCostPerSquareFoot);
        newOrder.setMaterialCost(MaterialCost);
        newOrder.setLaborCost(LaborCost);
        newOrder.setTax(Tax);

        newOrder.setTotal(Total);
        return newOrder;

    }

    private boolean isFullname(String customerName) {

        String expression = "^[a-zA-Z0-9 .'-]*$";

        return customerName.matches(expression);

    }

    public String propmtDisplayOrders() throws ParseException, InvalidInputException {
        String orderDate = userIo.readDate("Please enter the date to see the order in MM-dd-yyyy format", false);

        return orderDate;
    }

    public void displayOrders(List<Orders> orders) throws InvalidInputException, ParseException {
        //if there are no orders for the entered date print error msg
        if (orders == null) {
            userIo.print("Sorry No order present for the given date");
        } else {
            orders.stream().map(i -> String.format("## %s, %s %s %s %s %s %s %s %s %s %s %s", i.getOrderNumber(), i.getCustomerName(), i.getState(), i.getTaxRate(), i.getProductType(), i.getArea(), i.getCostPerSquareFoot(), i.getLaborCostPerSquareFoot(), i.getMaterialCost(), i.getLaborCost(), i.getTax(), i.getTotal())).forEachOrdered(listStr -> userIo.print(listStr));

        }
    }

    public void displayProducts(List<Products> products) throws InvalidInputException, ParseException {
        //if there are no orders for the entered date print error msg
        if (products == null) {
            userIo.print("Sorry No product present.");
        } else {
            products.stream().map(i -> String.format("## %s, %s %s %s ", i.getProductId(), i.getProductType(), i.getCostPerSquareFoot(), i.getLaborCostPerSquareFoot())).forEachOrdered(listStr -> userIo.print(listStr));

        }
    }

    public void displayUnknownCommandBanner() {
        userIo.print("Please Choose from the Given Options");
    }

    public void displayExitBanner() {
        userIo.print("====Good Bye====");
        userIo.print("====Thank You====");
    }

    public void displayOrderSuccess() {
        userIo.print("Order saved successfully.");
    }

    public int promptRemoveEditOrderGetNumber() throws InvalidInputException {
        int orderNum = userIo.readInt("Please enter order number.");
        return orderNum;
    }

    public String PromptRemoveEditOrderGetDate() throws ParseException {
        String dateStr = null;
        Date date;
        boolean keepGoing = true;
        while (keepGoing) {
            dateStr = userIo.readString("Please enter Date: in MM-dd-yyyy format.");
            try {
                date = new SimpleDateFormat("MM-dd-yyyy").parse(dateStr);
                keepGoing = false;
            } catch (ParseException e) {
                userIo.print(e.getMessage());
                userIo.print("Please Enter valid date.");
                //continue;
            }
        }
        return dateStr;
    }

    public String displayOrderDeletePrompt(Orders toDelete) {
        displayAnOrder(toDelete);
        String userChoice = userIo.readString("Do you really want to delete above order? Type Yes Or No");
        return userChoice;

    }

    public void displayOrderNotPresent(String dateStr) {

        userIo.print("Sorry no Order present for the given order number");

    }

    public void displayAnOrderError() {
        userIo.print("There is no order present for the given date");
    }

    public void displayOrderDeleteSuccess() {
        userIo.print("Order deleted successfully.");

    }

    public String promptSaveEditOrder() {
        String userEditChoice = userIo.readString("Save the changes??Press yes or no");

        return userEditChoice;
    }

    public void displayEmptyOrderMessage() {
        userIo.print("Sorry no order present for the given date.");
    }

    public void editSuccessBanner() {
        userIo.print("Order details edited successfully.");
    }

}
