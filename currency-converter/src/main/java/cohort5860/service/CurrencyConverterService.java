package cohort5860.service;

import cohort5860.model.FixerApiResponse; // Обратите внимание: ваш пакет, скорее всего, cohort5860.model.FixerApiResponse
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.Optional;

@Service
public class CurrencyConverterService {

    @Value("${fixer.api.key}")
    private String apiKey;

    @Value("${fixer.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public CurrencyConverterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Получает актуальные курсы валют от Fixer API.
     * @return Объект Optional<FixerApiResponse>, содержащий курсы валют, если запрос успешен.
     */
    public Optional<FixerApiResponse> getLatestRates() {
        String url = String.format("%s?access_key=%s", apiUrl, apiKey);
        try {
            FixerApiResponse response = restTemplate.getForObject(url, FixerApiResponse.class);
            // Упрощенная проверка: возвращаем Optional.of(response) только если success=true и rates не null
            if (response != null && response.isSuccess() && response.getRates() != null) {
                return Optional.of(response);
            } else {
                // Если API вернул неуспешный ответ (success=false) или rates пуст
                System.err.println("Fixer API вернул неуспешный ответ или отсутствуют данные о курсах.");
                return Optional.empty();
            }
        } catch (Exception e) {
            System.err.println("Ошибка при получении курсов валют: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Конвертирует сумму из одной валюты в другую, используя предоставленные курсы.
     * @param fromCurrency Исходная валюта (например, "USD").
     * @param toCurrency Целевая валюта (например, "JPY").
     * @param amount Сумма для конвертации.
     * @param rates Карта валютных курсов.
     * @param baseCurrency Базовая валюта, относительно которой представлены курсы в Fixer API (обычно EUR).
     * @return Сконвертированная сумма.
     * @throws IllegalArgumentException если валюты не найдены в курсах.
     */
    public double convert(String fromCurrency, String toCurrency, double amount,
                          Map<String, Double> rates, String baseCurrency) {

        if (!rates.containsKey(fromCurrency)) {
            throw new IllegalArgumentException("Исходная валюта '" + fromCurrency + "' не найдена в доступных курсах.");
        }
        if (!rates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Целевая валюта '" + toCurrency + "' не найдена в доступных курсах.");
        }

        Double rateFromBase = rates.get(fromCurrency);
        Double rateToBase = rates.get(toCurrency);

        if (rateFromBase == null || rateToBase == null) {
            // Это маловероятно после проверок containsKey, но для безопасности
            throw new IllegalStateException("Внутренняя ошибка: не удалось получить курсы для конвертации.");
        }

        double amountInBase = amount / rateFromBase;
        double convertedAmount = amountInBase * rateToBase;

        return convertedAmount;
    }
}