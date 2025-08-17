import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    private Map<String, Double> exchangeRates;

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.92);
        exchangeRates.put("GBP", 0.79);
        exchangeRates.put("JPY", 156.0);
        exchangeRates.put("INR", 83.5);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String baseCurrency, targetCurrency;
        double amountToConvert;

        System.out.println("Welcome to the Currency Converter!");
        System.out.println("Available currencies: USD, EUR, GBP, JPY, INR\n");

        while (true) {
            System.out.print("Enter base currency (e.g., USD): ");
            baseCurrency = scanner.next().toUpperCase();
            while (!exchangeRates.containsKey(baseCurrency)) {
                System.out.print("Invalid currency. Choose from USD, EUR, GBP, JPY, INR: ");
                baseCurrency = scanner.next().toUpperCase();
            }

            System.out.print("Enter target currency (e.g., EUR): ");
            targetCurrency = scanner.next().toUpperCase();
            while (!exchangeRates.containsKey(targetCurrency)) {
                System.out.print("Invalid currency. Choose from USD, EUR, GBP, JPY, INR: ");
                targetCurrency = scanner.next().toUpperCase();
            }

            System.out.print("Enter amount to convert: ");
            while (!scanner.hasNextDouble() || (amountToConvert = scanner.nextDouble()) <= 0) {
                System.out.print("Invalid amount. Enter a positive number: ");
                scanner.next(); // Consume invalid input if not a double
            }

            double amountInUsd = amountToConvert / exchangeRates.get(baseCurrency);
            double convertedAmount = amountInUsd * exchangeRates.get(targetCurrency);

            System.out.printf("Result: %.2f %s = %.2f %s\n", amountToConvert, baseCurrency, convertedAmount, targetCurrency);

            System.out.print("\nConvert another amount? (yes/no): ");
            if (!scanner.next().toLowerCase().equals("yes")) {
                break; // Exit loop if not 'yes'
            }
        }

        System.out.println("Thank you for using the Currency Converter. Goodbye!");
        scanner.close();
    }

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        converter.run();
    }
}
