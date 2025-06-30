import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountHolderName;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account opened with balance: $" + initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        transactionHistory.add("Deposited: $" + amount + ", New Balance: $" + balance);
        System.out.println("Deposit successful. New Balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds. Available Balance: $" + balance);
            return;
        }
        balance -= amount;
        transactionHistory.add("Withdrew: $" + amount + ", New Balance: $" + balance);
        System.out.println("Withdrawal successful. New Balance: $" + balance);
    }

    public void viewTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (String t : transactionHistory) {
            System.out.println(t);
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

public class BankAccountSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Account Number: ");
        String accNumber = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();

        Account account = new Account(name, accNumber, initialBalance);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Bank Account Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;

                case 4:
                    account.viewTransactionHistory();
                    break;

                case 5:
                    System.out.println("Thank you for using our bank simulation. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please select from 1 to 5.");
            }
        }

        scanner.close();
    }
}