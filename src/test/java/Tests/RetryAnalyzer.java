package Tests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        // Максимальное число попыток
        int maxRetryCount = 3;
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Повторный запуск теста: " + result.getName() +
                    ", попытка: " + retryCount);
            return true; // Запустить тест ещё раз
        }
        return false; // Больше не запускать
    }
}
