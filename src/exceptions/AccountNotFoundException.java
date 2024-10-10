package exceptions;

public class AccountNotFoundException extends RuntimeException {

    //Custom Exception that is thrown when the account ID cannot be found
    public AccountNotFoundException(int accountID) {
        super("Account not found with accountID: " + accountID);

    }


}
