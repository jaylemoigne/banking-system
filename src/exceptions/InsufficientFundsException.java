package exceptions;

public class InsufficientFundsException extends RuntimeException {

//Custom Exception that's thrown when there are insufficient funds for a transfer
    public InsufficientFundsException(double currentBalance, double withdrawAmount) {
        super("Insufficient funds for this withdrawal. (Available Balance:" + currentBalance + ", Amount Requested:" + withdrawAmount + ")");
    }


}
