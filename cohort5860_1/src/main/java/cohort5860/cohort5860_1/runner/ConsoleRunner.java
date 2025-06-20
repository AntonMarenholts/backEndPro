package cohort5860.cohort5860_1.runner;

import cohort5860.cohort5860_1.model.FixerApiResponse;
import cohort5860.service.CurrencyConverterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component // <-- Добавьте эту аннотацию!
public class ConsoleRunner implements CommandLineRunner {
    private final CurrencyConverterService converterService;

    public ConsoleRunner(CurrencyConverterService converterService) {
        this.converterService = converterService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Добро пожаловать в консольный конвертер валют! ---");

        Optional<FixerApiResponse> apiResponseOptional = converterService.getLatestRates();

        if (apiResponseOptional.isEmpty()) {
            System.err.println("Не удалось загрузить курсы валют. Пожалуйста, проверьте ваше интернет-соединение и API ключ Fixer API.");
            scanner.close();
            return;
        }

        FixerApiResponse response = apiResponseOptional.get();


        String baseCurrency = response.getBase();
        System.out.println("Курсы валют обновлены. Базовая валюта Fixer API: " + baseCurrency);
        System.out.println("Доступные валюты (неполный список): " + response.getRates().keySet().stream().limit(10).toList() + "..."); // Показываем часть доступных валют

        try {
            System.out.print("Введите исходную валюту (например, USD): ");
            String fromCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Введите целевую валюту (например, JPY): ");
            String toCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Введите сумму для конвертации: ");
            double amount = scanner.nextDouble();

            double convertedAmount = converterService.convert(fromCurrency, toCurrency, amount, response.getRates(), baseCurrency);

            System.out.printf("--- Результат конвертации ---%n");
            System.out.printf("%.2f %s = %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);

        } catch (java.util.InputMismatchException e) {
            System.err.println("Ошибка ввода: Пожалуйста, введите числовое значение для суммы.");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
            System.err.println("Убедитесь, что введенные коды валют корректны (например, USD, EUR, JPY).");
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Конвертер завершил работу.");
        }
    }
}

