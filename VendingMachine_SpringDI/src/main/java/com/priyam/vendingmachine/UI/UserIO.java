package com.priyam.vendingmachine.UI;

import java.math.BigDecimal;

public interface UserIO {

    void print(String msg);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    Boolean readBool(String prompt);

    String readString(String prompt);

    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);
}
