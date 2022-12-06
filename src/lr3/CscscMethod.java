package lr3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CscscMethod {
    public static void main(String[] args) {
        var planTime = readPositiveDecimal("Введите плановый срок проекта: ");
        var planBudget = readPositiveDecimal("Введите плановый бюджет проекта: ");
        var actualBudget = readPositiveDecimal("Введите расход бюджета на момент проверки: ");
        var progress = readPositiveDecimal("Введите прогресс выполнения проекта (в процентах): ");
        var actualTime = readPositiveDecimal("Введите время проверки (относительно времени старта проекта): ");

        var totalTime = actualTime.divide(progress.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP), 4, RoundingMode.HALF_UP);
        var totalBudget = actualBudget.divide(actualTime, 4, RoundingMode.HALF_UP).multiply(totalTime);

        System.out.println("Ожидаемое время выполнения проекта: " + totalTime + "");

        if (planTime.compareTo(totalTime) > 0) {
            System.out.println("Проект будет готов раньше срока на " + planTime.subtract(totalTime));
        } else if (planTime.compareTo(totalTime) < 0) {
            System.out.println("Сроки будут превышены на " + totalTime.subtract(planTime));
        } else {
            System.out.println("Сроки проекта будут соблюдены");
        }

        System.out.println("Ожидаемый бюджет: " + totalBudget);

        if (planBudget.compareTo(totalBudget) > 0) {
            System.out.println("Бюджет будет сэкономлен на " + planBudget.subtract(totalBudget));
        } else if (planBudget.compareTo(totalBudget) < 0) {
            System.out.println("Бюджет будет превышен на " + totalBudget.subtract(planBudget));
        } else {
            System.out.println("Бюджет проекта будет соблюден");
        }
    }

    private static BigDecimal readPositiveDecimal(String prompt) {
        var in = new Scanner(System.in);
        BigDecimal result;
        while (true) {
            System.out.println(prompt);
            result = in.nextBigDecimal();

            if (result.compareTo(BigDecimal.ZERO) < 1) {
                System.out.println("Введите положительное число");
                continue;
            }

            break;
        }

        return result;
    }
}
