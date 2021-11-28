package com.priyam.flooringMasteryServiceLayer;

import com.priyam.flooringMasteryDao.FlooringMasteryExportDao;
import com.priyam.flooringMasteryDao.FlooringMasteryOrderDao;
import com.priyam.flooringMasteryDao.FlooringMasteryProductsDao;
import com.priyam.flooringMasteryDao.FlooringMasteryTaxDao;
import com.priyam.flooringMasteryDao.InvalidInputException;
import com.priyam.flooringMasteryDao.PersistenceException;
import com.priyam.flooringMasteryDto.Orders;
import com.priyam.flooringMasteryDto.Products;
import com.priyam.flooringMasteryDto.Taxes;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    private FlooringMasteryOrderDao OrderDao;
    private FlooringMasteryTaxDao TaxDao;
    private FlooringMasteryProductsDao ProductsDao;
    private FlooringMasteryExportDao ExportDao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrderDao OrderDao, FlooringMasteryTaxDao TaxDao, FlooringMasteryProductsDao ProductsDao, FlooringMasteryExportDao ExportDao) {
        this.OrderDao = OrderDao;
        this.TaxDao = TaxDao;
        this.ProductsDao = ProductsDao;
        this.ExportDao = ExportDao;
    }
    FlooringMasteryServiceLayerImpl(FlooringMasteryOrderDao dao, FlooringMasteryExportDao export,FlooringMasteryProductsDao productDao) {
      
        this.OrderDao =dao;
      this.ExportDao = export;   
      this.ProductsDao=productDao;
    }

    @Override
    public Orders getProduct(int productId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void export() throws PersistenceException, ParseException {
        ExportDao.exportData();
    }

    @Override
    public List<Orders> getOrders(String date) throws PersistenceException, ParseException, InvalidInputException {
        //calling order dao to get orders for the given date,passing value of date parameter   
        List<Orders> ordersOfDate = OrderDao.getOrders(date);
        //return to the calling controller class
        return ordersOfDate;
    }

    @Override
    public List<Taxes> getListOfStateTax() throws PersistenceException {
        List<Taxes> listStates = TaxDao.getListOfStateTax();
        return listStates;
    }

    @Override
    public BigDecimal getTax(String stateAbbr) throws PersistenceException {
        BigDecimal stateTax = TaxDao.getTax(stateAbbr);
        System.out.println(stateTax);
        return stateTax;

    }

    @Override
    public void addTax(String stateAbbr, Taxes newTax) throws PersistenceException, IOException {
        TaxDao.addTax(stateAbbr, newTax);
    }

    @Override
    public Orders addOrder(Integer OrderNum, Orders anOrder) throws PersistenceException, IOException {

        OrderDao.addOrder(OrderNum, anOrder);
        return anOrder;
    }

    @Override
    public List<Products> getListOfProducts() throws PersistenceException {

        List<Products> listProducts = ProductsDao.getListOfProducts();
        return listProducts;

    }

    @Override
    public Orders removeOrder(String date, Orders orderToDelete) throws PersistenceException, InvalidInputException {

        Orders removedOrder=OrderDao.RemoveOrder(date, orderToDelete);
        return removedOrder;

    }

    @Override
    public void editOrder(int orderNum, Orders order, String dateInStr) throws PersistenceException, IOException, InvalidInputException {

        OrderDao.editOrder(orderNum, order, dateInStr);

    }

    @Override
    public void addProduct(int productId, Products product)throws PersistenceException,IOException {
      ProductsDao.addProduct(productId, product);
    }

}
