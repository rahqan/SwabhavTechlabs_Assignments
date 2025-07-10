package BasicExceptionHandling;

public class MinimumBalanceException extends RuntimeException{
	
	public String getMessage() {
		return "Minimum balance: 1000 must be maintained";
	}
	

}
