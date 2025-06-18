package volatiles;

import volatiles.task.InfiniteLoop;

public class InfinitiLoopApp {
    public static void main(String[] args) throws InterruptedException {
        InfiniteLoop loop = new InfiniteLoop();
        Thread thread = new Thread(loop);
        thread.start();
        Thread.sleep(3000);
        loop.setFlag(false);
        System.out.println(Thread.currentThread().getName() + " finished");
    }

}
