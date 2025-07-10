package BasicExceptionHandling;

import java.util.Scanner;

public class AccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Name: ");
		String name = scanner.nextLine();

		System.out.println("Choose Account Type:");
		System.out.println("1. Current Account");
		System.out.println("2. Savings Account");
		int choice = scanner.nextInt();

		int balance;
		do {
			System.out.println("Enter Initial Balance:");
			balance = scanner.nextInt();

			if (choice == 1 && balance < 0) {
				System.out.println("For Current Account, balance must be non-negative.");
			} else if (choice == 2 && balance < 1000) {
				System.out.println("For Savings Account, balance must be more than 1000.");
			} else {
				break;
			}

		} while (true);

		Account account = null;

		if (choice == 1) {
			account = new CurrentAccount(name, balance);
		} else if (choice == 2) {
			account = new SavingsAccount(name, balance);
		} else {
			System.out.println("Invalid choice.");
			System.exit(0);
		}

		int option;
		do {
			System.out.println("\nMenu:");
			System.out.println("1. Credit");
			System.out.println("2. Debit");
			System.out.println("3. Display");
			System.out.println("4. Exit");
			option = scanner.nextInt();

			switch (option) {
			case 1:
				System.out.print("Enter amount to credit: ");
				int creditAmt = scanner.nextInt();
				try {
					
				try {

					if (creditAmt < 0) {
						throw new NegativeAmmountException();

					}
					
					
				} catch (NegativeAmmountException exception) {
					System.out.println(exception.getMessage());
					break;
				}
				
				account.credit(creditAmt);
				}
				
				
				catch(Exception exception) {
					System.out.println("Something went wrong");
					scanner.nextLine();
					
				}
				
				
				break;
			case 2:
				try {
					System.out.print("Enter amount to debit: ");
					int debitAmt = scanner.nextInt();
					try {

						if (debitAmt < 0) {
							throw new NegativeAmmountException();

						}
					} catch (NegativeAmmountException exception) {
						System.out.println(exception.getMessage());
						break;
					}
					
					
					account.debit(debitAmt);

				} catch (OverDraftLimitUsedException exception) {
					System.out.println(exception.getMessage());
				}
				catch(Exception exception) {
					System.out.println("Something went wrong");
					scanner.nextLine();
				}
				

				break;

			case 3:
				account.display();
				break;
			case 4:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid option.");
			}
		} while (option != 4);

	}

}
