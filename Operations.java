import exceptions.*;
import model.*;

import java.io.IOException;
import java.util.InputMismatchException;

import java.util.List;
import java.util.Scanner;

public class Operations {
  private Bank bank;

  public Operations() {
    bank = new Bank();
  }

  private void showOperations() {
    System.out.println("Choose an option: ");
    System.out.println("1: Create a bank account.");
    System.out.println("2: Show history.");
    System.out.println("3: Deposit money.");
    System.out.println("4: Withdraw money");
    System.out.println("5: Check Balance");
    System.out.println("6: Exit");
    /*System.out.println("6: Transfer money.");
    System.out.println("7: Calculate amount"); */
  }
  public void start() {
    int option;
    Scanner reader = new Scanner(System.in);

    do {
      showOperations();

      try{
        option = reader.nextInt();
        reader.nextLine();

        if(option < 1 || option > 5) {
           break;
        }

        switch(option) {
          case 1:
              createBankAccount(reader);
              break;
          case 2:
              showHistory(reader);
              break;
          case 3:
              addMoney(reader);
              break;
          case 4:
              withdrawMoney(reader);
              break;
          case 5:
              checkBalance(reader);
              break;
          /*case 6:
              transferMoney(reader);
              break;
          case 7:
              calculateAmount(reader);
              break;
          */
        }
      } catch(InputMismatchException e) {
          System.err.println("Invalid argument. Try again.");
          reader.next();
      } catch(Exception e) {
          System.err.println(e.getMessage());
      }
    } while(true);

    reader.close();
  }

  private Owner readOwner(Scanner reader){
        System.out.println("Enter owner's name: ");
        String name = reader.nextLine();
        String names[] = name.split("\\s");

        System.out.println("Enter owner's age: ");
        int age = reader.nextInt();
        return new Owner(names[0],names[1],age);
    }

  public void createBankAccount(Scanner reader) {
        Owner owner = readOwner(reader);

        System.out.println("Enter account ID: ");
        int accId = reader.nextInt();
        reader.nextLine();

        System.out.println("Enter IFSC Code: ");
        String ifsc = reader.nextLine();

        bank.createBankAccount(accId,owner,ifsc);
    }

  public void checkBalance(Scanner reader) {
    System.out.println("Enter Account ID to view balance: ");
    int accId = reader.nextInt();

    bank.balance(accId);
  }

  public void showHistory(Scanner reader) {
      System.out.println("Enter Account ID to view history: ");
      int accId = reader.nextInt();

      bank.history(accId);
   }

  public void addMoney(Scanner reader) {
      System.out.println("Enter Account ID to deposit money: ");
      int accId = reader.nextInt();

      bank.deposit(accId);
  }

  public void withdrawMoney(Scanner reader) {
      System.out.println("Enter Account ID to withdraw money: ");
      int accId = reader.nextInt();

      bank.withdraw(accId);
  }

  public static void main(String [] args) {

    Operations ops = new Operations();

    ops.start();
  }
}
