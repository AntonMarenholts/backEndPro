package messages.model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageBoxC implements MessageBox{

    private String message;
    private final Lock mutex = new ReentrantLock();
    private final Condition senderWaitCondition = mutex.newCondition();
    private final Condition receiverWaitCondition = mutex.newCondition();

    @Override
    public void post(String message) {
        mutex.lock();
        try {
            while (this.message != null){
                try {
                    senderWaitCondition.await();
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
            this.message = message;
            receiverWaitCondition.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get() {
        mutex.lock();

        try {
            while (message == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String res = message;
            message = null;
            this.notifyAll();
            return res;
        } finally {
            mutex.unlock();
        }
    }
}
