package BasicExceptionHandling;



public abstract class  Account {

	protected String accountNumber;
	protected String name;
	protected int balance;
	
	public String getName() {
		return name;
	}



	public Account(String name, int balance) {
	this.accountNumber=generateAccountNumber();
		
		this.name = name;
		this.balance = balance;
	}
	
	
	
	public String generateAccountNumber() {
		StringBuilder sb = new StringBuilder("IDBI1000");

		String choice = "1234567890";

		for (int i = 0; i < 7; i++) {

			int randomIndex = (int) (Math.random() * 10);

			//System.out.println(randomIndex);
			sb.append(choice.charAt(randomIndex));

		}

		String accountNumber = sb.toString();

		return accountNumber;
	}
	
	public abstract void debit(int ammount);
	
	
	public abstract void credit(int ammount);
	
	public abstract void display();
	
	
	
	
	
}

