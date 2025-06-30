package messages.service;

import messages.model.MessageBox;

public class Recever implements Runnable {

    private MessageBox messageBox;

    public Recever(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        while (true) {
            String message = messageBox.get();
            System.out.println(message + " - receiver " + Thread.currentThread().getName());
        }
    }
}
