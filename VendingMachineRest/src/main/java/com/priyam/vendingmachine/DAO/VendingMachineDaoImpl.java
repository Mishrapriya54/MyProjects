package com.priyam.vendingmachine.DAO;

import com.priyam.vendingmachine.DTO.Items;
import com.priyam.vendingmachine.ServiceLayer.PersistenceException;
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

public class VendingMachineDaoImpl implements VendingMachineDao {

    private Map<Integer, Items> items = new HashMap<>();

    private final String ITEM_FILE;

    public static final String DELIMITER = "::";

    public VendingMachineDaoImpl() {
        ITEM_FILE = "VendingMachineInventory";
    }

    public VendingMachineDaoImpl(String itemTestFile) {
        ITEM_FILE = itemTestFile;
    }

    @Override
    public List<Items> getListOfItems() throws PersistenceException {

        loadFile();

        return new ArrayList(items.values());

    }

    @Override
    public Items getItem(int ItemId) throws PersistenceException {
        loadFile();
        return items.get(ItemId);
    }

    private void loadFile() throws PersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }

        String currentLine;
        Items currentItem;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentItem = unmarshallItem(currentLine);

            items.put(currentItem.getItemId(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private Items unmarshallItem(String itemAsText) throws PersistenceException {

        String[] itemTokens = itemAsText.split(DELIMITER);
        String itemId = itemTokens[0];
        Items itemFromFile = new Items(itemId);
        itemFromFile.setItemName(itemTokens[1]);
        itemFromFile.setItemCost(itemTokens[2]);
        itemFromFile.setItemQuantity(itemTokens[3]);

        return itemFromFile;
    }

    @Override
    public void updateQuantity(int ItemId) throws PersistenceException {

        writeFile();

    }

    private void writeFile() throws PersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            throw new PersistenceException(
                    "Could not save data.", e);
        }

        String itemText;
        List<Items> itemList = this.getListOfItems();
        for (Items currentItem : itemList) {

            itemText = marshallItem(currentItem);

            out.println(itemText);

            out.flush();
        }
        out.close();
    }

    private String marshallItem(Items anItem) {

        String itemText = anItem.getItemId() + DELIMITER;

        itemText += anItem.getItemName() + DELIMITER;

        itemText += anItem.getItemCost() + DELIMITER;

        itemText += anItem.getItemQuantity();

        return itemText;
    }

    @Override
    public Items addItem(int ItemId, Items item) throws PersistenceException {
        loadFile();
        Items newItem = items.put(ItemId, item);
        writeFile();
        return newItem;
    }

    @Override
    public Items removeItem(int itemId) throws PersistenceException {
        loadFile();
        Items removedItem = items.remove(itemId);
        writeFile();
        return removedItem;
    }

}
