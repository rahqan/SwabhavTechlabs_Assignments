package BasicExceptionHandling;


public class SavingsAccount extends Account {

	public static int minimumBalance=1000;

	public SavingsAccount( String name, int balance) {
		super( name, balance);
		
	}
	@Override
    public void credit(int amount) {
		
        balance += amount;
        System.out.println("Credited " + amount + " to Savings Account.");
    }
	@Override
    public void debit(int amount) {
		try {
        if ((balance - amount) >= minimumBalance) {
            balance -= amount;
            System.out.println("Debited " + amount + " from Savings Account.");
        } else {
            //System.out.println("Cannot debit. Minimum balance " + minimumBalance + " must be maintained.");
            throw new MinimumBalanceException();
            
        }
		}catch(MinimumBalanceException exception) {
			
			System.out.println(exception.getMessage());
		}
    }
	@Override
    public void display() {
        System.out.println("Savings Account [Acc No: " + accountNumber + ", Name: " +name + ", Balance: " + balance + "]");
    }
    
    
	
	
}

