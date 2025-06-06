package hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class TarakansRace {


    private static volatile boolean raceFinished = false;

    private static final AtomicInteger winnerId = new AtomicInteger(-1);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTarakans = 0;
        int distance = 0;

        while (numTarakans <= 0) {
            System.out.print("Введите количество тараканов (целое число > 0): ");
            try {
                numTarakans = Integer.parseInt(scanner.nextLine());
                if (numTarakans <= 0) {
                    System.out.println("Количество тараканов должно быть больше нуля.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
            }
        }

        while (distance <= 0) {
            System.out.print("Введите дистанцию гонки (количество итераций, целое число > 0): ");
            try {
                distance = Integer.parseInt(scanner.nextLine());
                if (distance <= 0) {
                    System.out.println("Дистанция должна быть больше нуля.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
            }
        }

        System.out.println(String.format("\nНачинаем гонку с %d тараканами на дистанцию %d итераций!", numTarakans, distance));
        System.out.println("Тараканы спят случайное время от 2 до 5 миллисекунд между итерациями.");

        List<Tarakan> tarakans = new ArrayList<>();

        for (int i = 1; i <= numTarakans; i++) {

            Tarakan tarakan = new Tarakan(i, distance, winnerId, raceFinished);
            tarakans.add(tarakan);
            tarakan.start();
        }

        while (winnerId.get() == -1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Главный поток был прерван.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        raceFinished = true;

        for (Tarakan tarakan : tarakans) {
            tarakan.setRaceFinished(true);
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        int finalWinnerId = winnerId.get();
        if (finalWinnerId != -1) {
            System.out.println(String.format("\nПоздравляем таракана #%d (победитель)!!!", finalWinnerId));
        } else {
            System.out.println("\nГонка завершилась без явного победителя."); // Крайне маловероятно в данном сценарии
        }

        scanner.close();
    }

}
