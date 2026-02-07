import java.util.Scanner;

public class ATMMachine 
{
	static Account[] accounts = {
			new Account(12345, 8080, "Rakesh", 8000),
			new Account(12347, 9090, "Manoj", 5000),
			new Account(12349, 1010, "Imran", 6000),
	};
	public static Account getAccount(int accountNo)
	{
		for(Account account: accounts)
		{
			if(account.getAccountNo() == accountNo)
				return account;
		}
		return null;
	}
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("\n🏦 =====  BANK ATM SYSTEM ===== 🏦");
			System.out.print("🔢 Enter Account Number: ");
			int accNo = sc.nextInt();
			Account account = getAccount(accNo);
			if(account == null)
			{
				System.out.println("❌ Account does not exist.");
				continue;
			}
			if(account.isLocked())
			{
				System.out.println("🚫 This account is locked. Contact bank!");
				continue;
			}
			int attemps = 3;
			while(attemps > 0)
			{
				System.out.print("🔐 Enter PIN: ");
				int pinNo = sc.nextInt();
				if(pinNo == account.getPin())
				{
					System.out.println("\n✅ Login successful!");
					break;
				}
				attemps --;
				System.out.println("\n❌ Wrong PIN. Attempts left: "+attemps);
			}
			if(attemps == 0)
			{
				account.setLocked(true);
				System.out.println("🚫 Account locked due to multiple wrong attempts.");
				continue;
			}
			//ATM Menu
			while(true)
			{
				System.out.println("\n📌 --- ACCOUNT MENU ---");
				System.out.println("1. View Blanace");
				System.out.println("2. Deposit Money");
				System.out.println("3. Withdraw Money");
				System.out.println("4. Logout");
				System.out.print("👉 Enter your choice:");
				int choice = sc.nextInt();
				if(choice == 1)
					account.showBalance();
				else if(choice == 2)
				{
					System.out.print("💵 Enter amount to deposit:");
					int amt = sc.nextInt();
					account.deposit(amt);
				}
				else if(choice == 3)
				{
					System.out.print("💸 Enter amount to withdraw:");
					int amt = sc.nextInt();
					account.withdraw(amt);
				}
				else if(choice == 4)
				{
					System.out.println("\n👋 Logged out successfully.");
					break;
				}
				else
				{
					System.out.println("\n❌ Wrong choice. Try again!");
				}
			}
				
		}
	}
}
