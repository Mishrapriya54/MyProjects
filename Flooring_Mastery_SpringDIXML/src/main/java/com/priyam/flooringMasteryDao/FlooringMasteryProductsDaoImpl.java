package com.priyam.flooringMasteryDao;

import com.priyam.flooringMasteryDto.Products;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlooringMasteryProductsDaoImpl implements FlooringMasteryProductsDao {

    Map<Integer, Products> products = new HashMap<>();
    public static final String DELIMITER = ",";
    public static String FOLDERPATH;

    public FlooringMasteryProductsDaoImpl() {
        FOLDERPATH = "C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\Data\\Products.txt";
    }

    FlooringMasteryProductsDaoImpl(String testFile) {
        FOLDERPATH = "C:\\Priya_swguild_project\\Repos\\online-java-2021-Mishrapriya54\\Flooring_Mastery\\TestOrdersFiles\\testProducts.txt";
    }

    @Override
    public List<Products> getListOfProducts() throws PersistenceException {
        loadFile();
        return new ArrayList(products.values());
    }

    @Override
    public void addProduct(int productId, Products product) throws PersistenceException, IOException {
        products.put(productId, product);
        writeFile();

    }

    @Override
    public Products getProduct(int productId) throws PersistenceException {
        loadFile();
        return products.get(productId);

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
        Products currentProduct;
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentProduct = unmarshallProduct(currentLine);

            products.put(currentProduct.getProductId(), currentProduct);
        }
        // close scanner
        scanner.close();
    }

    public Products unmarshallProduct(String productAsText) throws PersistenceException {
        String[] productTokens = productAsText.split(DELIMITER);
        Products productFromFile = new Products();
        productFromFile.setProductId(productTokens[0]);
        productFromFile.setProductType(productTokens[1]);
        productFromFile.setCostPerSquareFoot(productTokens[2]);
        productFromFile.setLaborCostPerSquareFoot(productTokens[3]);
        return productFromFile;
    }

    public void writeFile() throws PersistenceException, IOException {

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("FOLDERPATH"));
        } catch (IOException e) {
            throw new PersistenceException(
                    "Could not save data.", e);
        }
        String productText;
        List<Products> productList = this.getListOfProducts();
        for (Products currentProduct : productList) {

            productText = marshallProduct(currentProduct);

            out.println(productText);

            out.flush();
        }
        out.close();
    }

    public String marshallProduct(Products aProduct) {

        String productText = aProduct.getProductId() + DELIMITER;
        productText += aProduct.getProductType() + DELIMITER;

        productText += aProduct.getCostPerSquareFoot() + DELIMITER;
        productText += aProduct.getLaborCostPerSquareFoot();

        return productText;
    }

}
