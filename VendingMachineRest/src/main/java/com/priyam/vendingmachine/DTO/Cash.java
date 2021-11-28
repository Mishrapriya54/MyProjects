package com.priyam.vendingmachine.DTO;

import java.math.BigDecimal;

public class Cash {

    private BigDecimal cash;

    public Cash(BigDecimal Balance) {
        this.cash = Balance;

    }

    public String getBalance() {
        String change_amount;
        BigDecimal change ;
        int change_quarters = 0;
        int change_dimes = 0;
        int change_nickel = 0;
        int change_penny = 0;
        Coins[] coins = Coins.values();
        do {

            for (Coins c : coins) {
                //multiplying cash value by 100 to get value in pennies.
                change = cash.multiply(new BigDecimal(100));
                //setting value of pennies.
                c.setPenny(change);
                //dividing penny value by 25 and converting it to int to get nondecimal value.
                if (change.compareTo(new BigDecimal(25)) == 1 || change.compareTo(new BigDecimal(25)) == 0) {
                    change_quarters = change.divide(new BigDecimal(25)).intValue();
                    //converting quarter to BigDecimal and setting the quarters
                    c.setQuarter(new BigDecimal(change_quarters));
                    //subtracting qurters*25 value from total number of pennies to get the remaining amount and setting new number 
                    change = change.subtract(new BigDecimal(change_quarters * 25));

                }

                if (change.compareTo(new BigDecimal(10)) == 1 || change.compareTo(new BigDecimal(10)) == 0) {
                    //dividing penny value by 10 and converting it to int to get nondecimal value.
                    change_dimes = change.divide(new BigDecimal(10)).intValue();
                    //converting quarter to BigDecimal and setting the dimes
                    c.setDime(new BigDecimal(change_dimes));
                    //subtracting dimes*10 value from total number of pennies to get the remaining amount and setting new number 
                    change = change.subtract(new BigDecimal(change_dimes * 10));
                }

                if (change.compareTo(new BigDecimal(5)) == 1 || change.compareTo(new BigDecimal(5)) == 0) {
                    //dividing penny value by 5 and converting it to int to get nondecimal value.
                    change_nickel = change.divide(new BigDecimal(5)).intValue();
                    //converting nickel to BigDecimal and setting the nickel
                    c.setNickel(new BigDecimal(change_nickel));
                    //subtracting nickel*5 value from total number of pennies to get the remaining amount and setting new number 
                    change = change.subtract(new BigDecimal(change_nickel * 5));
                }

                if (change.compareTo(new BigDecimal(1)) == 1 || change.compareTo(new BigDecimal(1)) == 0) {

                    c.setPenny(change);
                    change_penny = change.intValue();

                    change = new BigDecimal(0);
                }
                //check to see if change is equal to 0 then break the loop.
                if (change.compareTo(new BigDecimal(0)) == 0) {

                    break;
                }
            }
            change_amount = cash.toString() + "::" + Coins.QUARTER.name() + "->" + change_quarters + "," + Coins.DIME.name() + "->" + change_dimes + "," + Coins.NICKEL.name() + "->" + change_nickel + "," + Coins.PENNY.name() + "->" + change_penny;
            //come out of loop if cash is 0
        } while (cash.compareTo(new BigDecimal(0)) == 0);
        return change_amount;
    }

    public enum Coins {
        QUARTER(new BigDecimal("25")),
        NICKEL(new BigDecimal("5")),
        DIME(new BigDecimal("10")),
        PENNY(new BigDecimal("1"));

        private Coins(BigDecimal value) {
            this.value = value;
        }

        public BigDecimal getValue() {
            return value;
        }
        private BigDecimal quarter;
        private BigDecimal nickel;
        private BigDecimal dime;
        private BigDecimal penny;
        private BigDecimal value;

        public BigDecimal getQuarter() {
            return quarter;
        }

        public void setQuarter(BigDecimal quarter) {
            this.quarter = quarter;
        }

        public BigDecimal getNickel() {
            return nickel;
        }

        public void setNickel(BigDecimal nickel) {
            this.nickel = nickel;
        }

        public BigDecimal getDime() {
            return dime;
        }

        public void setDime(BigDecimal dime) {
            this.dime = dime;
        }

        public BigDecimal getPenny() {
            return penny;
        }

        public void setPenny(BigDecimal penny) {
            this.penny = penny;
        }
    }

}
