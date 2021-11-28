package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Taxes;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlooringMasteryTaxDaoImplTest {

    private FlooringMasteryTaxDao testTaxDao;

    public FlooringMasteryTaxDaoImplTest() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        String testFile = "C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\TestOrdersFiles\\testTax.txt";
        new FileWriter(testFile);
        testTaxDao = new FlooringMasteryTaxDaoImpl(testFile);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetTaxList() throws PersistenceException, IOException {
        Taxes stateOne = new Taxes();
        String firstStateAbbr = "CA";
        stateOne.setStateAbbreviation(firstStateAbbr);
        stateOne.setStateName("California");
        stateOne.setTaxRate(new BigDecimal("25"));

        Taxes stateTwo = new Taxes();
        String secondStateAbbr = "MN";
        stateTwo.setStateAbbreviation(secondStateAbbr);
        stateTwo.setStateName("Minneapolis");
        stateTwo.setTaxRate(new BigDecimal("3.35"));
        testTaxDao.addTax(firstStateAbbr, stateOne);
        testTaxDao.addTax(secondStateAbbr, stateTwo);
        List<Taxes> retrievedTaxList = testTaxDao.getListOfStateTax();
        assertFalse(retrievedTaxList.isEmpty());
        assertEquals(retrievedTaxList.size(), 2, "check if size is 2");
        // Then the specifics
        assertTrue(retrievedTaxList.contains(stateOne),
                "The list should contain California");
        assertTrue(retrievedTaxList.contains(stateTwo),
                "The list should contain Minneapolis");

    }

}
