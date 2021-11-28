
package com.priyam.vendingmachine.DAO;

import com.priyam.vendingmachine.ServiceLayer.PersistenceException;

public interface VendingMachineAuditDAO {
     public void writeAuditEntry(String entry) throws PersistenceException;
}
