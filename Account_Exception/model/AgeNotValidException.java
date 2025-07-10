package BasicExceptionHandling;

public class AgeNotValidException extends Exception {
    
	private int age;
	
	public AgeNotValidException(int age) {
		super();
		this.age = age;
	}
	

	public String getMessage() {
		return "Age must be greater than 18, You Entered "+age;
	}
	
}
