# Banking-Solution-TW
Banking solution to simulate (Deposit - Withdraw - Balance check)

**Pre-Requisite**: Java 1.8 (JDK, JRE)

## Structure:

### Operations.java:

Is the entry point for the application. On running it will present a list of choices on console. Choose the applicable one to perform the operation

### Model:

Folder contains the actors/data objects of the app:

  1.) **Bank** - Simulates a list of accounts (see below) and the related operations.
  
  2.) **Owner** - Simulates the person/actor accessing/owning the account.
  
  3.) **Account** - Simulates the account and atomic operations on the account.
  
### Exceptions:

Folder contains the custom exception used in the application:

  1.) **InsufficientFundsException** - Fired when funds are insufficient, while withdrawing.
  
  2.) **NoBankAccountExistsException** - Fired if the account with the given ID doesn't exist.
  
  3.) **WithdrawLimitExceeded** - Fired in case the daily withdrawal limit is exceeded.
  
### NOTES:
  
  This is a thread-bare application to display the basic operations. Modifications/Improvements may include:
  
  1. Generation of ID randomly (UUID etc)
  
  2. Applying SI/CI operations.
  
  3. Adding varieties of account. Cureently its a simple account, we can eventually categorize between Savings, current etc.
