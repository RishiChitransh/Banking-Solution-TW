package model;

import java.util.*;
import exceptions.*;

public class Bank {
  private Set<Account> accounts;

  public Bank() {
    accounts = new HashSet<>();
  }

  public void createBankAccount(int accId, Owner own, String ifsc) {
    Account newAcc = new Account(accId, own, ifsc);
    accounts.add(newAcc);
    System.out.println("New Account created with ID: "+accId+" & IFSC Code: "+ifsc); 
  }

   Account findAccount(int accId) throws NoBankAccountExistsException {
     for(Account account : accounts) {
       if (account.accountId == accId) {
          return account;
       }
     }

      throw new NoBankAccountExistsException(String.format("There is no account with this id (%s). Please check Account ID and try again.", accId));
   }
  public void deposit(int accId) {

    try {
      Account currentAcc = findAccount(accId);
      currentAcc.depositMoney();
    } catch (NoBankAccountExistsException e) {
        System.err.println(e.getMessage());
    }

  }

  public void balance(int accId) {

    try {
      Account currentAcc = findAccount(accId);
      System.out.println(currentAcc.getBalance());
    } catch (NoBankAccountExistsException e) {
        System.err.println(e.getMessage());
    }

  }

  public void withdraw(int accId) {

    try {
      Account currentAcc = findAccount(accId);
      currentAcc.withdrawMoney();
    } catch (NoBankAccountExistsException e) {
        System.err.println(e.getMessage());
    } catch (InsufficientFundsException | WithdrawLimitExceeded e) {
      System.err.println(e.getMessage());
    }

  }

  public void history(int accId) {

    try {
      Account currentAcc = findAccount(accId);

      List<String> operations = currentAcc.getTransactionHistory();

      System.out.println("List of Transactions for account: "+currentAcc.accountId);

      for(String operation : operations){
          System.out.println(operation);
      }

    } catch (NoBankAccountExistsException e) {
        System.err.println(e.getMessage());
    }

  }
}
