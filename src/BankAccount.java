import exceptions.InsufficientFundsException;
import java.text.DecimalFormat;


public class BankAccount {

    //Global Variables declared
    private final int accountID;
    private double currentBalance;

    //Constructor for instance variables
    public BankAccount(int accountID, double currentBalance) {

        this.accountID = accountID;
        this.currentBalance = currentBalance;
    }

    //This method returns the instance variable for accountID, no inputs
    public int getAccountID(){ return this.accountID; }

    //This method returns the instance variable for currentBalance, no inputs
    public double getCurrentBalance(){
        return this.currentBalance;
    }

    //This method takes an input for deposit amount, checks for Exceptions, if not thrown, the amount is added to the currentBalance.
    // No return statement.
    public void deposit(double depositAmount) throws IllegalArgumentException {

        if (depositAmount <= 0){
            throw new IllegalArgumentException("Deposit amount must be greater than 0.00!");
        }
        else {
            this.currentBalance += depositAmount;
        }
    }

    //This method has no returns.
    //  It takes an input of withdraw amount, checks for Exceptions, if not thrown, the amount is taken from the currentBalance.
    public void withdraw(double withdrawAmount) throws IllegalArgumentException, InsufficientFundsException {

        if (withdrawAmount <= 0){
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.00!");
        }
        else if (currentBalance - withdrawAmount < 0 ){
            throw new InsufficientFundsException(this.currentBalance, withdrawAmount);
        }
        else{
            currentBalance -= withdrawAmount;
        }
    }


    //This method doesn't take in any inputs, returns a String value and formats the accountID with String.format()
    public String getFormattedAccountNumber() { return String.format("%08d", accountID); } // String format for AccountID


    //This method returns a string value, doesn't take any inputs, and formats the currentBalance with DecimalFormat
    public String getFormattedCurrentBalance(){
        DecimalFormat df = new DecimalFormat("£#,##0.00"); //Decimal format for currentBalance

        return df.format(currentBalance);
    }

    //This method simply returns a string with method calls to the previous methods, in a correctly formatted way to display
    // the account number and balance.
    @Override
    public String toString() { return "A/C No: " + getFormattedAccountNumber() + ", Balance: " + getFormattedCurrentBalance(); }






    /* =======================================
        DO NOT EDIT OR REMOVE THE BELOW CODE!
    ======================================= */

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BankAccount){
            return (((BankAccount) obj).accountID == accountID) && ((BankAccount) obj).currentBalance == currentBalance;
        }else{
            return super.equals(obj);
        }
    }
}
