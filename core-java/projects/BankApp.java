import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private int accountNo;
    private String holderName;
    private int pin;
    private int balance;
    private boolean locked;

    public Account(int accountNo, String holderName, int pin, int balance) {
        this.accountNo = accountNo;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = balance;
        this.locked = false;
    }
    public int getPin() {
        return pin;
    }

    public boolean isLocked() {
        return locked;
    }

    public void lockAccount() {
        locked = true;
    }

    public void changePin(int oldPin, int newPin) {
        if (oldPin == pin) {
            pin = newPin;
            System.out.println("✅ PIN changed successfully.");
        } else {
            System.out.println("❌ Old PIN incorrect.");
        }
    }

    public void showBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposit successful.");
        } else {
            System.out.println("❌ Invalid amount.");
        }
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid amount.");
        } else if (amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawal successful.");
        } else {
            System.out.println("❌ Insufficient balance.");
        }
    }

    public void display() {
        System.out.println("\n📄 --- Account Details ---");
        System.out.println("Account No   : " + accountNo);
        System.out.println("Holder Name : " + holderName);
        System.out.println("Balance     : " + balance);
    }
}

public class BankApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Collection to store accounts
        Map<Integer, Account> accounts = new HashMap<>();
        accounts.put(12345, new Account(12345, "Amit Singh", 9919, 5000));
        accounts.put(12349, new Account(12349, "Manoj Verma", 1973, 5000));
        accounts.put(12347, new Account(12347, "Imran Ahmad", 8810, 5000));

        while (true) {
            System.out.println("\n🏦 ===== JAVA BANK SYSTEM ===== 🏦");
            System.out.print("🔢 Enter Account Number: ");
            int accNo = sc.nextInt();

            if (!accounts.containsKey(accNo)) {
                System.out.println("❌ Account does not exist.");
                continue;
            }

            Account account = accounts.get(accNo);

            if (account.isLocked()) {
                System.out.println("🚫 Account is locked. Contact bank.");
                continue;
            }

            // PIN authentication
            int attempts = 3;
            while (attempts > 0) {
                System.out.print("🔐 Enter PIN: ");
                int pin = sc.nextInt();

                if (pin == account.getPin()) {
                    System.out.println("✅ Login successful!");
                    break;
                } else {
                    attempts--;
                    System.out.println("❌ Wrong PIN. Attempts left: " + attempts);
                }
            }

            if (attempts == 0) {
                account.lockAccount();
                System.out.println("🚫 Account locked due to multiple wrong attempts.");
                continue;
            }

            // Account menu
            while (true) {
                System.out.println("\n📌 --- ACCOUNT MENU ---");
                System.out.println("1. View Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Display Account");
                System.out.println("5. Change PIN");
                System.out.println("6. Logout");
                System.out.print("👉 Enter choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        account.showBalance();
                        break;

                    case 2:
                        System.out.print("💵 Enter amount: ");
                        account.deposit(sc.nextInt());
                        break;

                    case 3:
                        System.out.print("💸 Enter amount: ");
                        account.withdraw(sc.nextInt());
                        break;

                    case 4:
                        account.display();
                        break;

                    case 5:
                        System.out.print("🔐 Enter old PIN: ");
                        int oldPin = sc.nextInt();
                        System.out.print("🆕 Enter new PIN: ");
                        int newPin = sc.nextInt();
                        account.changePin(oldPin, newPin);
                        break;

                    case 6:
                        System.out.println("👋 Logged out successfully.");
                        break;

                    default:
                        System.out.println("❌ Invalid option.");
                }

                if (choice == 6)
                    break;
            }
        }
    }
}
