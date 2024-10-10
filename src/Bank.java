import exceptions.AccountNotFoundException;
import exceptions.InsufficientFundsException;

import java.io.*;
import java.util.*;

public class Bank {

    //Global variable declared to store accounts in an ArrayList
    private ArrayList<BankAccount> accounts;

    //In this method there is no return statement, it takes in an input of a String, in this case a filename
    // It throws FileNotFoundException
    // The method reads the file and splits the values, storing them in appropriate variables, then storing to a new account
    public void readAccounts(String accountFile) throws FileNotFoundException {

        this.accounts = new ArrayList<>();
        File accountFileName = new File(accountFile);
        Scanner readAccounts = new Scanner(accountFileName); //Starts scanner

        while (readAccounts.hasNextLine()) {
            String line = readAccounts.nextLine();
            String[] accountData = line.split(","); //splits line by "," to differentiate value

            //Bank Account
            if (accountData.length == 2) { //detects if there are two values on the line
                int accountID = Integer.parseInt(accountData[0].trim()); //stores value based on index into variable
                double currentBalance = Double.parseDouble(accountData[1].trim().replace("£", ""));
                BankAccount bankAccount = new BankAccount(accountID, currentBalance);
                this.accounts.add(bankAccount); // adds new bank account to ArrayList
            }
            //Savings Account
            else if (accountData.length == 3) { //detects if there are three values on the line
                int accountID = Integer.parseInt(accountData[0].trim()); //stores value based on index into variable
                double currentBalance = Double.parseDouble(accountData[1].trim().replace("£", ""));
                double interestRate = Double.parseDouble(accountData[2].trim().replace("%", ""));
                SavingsAccount savingsAccount = new SavingsAccount(accountID, currentBalance, interestRate);
                this.accounts.add(savingsAccount); // adds new savings account to ArrayList
            }
        }
        readAccounts.close();
    }

    //This method simply returns a List, in this case the accounts list. There is no inputs.
    public List<BankAccount> getAccounts() { return this.accounts; }

    //This method outputs the object BankAccount, it takes the input of an accountID. It throws AccountNotFoundException.
    // Using an advanced for loop and an if statement it iterates through the accounts and when the account matches it returns it.
    public BankAccount getAccount(int accountIdToLocate) throws AccountNotFoundException {

        for (BankAccount account : accounts) {
            if (account.getAccountID() == accountIdToLocate) {
                return account;
            }
        }
        throw new AccountNotFoundException(accountIdToLocate);
    }

    // The method returns an integer, taking in an integer as an input for finding the account index. It throws AccountNotFoundException
    public int getAccountIndex(int accountIdToLocateIndex) throws AccountNotFoundException {

        for (BankAccount account : accounts) {
            if (account.getAccountID() == accountIdToLocateIndex) {
                return accounts.indexOf(account);
            }
        }
        throw new AccountNotFoundException(accountIdToLocateIndex);
    }

    //This method returns an integer, and takes in no inputs.
    // It sets a default value for the next account ID and using an advanced for loop it iterates through comparing the id to each
    // Once it's found it stores the values into a variable and returns it
    public int getNextAccountID() {
        int nextAccountID = 1;

        for (BankAccount account : accounts) {
            int accountID = account.getAccountID();
            accountID++;
            nextAccountID = Math.max(nextAccountID, accountID);
        }
        return nextAccountID;
    }

    // This method returns an object called BankAccount, it has no inputs.
    // This method opens a BankAccount, it establishes a new one, with the starting balance as 0.00
    // It then adds it to the array list and returns the new account
    public BankAccount openBankAccount() {
        BankAccount newBankAccount = new BankAccount(getNextAccountID(), 0.00);
        accounts.add(newBankAccount); //adds new bank account
        return newBankAccount;
    }

    // This method returns an object called BankAccount, it has no inputs.
    // This method opens a BankAccount, it establishes a new one, with the starting balance as 0.00
    // It then adds it to the array list and returns the new account
    public BankAccount openSavingsAccount(double startingInterestRate) {

        SavingsAccount newSavingsAccount = new SavingsAccount(getNextAccountID(), 0.00, startingInterestRate);
        accounts.add(newSavingsAccount); //adds new savings account
        return newSavingsAccount;
    }

    // This method has no return statement, takes an input of the account id that needs to be closed, it throws the AccountNotFoundException
    public void closeAccount(int accountIdToClose) throws AccountNotFoundException {
        accounts.remove(getAccountIndex(accountIdToClose));
    }

    // This method has no return statement, it takes three inputs, being the payee & payer accountID and an amount to transfer.
    // It throws three Exceptions, being IllegalArgumentException, InsufficientFundsException and AccountNotFoundException.
    // It first checks the account exist, then using an if statement, it determines if the transfer amount is larger than zero.
    // If so it calls the withdraw and deposit methods, otherwise, it throws the IllegalArgumentException
    public void transferFunds(int payerAccountID, int payeeAccountID, double transferAmount) throws IllegalArgumentException, InsufficientFundsException, AccountNotFoundException {

        BankAccount payeeAccount = accounts.get(getAccountIndex(payeeAccountID));
        BankAccount payerAccount = accounts.get(getAccountIndex(payerAccountID));

        if (transferAmount > 0) {
            //withdraws the money from the payer account
            payerAccount.withdraw(transferAmount);
            //deposits into payee account
            payeeAccount.deposit(transferAmount);
        }
        else {
            throw new IllegalArgumentException("Transfer amount should be more than zero!");
        }
    }

    // The method has no return statement, it has an input of a String as a filename.
    // It throws the exception IOException
    // Using an advanced for loop it writes from the various accounts and prints it as a formatted version
    public void saveAccounts(String fileName) throws IOException {

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (BankAccount account : accounts) { //advanced for loop to iterate through accounts list to write to file
                if (account instanceof SavingsAccount) {
                    SavingsAccount savingsAccount = (SavingsAccount) account;
                    out.println(account.getAccountID() +
                            "," + account.getCurrentBalance() +
                            "," + savingsAccount.getInterestRate());
                }
                else {
                    out.println(account.getAccountID() +
                            "," + account.getCurrentBalance());
                }
            }

        }

    }
}