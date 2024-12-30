package BMS.java;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


    class Account {
	private String accountNumber;
	private String accountHolderName;
	private double balance;
	
	public Account(String accountNumber, String accountHolderName, double initialBalance) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = initialBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	
	public double getBalance() {
		return balance;
	}

	public void deposite(double amount) {
		if(amount >0) {
			balance +=amount;
			System.out.println("Deposit successful.New balance:"+balance);
		}else {
			System.out.println("Invalid deposit amount.");
		}
	}
	
	public void withdraw(double amount) {
		if(amount >0 && amount <=balance) {
			balance -= amount;
			System.out.println("Withdrawal successful. New balance:"+ balance);
		}else {
			System.out.println("Insufficient balance or invalid amount.");
		}
	}
	
	public void displayDetails() {
		System.out.println("Account Number:"+ accountNumber);
		System.out.println("Account Holder Name:"+ accountHolderName);
		System.out.println("Balance:" +balance);
	}
}

public class BankMangSystem{

private static HashMap<String, Account> accounts=new HashMap<>();

public static void createAccount(Scanner scanner) {
	System.out.println("Enter Account Number:");
	String accountNumber = scanner.next();
	if (accounts.containsKey(accountNumber)){
		System.out.println("Account already exists.");
		return;
	}
	
	System.out.println("Enter Account Holder Name:");
	String accountHolderName = scanner.nextLine();
	
	double initialBalance=0;
	boolean validInput=false;
	
	while(!validInput) {
		System.out.println("Enter Initial Balance:");
		try {
   initialBalance = scanner.nextDouble();
   scanner.nextLine();
   
   if(initialBalance<0) {
	   System.out.println("Balance cannot be negative.please enter a valid amount.");
   }else {
	   validInput =true;
   }
   
 }catch(InputMismatchException e) {
	 System.out.println("Invalid input.please enter a numeric value.");
	 scanner.nextLine();
 }
	}
   Account account =new Account(accountNumber, accountHolderName,initialBalance);
accounts.put(accountNumber, account);
   System.out.println("Account created successfully.");
}

public static void deposit(Scanner scanner) {
	System.out.print("Enter Account Number:");
	String accountNumber= scanner.nextLine();
	Account account =accounts.get(accountNumber);
	if(account !=null) {
		System.out.println("Enter Amount number:");
		try {
        double amount= scanner.nextDouble();
        scanner.nextLine();
        account.deposite(amount);
        
		}catch(InputMismatchException e) {
			System.out.println("Invalid input.Please enter a numberic value.");
			scanner.nextLine();
		}
	}else {
		System.out.println("Account not found.");
	}
}

public static void withdraw(Scanner scanner) {
	System.out.println("Enter Account Number:");
	String accountNumber =scanner.nextLine();
	Account account = accounts.get(accountNumber);
	if(account !=null) {
		System.out.print("Enter Amount to Withdraw:");
		try {
	   double amount =scanner.nextDouble();
	   scanner.nextLine();
	   account.withdraw(amount);
		}catch(InputMismatchException e) {
			System.out.println("Invalid input.Please enter a numberic value.");
			scanner.nextLine();
}
	}else {
	System.out.println("Account not found.");
}
}

public static void displayAccountDetails(Scanner scanner) {
	System.out.println("Enter Account Number:");
	String accountNumber=scanner.nextLine();
	Account account =accounts.get(accountNumber);
	if(account !=null) {
		account.displayDetails();
	}else {
		System.out.println("Account not found.");
	}
	
}
public static void main(String[] args) {
	Scanner scanner =new Scanner(System.in);
	int choice;
	
	do {
		System.out.println("\n--Bank Management System--");
		System.out.println("1. create Account");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Display Account Details");
		System.out.println("5. Exit");
		System.out.println("Enter your choice:");
		
		choice=scanner.nextInt();
		scanner.nextLine();
		
	switch (choice) {
	case 1: 
		createAccount(scanner);
		break;
		
	case 2:
		deposit(scanner);
		break;
		
	case 3: 
		withdraw(scanner);
		break;
		
	case 4:
		displayAccountDetails(scanner);
		break;
		
	case 5: 
		System.out.println("Exiting the system. Thank you!");
		break;
	default:
		System.out.println("Invalid choice. Please try again.");
		
	}
	}while (choice !=5);
	scanner.close();


	}

}
  


