public class Account 
{
	private int accountNo ;
	private int pin ;
	private String holder;
	private int amount;
	private boolean locked = false;
	
	public Account(int accountNo, int pin, String holder, int amount)
	{
		this.accountNo =accountNo;
		this.pin = pin;
		this.holder = holder;
		this.amount = amount;
	}
	
	public void showBalance()
	{
		System.out.println("\nCurrent balance is: ₹"+amount);
	}
	public  void deposit(int amt)
	{
		if(amt <=0)
			System.out.println("\n❌ Invalid amount!");
		else
		{
			amount += amt;
			System.out.println("\n✅ Deposit successful!");
		}
	}
	public void withdraw(int amt)
	{
		if(amt <=0)
			System.out.println("\n❌ Invalid amount!");
		else if(amt <= amount)
		{
			amount -= amt;
			System.out.println("\n✅Withdraw successful!");
		}
		else
			System.out.println("\n❌ Insufficient anount!");
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
}
