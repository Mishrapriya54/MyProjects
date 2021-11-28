package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Orders;
import com.priyam.flooringMasteryDto.Taxes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlooringMasteryTaxDaoImpl implements FlooringMasteryTaxDao {

    Map<String, Taxes> stateTax = new HashMap<>();
    public static final String DELIMITER = ",";
    public static String FOLDERPATH;

    public FlooringMasteryTaxDaoImpl() {
        FOLDERPATH = "C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\Data\\Taxes.txt";
    }

    FlooringMasteryTaxDaoImpl(String testFile) {
        FOLDERPATH = "C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\TestOrdersFiles\\testTax.txt";
    }

    @Override
    public List<Taxes> getListOfStateTax() throws PersistenceException {
        loadFile();
        return new ArrayList(stateTax.values());
    }

    @Override
    public BigDecimal getTax(String stateAbbr) throws PersistenceException {
        loadFile();
        return stateTax.get(stateAbbr).getTaxRate();

    }

    public void loadFile() throws PersistenceException {

        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(FOLDERPATH)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException(
                    "-_- Could not load data into memory.", e);
        }
        String currentLine;
        Taxes currentTax;
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTax = unmarshallTax(currentLine);

            stateTax.put(currentTax.getStateAbbreviation(), currentTax);
        }
        // close scanner
        scanner.close();
    }

    public Taxes unmarshallTax(String TaxAsText) throws PersistenceException {
        String[] TaxTokens = TaxAsText.split(DELIMITER);
        Taxes TaxFromFile = new Taxes();
        TaxFromFile.setStateAbbreviation(TaxTokens[0]);
        TaxFromFile.setStateName(TaxTokens[1]);
        TaxFromFile.setTaxRate(TaxTokens[2]);
        return TaxFromFile;
    }

    public void writeFile() throws PersistenceException, IOException {

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("FOLDERPATH"));
        } catch (IOException e) {
            throw new PersistenceException(
                    "Could not save data.", e);
        }
        String TaxText;
        List<Taxes> TaxList = this.getListOfStateTax();
        for (Taxes currentTax : TaxList) {

            TaxText = marshallTax(currentTax);

            out.println(TaxText);

            out.flush();
        }
        out.close();
    }

    public String marshallTax(Taxes aTax) {

        String TaxText = aTax.getStateAbbreviation() + DELIMITER;
        TaxText += aTax.getStateName() + DELIMITER;
        TaxText += aTax.getTaxRate();

        return TaxText;
    }

    @Override
    public void addTax(String stateAbbr, Taxes newTax) throws PersistenceException, IOException {
        stateTax.put(stateAbbr, newTax);
        writeFile();

    }
}
