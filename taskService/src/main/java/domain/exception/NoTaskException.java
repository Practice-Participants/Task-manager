package domain.exception;

/**
 * Created by Lorden on 21.02.2025
 */
public class NoTaskException extends RuntimeException{
    public NoTaskException(){
        super("No issues with this id were found.");
    }
}
