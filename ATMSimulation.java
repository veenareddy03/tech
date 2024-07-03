import java.util.ArrayList;
import java.util.Scanner;

class ATMMachine {
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    public ATMMachine(String pin) {
        this.balance = 0;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public void changePin(String oldPin, String newPin) {
        if (authenticate(oldPin)) {
            this.pin = newPin;
            System.out.println("PIN changed successfully");
        } else {
            System.out.println("Incorrect old PIN");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrew: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            System.out.println("Invalid withdrawal amount");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
        transactionHistory.add("Checked balance: $" + balance);
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Set your PIN: ");
        String pin = scanner.nextLine();

        ATMMachine atm = new ATMMachine(pin);

        while (true) {
            System.out.print("Enter your PIN: ");
            String inputPin = scanner.nextLine();
            if (!atm.authenticate(inputPin)) {
                System.out.println("Incorrect PIN. Try again.");
                continue;
            }

            while (true) {
                System.out.println("\nWelcome to the ATM");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Change PIN");
                System.out.println("5. Show Transaction History");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        atm.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        atm.withdraw(withdrawAmount);
                        break;
                    case 3:
                        atm.checkBalance();
                        break;
                    case 4:
                        System.out.print("Enter old PIN: ");
                        String oldPin = scanner.nextLine();
                        System.out.print("Enter new PIN: ");
                        String newPin = scanner.nextLine();
                        atm.changePin(oldPin, newPin);
                        break;
                    case 5:
                        atm.showTransactionHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}