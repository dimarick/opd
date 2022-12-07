package lr5;

import java.util.Scanner;

class Cost {
    private final int min;
    private final int max;
    private final int expected;

    public final int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getExpected() {
        return expected;
    }

    public Cost(int min, int max, int expected) {
        this.min = min;
        this.max = max;
        this.expected = expected;
    }
}

public class PertMethod {
    public static void main(String[] args) {
        final var kUi = readPositiveInt("количество пользовательских экранов: ");
        final var kAct = readPositiveInt("количество обработчиков событий: ");
        final var kBo = readPositiveInt("количество новых бизнес-объектов: ");
        final var kBm = readPositiveInt("количество новых или модифицируемых бизнес-методов: ");

        final var uiCost = new Cost(2, 20, 4);
        final var actCost = new Cost(4, 32, 8);
        final var boCost = new Cost(2, 8, 3);
        final var bmCost = new Cost(2, 26, 6);

        final var uiStdDev = stdDev(uiCost);
        final var actStdDev = stdDev(actCost);
        final var boStdDev = stdDev(boCost);
        final var bmStdDev = stdDev(bmCost);

        final var uiAvg = avgCost(uiCost);
        final var actAvg = avgCost(actCost);
        final var boAvg = avgCost(boCost);
        final var bmAvg = avgCost(bmCost);

        final var projectAvgCostHours = kUi * uiAvg + kAct * actAvg + kBo * boAvg + kBm * bmAvg;
        final var projectStdDev = Math.sqrt(kUi * uiStdDev * uiStdDev + kAct * actStdDev * actStdDev + kBo * boStdDev * boStdDev + kBm * bmStdDev * bmStdDev);
        final var secondSigmaProjectCost = projectAvgCostHours + 2 * projectStdDev;
        final var projectCostDeviation = projectStdDev / projectAvgCostHours;
        final var totalProjectCost = 4 * secondSigmaProjectCost;
        final var totalProjectCostMonths = totalProjectCost / 132;
        final var projectDuration = 2.5 * Math.pow(totalProjectCostMonths, 1.0 / 3);
        final var projectAvgTeamSize = totalProjectCostMonths / projectDuration;

        System.out.println("Средняя стоимость кодирования: " + "ui - " + formatDouble(uiAvg) + " чел*ч, событие - " + formatDouble(actAvg) + " чел*ч, бизнес-объектов - " + formatDouble(boAvg) + " чел*ч, бизнес-моделей - " + formatDouble(bmAvg) + "  чел*ч");
        System.out.println("Стандартное отклонение: " + "ui - " + formatDouble(uiStdDev) + " чел*ч, событие - " + formatDouble(actStdDev) + " чел*ч, бизнес-объектов - " + formatDouble(boStdDev) + " чел*ч, бизнес-моделей - " + formatDouble(bmStdDev) + "  чел*ч");
        System.out.println("Средняя полная стоимость кодирования проекта: " + formatDouble(projectAvgCostHours) + " чел*ч");
        System.out.println("Стандартное отклонение: " + formatDouble(projectStdDev) + " чел*ч");
        System.out.println("Стоимость кодирования проекта с вероятностью 95%: " + formatDouble(secondSigmaProjectCost) + " чел*ч");
        System.out.println("Отклонение стоимости проекта: " + formatPercent(projectCostDeviation));
        System.out.println("Полная стоимость проекта: " + formatDouble(totalProjectCost) + " чел*ч, " + formatDouble(totalProjectCostMonths) + " чел*м");
        System.out.println("Срок реализации проекта: " + formatDouble(projectDuration) + " месяцев");
        System.out.println("Средний размер команды: " + formatDouble(projectAvgTeamSize) + " человек");
    }

    private static double stdDev(Cost cost) {
        return ((double) (cost.getMax() - cost.getMin())) / 6;
    }

    private static double avgCost(Cost cost) {
        return ((double) (cost.getMin() + 4 * cost.getExpected() + cost.getMax())) / 6;
    }

    private static String formatDouble(double value) {
        return String.valueOf((double) Math.round(value * 100) / 100);
    }

    private static String formatPercent(double value) {
        return (double) Math.round(value * 10000) / 100 + " %";
    }

    private static int readPositiveInt(String prompt) {
        var in = new Scanner(System.in);
        int result;
        while (true) {
            System.out.println(prompt);
            result = in.nextInt();

            if (result < 0) {
                System.out.println("Введите неотрицательное число");
                continue;
            }

            break;
        }

        return result;
    }
}
