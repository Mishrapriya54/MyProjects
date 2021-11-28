package com.priyam.flooringMasteryDto;

import java.math.BigDecimal;

public class Taxes {

    private String StateAbbreviation;
    private String StateName;
    private BigDecimal TaxRate;

    public String getStateAbbreviation() {
        return StateAbbreviation;
    }

    public void setStateAbbreviation(String StateAbbreviation) {
        this.StateAbbreviation = StateAbbreviation;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String StateName) {
        this.StateName = StateName;
    }

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal TaxRate) {
        this.TaxRate = TaxRate;
    }

    public void setTaxRate(String Tax) {
        BigDecimal TaxBD = new BigDecimal(Tax);
        this.TaxRate = TaxBD;
    }
}
