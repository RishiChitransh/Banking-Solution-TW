package exceptions;

public class WithdrawLimitExceeded extends Exception {
    public WithdrawLimitExceeded(){
        super();
    }
    public WithdrawLimitExceeded(String message){
        super(message);

    }
}
