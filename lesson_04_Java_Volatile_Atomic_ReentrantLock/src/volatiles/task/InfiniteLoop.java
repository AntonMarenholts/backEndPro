package volatiles.task;

public class InfiniteLoop implements Runnable {
    private volatile boolean isFlag = true;

    public InfiniteLoop() {
        this.isFlag = isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    @Override
    public void run() {

    }
}
