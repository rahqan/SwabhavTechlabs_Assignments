package BasicExceptionHandling;

public class NegativeAmmountException extends RuntimeException{

	public String getMessage() {
		return "Ammount cant be negative";
	}	
	
}
