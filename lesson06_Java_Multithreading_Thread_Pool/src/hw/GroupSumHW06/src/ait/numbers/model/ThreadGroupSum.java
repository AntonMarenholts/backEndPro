package hw.GroupSumHW06.src.ait.numbers.model;

import hw.GroupSumHW06.src.ait.numbers.task.OneGroupSum;

import java.util.ArrayList;
import java.util.List;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // TODO ThreadGroupSum
        List<OneGroupSum> tasks = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();


        for (int[] group : numberGroups) {
            OneGroupSum task = new OneGroupSum(group);
            tasks.add(task);
            threads.add(new Thread(task));
        }


        for (Thread thread : threads) {
            thread.start();
        }


        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        int totalSum = 0;
        for (OneGroupSum task : tasks) {
            totalSum += task.getSum();
        }

        return totalSum;
    }
}