package messages.model;

public class MessageBoxB implements MessageBox {

    private String message;

    @Override
    public synchronized void post(String message) {
        while (this.message != null){
            try {
                this.wait();
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        this.message = message;
        this.notify();
    }

    @Override
    public synchronized String get() {
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
    }

}
