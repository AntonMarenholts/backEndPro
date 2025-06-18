package integral;

import integral.task.SumRectangles;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolIntegralAppl {
    private static final int N_TASKS = 10000;
    private static final int A = 0;
    private static final int B = 3;
    private static final int N_PARTS = 10000000;


    public static void main(String[] args) throws InterruptedException {
        SumRectangles[] tasks = new SumRectangles[N_TASKS];
        for (int i = 0; i < tasks.length; i++){
            tasks[i] = new SumRectangles(A,B, x -> x * x, N_PARTS, N_TASKS, i);
        }

        long t1 = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Thread[] threads = new Thread[N_TASKS];
        for (int i = 0; i < tasks.length;i++){
            executorService.execute(tasks[i]);
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        long t2 = System.currentTimeMillis();
        System.out.println("Duration = " + (t2 - t1));

        double res = Arrays.stream(tasks).mapToDouble(SumRectangles::getResult).sum();
        System.out.println("Result = " + res);



    }
}
