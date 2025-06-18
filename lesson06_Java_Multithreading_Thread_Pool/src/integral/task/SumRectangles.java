package integral.task;

import java.util.function.Function;

public class SumRectangles implements Runnable{

    private double a;
    private double b;
    private Function<Double, Double> func;
    private int nParts;
    private int nTasks;
    private int threadNumber;
    private double result;


    public SumRectangles(double a, double b, Function<Double, Double> func, int nParts, int nTasks, int threadNumber) {
        this.a = a;
        this.b = b;
        this.func = func;
        this.nParts = nParts;
        this.nTasks = nTasks;
        this.threadNumber = threadNumber;
    }

    public double getResult() {
        return result;
    }

    @Override
    public void run() {
        double delta = (b - a) / nParts;
        for (int i = threadNumber; i < nParts; i += nTasks){
            double l = a + i * delta;
            double r = l + delta;
            double mid = (l + r) / 2;
            result += delta * func.apply(mid);
        }
    }
}
