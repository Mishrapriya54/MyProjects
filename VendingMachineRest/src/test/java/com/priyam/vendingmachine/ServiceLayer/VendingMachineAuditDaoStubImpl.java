package com.priyam.vendingmachine.ServiceLayer;

import com.priyam.vendingmachine.DAO.VendingMachineAuditDAO;

public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDAO {

    @Override
    public void writeAuditEntry(String entry) throws PersistenceException {
       // do nothing...
    }
    
}
