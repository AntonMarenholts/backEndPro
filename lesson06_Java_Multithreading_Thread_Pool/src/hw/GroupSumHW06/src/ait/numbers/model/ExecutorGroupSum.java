package hw.GroupSumHW06.src.ait.numbers.model;

import hw.GroupSumHW06.src.ait.numbers.task.OneGroupSum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorGroupSum extends GroupSum{
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // TODO ExecutorGroupSum

        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        List<Future<Integer>> futures = new ArrayList<>();

        for (int[] group : numberGroups) {

            OneGroupSum task = new OneGroupSum(group);

            futures.add(executorService.submit(() -> {
                task.run();
                return task.getSum();
            }));
        }

        int totalSum = 0;

        for (Future<Integer> future : futures) {
            try {
                totalSum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        return totalSum;
    }
}