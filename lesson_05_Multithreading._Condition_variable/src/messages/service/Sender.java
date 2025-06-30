package messages.service;

import messages.model.MessageBox;

public class Sender implements Runnable {

    private MessageBox messageBox;
    private int nMessage;

    public Sender(MessageBox messageBox, int nMessage) {
        this.messageBox = messageBox;
        this.nMessage = nMessage;
    }

    @Override
    public void run() {
        for (int i = 0 ; i < nMessage; i++){
            messageBox.post("message#" + i);
        }
    }
}
