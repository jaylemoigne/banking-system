import java.text.DecimalFormat;

public class SavingsAccount extends BankAccount {

    //Global variable declared
    private double interestRate;

    //Constructor for instance variables
    public SavingsAccount(int accountID, double currentBalance, double interestRate) {
        super(accountID, currentBalance);
        this.interestRate = interestRate;
    }

    //This method returns the instance variable for interestRate.
    public double getInterestRate(){
        return this.interestRate;
    }

    //This method returns a String which is formatted to be a percentage.
    public String getFormattedInterestRate() { return String.format("%#.1f%%", interestRate * 100); }


    //This method doesn't return any values, it takes an input of a double, and throws an IllegalArgumentException
    // This method is intended to check the interest rate to be sure its above 0.0 and below 1.0, if so it stores the set interest rate in the instance variable.
    public void setInterestRate(double newInterestRate) throws IllegalArgumentException {

        if (newInterestRate <= 0.0){
            throw new IllegalArgumentException("Interest Rate cannot be less than 0%!");
        }
        else if (newInterestRate > 1.0){
            throw new IllegalArgumentException("Interest Rate cannot be greater than 100%!");
        }
        else{
            this.interestRate = newInterestRate;
        }
    }

    //This method returns a String value, using DecimalFormat it formats an annual return using the currentBalance and interestRate
    public String getEstimatedAnnualReturn(){

        DecimalFormat df = new DecimalFormat("£#,##0.00"); //Decimal Format
        double annualReturn = getCurrentBalance() * interestRate;

        return df.format(annualReturn);
    }


    //This method returns a String value, similar to the last method it formats the annual return, then divides by 12 and returns the value
    public String getEstimatedMonthlyReturn(){

        DecimalFormat df = new DecimalFormat("£#,##0.00"); //Decimal Format
        double monthlyReturn = (getCurrentBalance() * interestRate) / 12; //Divided to determine monthly return

        return df.format(monthlyReturn);
    }

    //This method returns a String value, it returns a formatted value. It calls back to previous methods.
    // It gets the formatted versions of account number, current balance and interest rate.
    @Override
    public String toString() {
        return "A/C No: " + getFormattedAccountNumber() + ", Balance: " + getFormattedCurrentBalance() + ", Interest Rate: " + getFormattedInterestRate();
    }


}
