package com.priyam.flooringMasteryDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Orders {

    private Integer OrderNumber = 1;
    private String CustomerName;
    private String State;
    private BigDecimal TaxRate;
    private String ProductType;
    private BigDecimal Area;
    private BigDecimal CostPerSquareFoot;
    private BigDecimal LaborCostPerSquareFoot;
    private BigDecimal MaterialCost;
    private BigDecimal LaborCost;
    private BigDecimal Tax;
    private BigDecimal Total;
    private Date orderDate;

    public Orders() {
    }

    @Override
    public String toString() {
        return "Orders{" + "OrderNumber=" + OrderNumber + ", CustomerName=" + CustomerName + ", State=" + State + ", TaxRate=" + TaxRate + ", ProductType=" + ProductType + ", Area=" + Area + ", CostPerSquareFoot=" + CostPerSquareFoot + ", LaborCostPerSquareFoot=" + LaborCostPerSquareFoot + ", MaterialCost=" + MaterialCost + ", LaborCost=" + LaborCost + ", Tax=" + Tax + ", Total=" + Total + ", orderDate=" + orderDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.OrderNumber);
        hash = 47 * hash + Objects.hashCode(this.CustomerName);
        hash = 47 * hash + Objects.hashCode(this.State);
        hash = 47 * hash + Objects.hashCode(this.TaxRate);
        hash = 47 * hash + Objects.hashCode(this.ProductType);
        hash = 47 * hash + Objects.hashCode(this.Area);
        hash = 47 * hash + Objects.hashCode(this.CostPerSquareFoot);
        hash = 47 * hash + Objects.hashCode(this.LaborCostPerSquareFoot);
        hash = 47 * hash + Objects.hashCode(this.MaterialCost);
        hash = 47 * hash + Objects.hashCode(this.LaborCost);
        hash = 47 * hash + Objects.hashCode(this.Tax);
        hash = 47 * hash + Objects.hashCode(this.Total);
        hash = 47 * hash + Objects.hashCode(this.orderDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Orders other = (Orders) obj;
        if (!Objects.equals(this.CustomerName, other.CustomerName)) {
            return false;
        }
        if (!Objects.equals(this.State, other.State)) {
            return false;
        }
        if (!Objects.equals(this.ProductType, other.ProductType)) {
            return false;
        }
        if (!Objects.equals(this.OrderNumber, other.OrderNumber)) {
            return false;
        }
        if (!Objects.equals(this.TaxRate, other.TaxRate)) {
            return false;
        }
        if (!Objects.equals(this.Area, other.Area)) {
            return false;
        }
        if (!Objects.equals(this.CostPerSquareFoot, other.CostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.LaborCostPerSquareFoot, other.LaborCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.MaterialCost, other.MaterialCost)) {
            return false;
        }
        if (!Objects.equals(this.LaborCost, other.LaborCost)) {
            return false;
        }
        if (!Objects.equals(this.Tax, other.Tax)) {
            return false;
        }
        if (!Objects.equals(this.Total, other.Total)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        return true;
    }

    public Orders(int ordNumber) {
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderNumber() {

        return OrderNumber;
    }

    public void setOrderNumber(String OrderToken) {

        int orderNum = Integer.parseInt(OrderToken);

        this.OrderNumber = orderNum;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal TaxRate) {
        this.TaxRate = TaxRate;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public BigDecimal getArea() {
        return Area;
    }

    public void setArea(BigDecimal Area) {
        this.Area = Area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal CostPerSquareFoot) {
        this.CostPerSquareFoot = CostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal LaborCostPerSquareFoot) {
        this.LaborCostPerSquareFoot = LaborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return MaterialCost;
    }

    public void setMaterialCost(BigDecimal MaterialCost) {
        this.MaterialCost = MaterialCost;
    }

    public BigDecimal getLaborCost() {
        return LaborCost;
    }

    public void setLaborCost(BigDecimal LaborCost) {
        this.LaborCost = LaborCost;
    }

    public BigDecimal getTax() {
        return Tax;
    }

    public void setTax(BigDecimal Tax) {
        this.Tax = Tax;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }

    public void setOrderNumber(int i) {
        this.OrderNumber = i;
    }

}
