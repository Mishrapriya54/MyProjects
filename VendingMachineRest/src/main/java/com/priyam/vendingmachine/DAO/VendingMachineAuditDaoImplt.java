package com.priyam.vendingmachine.DAO;

import com.priyam.vendingmachine.ServiceLayer.PersistenceException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class VendingMachineAuditDaoImplt implements VendingMachineAuditDAO {

    public static final String INVENTORY_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws PersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE, true));
        } catch (IOException e) {
            throw new PersistenceException("Could not Log information.", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
        out.flush();
    }
}
