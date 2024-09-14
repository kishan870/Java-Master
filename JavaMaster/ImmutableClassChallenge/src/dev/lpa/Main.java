package dev.lpa;

import dev.bank.BankAccount;
import dev.bank.BankCustomer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        BankAccount account = new BankAccount(BankAccount.AccountType.CHECKING, 500);
//        System.out.println(account);

        BankCustomer joe = new BankCustomer("Joe", 500.00,
                10000.00);
        System.out.println(joe);

        //Checking if Joe account data is immutable
        List<BankAccount> accounts = joe.getAccounts();
        accounts.clear();
        System.out.println(joe);

       // accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, 150000.00));
       // System.out.println(joe);
    }
}
