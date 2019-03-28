package exceptions;

public class NoBankAccountExistsException extends Exception {
    public NoBankAccountExistsException(){
        super();
    }
    public NoBankAccountExistsException(String message){
        super(message);
    }
}
