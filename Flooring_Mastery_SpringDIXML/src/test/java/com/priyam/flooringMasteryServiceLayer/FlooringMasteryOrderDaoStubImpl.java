package com.priyam.flooringMasteryServiceLayer;

import com.priyam.flooringMasteryDao.FlooringMasteryOrderDao;
import com.priyam.flooringMasteryDao.InvalidInputException;
import com.priyam.flooringMasteryDao.PersistenceException;
import com.priyam.flooringMasteryDto.Orders;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlooringMasteryOrderDaoStubImpl implements FlooringMasteryOrderDao {

    public Orders firstOrder;
    List<Orders> orderList = new ArrayList<>();

    public FlooringMasteryOrderDaoStubImpl() {
        firstOrder = new Orders(1);
        String dateString = "11-02-2022";
        Date date = new Date(11 - 02 - 2022);
        firstOrder.setOrderDate(date);
        firstOrder.setCustomerName("Alia");
        firstOrder.setState("mn");
        firstOrder.setTaxRate(new BigDecimal(3.0));
        firstOrder.setProductType("Vinayl");
        firstOrder.setArea(new BigDecimal(2000));
        firstOrder.setCostPerSquareFoot(new BigDecimal(2.10));
        firstOrder.setLaborCostPerSquareFoot(new BigDecimal(3.0));
        firstOrder.setMaterialCost(new BigDecimal(6.2));
        firstOrder.setLaborCost(new BigDecimal(3.0));
        firstOrder.setTax(new BigDecimal(2.25));
        firstOrder.setTotal(new BigDecimal(3000));
    }

    public FlooringMasteryOrderDaoStubImpl(Orders testOrder) {
        this.firstOrder = testOrder;
    }

    @Override
    public List<Orders> getOrders(String Date) throws PersistenceException, InvalidInputException {

        return orderList;

    }

    @Override
    public Orders addOrder(Integer OrderNum, Orders order) throws PersistenceException, IOException {
        orderList.add(order);
        return order;

    }

    @Override
    public Orders RemoveOrder(String date, Orders order) throws PersistenceException, InvalidInputException {

        orderList.remove(order);

        return order;

    }

    @Override
    public void editOrder(int orderNum, Orders order, String dateInStr) throws PersistenceException, InvalidInputException {
        orderList.remove(order);
        orderList.add(order);
    }

}
