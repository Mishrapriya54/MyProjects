package com.priyam.flooringMasteryDto;

import java.math.BigDecimal;

public class Products {

    private int ProductId;
    private String ProductType;
    private BigDecimal CostPerSquareFoot;
    private BigDecimal LaborCostPerSquareFoot;

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public void setProductId(String strProductId) {
        int convertedProductId = Integer.parseInt(strProductId);
        //int i=Integer.parseInt(s);  
        this.ProductId = convertedProductId;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public void setCostPerSquareFoot(String CostPerSquareFoot) {
        BigDecimal convertedStrCost = new BigDecimal(CostPerSquareFoot);
        this.CostPerSquareFoot = convertedStrCost;
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

    public void setLaborCostPerSquareFoot(String LaborCostPerSquareFoot) {

        BigDecimal convertedStrLbrCost = new BigDecimal(LaborCostPerSquareFoot);

        this.LaborCostPerSquareFoot = convertedStrLbrCost;
    }

}
