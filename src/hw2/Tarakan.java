package hw2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Tarakan extends Thread {

    private final int tarakanId;
    private final int distance;


    private final AtomicInteger winnerId;
    private volatile boolean raceFinished;

    private final Random random = new Random();


    public Tarakan(int tarakanId, int distance, AtomicInteger winnerId, boolean raceFinished) {
        this.tarakanId = tarakanId;
        this.distance = distance;
        this.winnerId = winnerId;
        this.raceFinished = raceFinished;

        setName("Таракан #" + tarakanId);
    }

    @Override
    public void run() {

        System.out.println("Таракан #" + tarakanId + " стартовал!");

        for (int i = 1; i <= distance; i++) {
            if (raceFinished) {
                return;
            }
            System.out.println("Таракан #" + tarakanId + " - итерация " + i);
            try {

                long sleepTime = random.nextInt(4) + 2;
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Таракан #" + tarakanId + " был прерван.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        if (winnerId.compareAndSet(-1, tarakanId)) {
            this.raceFinished = true;
        }
    }

    public void setRaceFinished(boolean raceFinished) {
        this.raceFinished = raceFinished;
    }

}
