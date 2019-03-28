package model;

import java.util.*;
import exceptions.*;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    int accountId;
    Owner owner;
    String IFSCcode;
    double accountBalance;
    private List<String> operations;
    final double dailyLimit = 2500.0;
    private ReentrantLock rLock;

   public Account(int accountId,Owner owner,String IFSCcode){
    this.accountId = accountId;
    this.owner = owner;
    this.IFSCcode = IFSCcode;
    this.accountBalance = 0.0;
    this.operations = new ArrayList<>();
    rLock = new ReentrantLock();
    //Recording account initiation
    addOperationToList(String.format("Account Initiated. ID: %s %n", this.accountId));
    addOperationToList(String.format("Available balance: %s  %n", this.accountBalance));
   }

    public void addOperationToList(String operation) {
      this.operations.add(operation);
    }

    public List<String> getTransactionHistory(){
        List<String> operationsList = this.operations;
        return operationsList;
    }

    public String getBalance() {
        return String.format("Current account balance is: %s",this.accountBalance);
    }

    public void depositMoney() throws NoBankAccountExistsException{

      if(this.accountId <= 0) {
        throw new NoBankAccountExistsException(String.format("There is no account with this id (%s). Please check Account ID and try again.", accountId));
      }

      Scanner deposit = new Scanner(System.in);
      System.out.println("please enter the deposit amount");

      double depositAmount = deposit.nextDouble();

      rLock.lock();
      try {
          this.accountBalance += depositAmount;

          System.out.println("your current balance: " + this.accountBalance);

          addOperationToList(String.format("%s Deposited: %n", depositAmount));
          addOperationToList(String.format("Available balance: %s %n", this.accountBalance));

        } catch (Exception e) {
          //System.err.println(e.getMessage());
          throw e;
        } finally {
          rLock.unlock();
        }
    }
    //withdrawl money  and set daily withdrawl limit
    public void withdrawMoney() throws InsufficientFundsException, WithdrawLimitExceeded{

        if(this.accountBalance == 0.0) {
          throw new InsufficientFundsException("There is no balance. Please deposit before withdrawing.");
        }

        Scanner withDraw = new Scanner(System.in);
        System.out.println("please enter the withdraw amount");
        double withdrawMoney = withDraw.nextDouble();

        rLock.lock();
        try {
          if(this.accountBalance < withdrawMoney) {
            throw new InsufficientFundsException("Sorry, the operation could not be completed. Insufficient funds.");
          }
          else if (withdrawMoney > this.dailyLimit) {
              throw new WithdrawLimitExceeded(String.format("You have exceeded the daily withdrawal limit. %s is the current daily limit.%n",this.dailyLimit));
          }
          else {
              this.accountBalance -= withdrawMoney;
              System.out.println("your current balance is: " + this.accountBalance);
              addOperationToList(String.format("%s Withdrawn %n", withdrawMoney));
              addOperationToList(String.format("Available balance: %s %n", this.accountBalance));
          }
        } catch (Exception e) {
          throw e;
        } finally {
          rLock.unlock();
        }
    }

    public String toString(){

        return String.format("Hello, Details are as follows: %n Account ID: " + this.accountId + "%n " + this.owner + "%n IFSC Code: " + this.IFSCcode);
    }

    public static void main(String[] args){

        Account account = new Account(1234455533,new Owner("Rishi", "Chitransh", 34),"ICIC09");
        System.out.println(account);

        //Deposit money into Account
        try {
            account.depositMoney();
        } catch (NoBankAccountExistsException e) {
            System.out.println(e);
            System.exit(0);
        }

        //Withdraw money from Account
        try {
            account.withdrawMoney();
        } catch (InsufficientFundsException | WithdrawLimitExceeded e) {
            System.out.println(e);
        }

        //Print Transaction history
        List<String> operations = account.getTransactionHistory();
        for(String operation : operations){
            System.out.println(operation);
        }
    }

}
