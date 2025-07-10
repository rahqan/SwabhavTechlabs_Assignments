package BasicExceptionHandling;



public class CurrentAccount extends Account {

	private int overDraftLimit = 50000;

	public CurrentAccount(String name, int balance) {
		super(name, balance);
	}

	@Override
	public void debit(int amount) {
		if(amount<0) {
			System.out.println("Cant be negative");
			return;
		}
		
		if (balance - amount >= -overDraftLimit) {
			balance -= amount;
			System.out.println("Debited " + amount + " from Current Account.");
		} else {
			throw new OverDraftLimitUsedException();
			
			
		}
	}

	@Override
	public void credit(int amount) {
		if(amount<0) {
			System.out.println("Cant be negative");
			return;
		}
		balance += amount;
		System.out.println("Credited " + amount + " to Current Account.");
	}

	@Override
	public void display() {
		int overdraftUsed = balance < 0 ? -balance : 0;
		int availableOverdraft = overDraftLimit - overdraftUsed;
		int displayBalance = balance < 0 ? 0 : balance;
		String name=this.name;

System.out.println("Current Account [Account No: " + accountNumber + ", Name: " + name + ", Balance: " 
+ displayBalance+ ", Overdraft Used: " + overdraftUsed + ", Remaining Overdraft: " + availableOverdraft + "]");

	}
}
