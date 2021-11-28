package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Taxes;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface FlooringMasteryTaxDao {

    public List<Taxes> getListOfStateTax() throws PersistenceException;

    public BigDecimal getTax(String stateAbbr) throws PersistenceException;

    public void addTax(String stateAbbr, Taxes newTax) throws PersistenceException, IOException;
}
