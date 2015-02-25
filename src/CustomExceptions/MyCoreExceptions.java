package CustomExceptions;

public class MyCoreExceptions extends Exception{
  
	private String message = null;
    
    public MyCoreExceptions() {
        super();
    }
 
    public MyCoreExceptions(String message) {
        super(message);
        this.message = message;
    }
 
    public MyCoreExceptions(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
}
