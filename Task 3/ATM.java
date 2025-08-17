import java.util.Scanner;

public class ATM {

    private BankAccount userAccount;

    public ATM(BankAccount account) {
        this.userAccount = account;
    }

    public void displayMenu() {
        System.out.println("\n--- Welcome to Your ATM ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Please choose an option: ");
    }

    public void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", userAccount.getBalance());
    }

    public void deposit(double amount) {
        if (amount > 0) {
            userAccount.deposit(amount);
            System.out.printf("Successfully deposited $%.2f. New balance: $%.2f\n", amount, userAccount.getBalance());
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (userAccount.getBalance() >= amount) {
            userAccount.withdraw(amount);
            System.out.printf("Successfully withdrew $%.2f. New balance: $%.2f\n", amount, userAccount.getBalance());
        } else {
            System.out.println("Insufficient balance. Your current balance is: $" + userAccount.getBalance());
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number from the menu.");
                scanner.next();
                displayMenu();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = -1;
                    while (true) {
                        if (scanner.hasNextDouble()) {
                            depositAmount = scanner.nextDouble();
                            if (depositAmount > 0) {
                                break;
                            } else {
                                System.out.println("Amount must be positive. Enter amount to deposit: $");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number. Enter amount to deposit: $");
                            scanner.next();
                        }
                    }
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = -1;
                     while (true) {
                        if (scanner.hasNextDouble()) {
                            withdrawAmount = scanner.nextDouble();
                            if (withdrawAmount > 0) {
                                break;
                            } else {
                                System.out.println("Amount must be positive. Enter amount to withdraw: $");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number. Enter amount to withdraw: $");
                            scanner.next();
                        }
                    }
                    withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(500.00);
        ATM myATM = new ATM(myAccount);
        myATM.run();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
