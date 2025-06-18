package messages;

import messages.model.MessageBox;
import messages.model.MessageBoxA;
import messages.model.MessageBoxB;
import messages.service.Recever;
import messages.service.Sender;

public class MessageBoxAppl {
    private static final int N_MESSAGES = 20;
    private static final int N_RECEIVERS = 5;
    private static MessageBox messageBox = new MessageBoxB();
    public static void main(String[] args) throws InterruptedException {

        Thread sender = new Thread(new Sender(messageBox, N_MESSAGES));
        for (int i=0; i < N_RECEIVERS; i++){
            Thread receiver = new Thread(new Recever(messageBox));
            receiver.setDaemon(true);
            receiver.start();
        }
        Thread.sleep(100);
        sender.start();
        sender.join();
        Thread.sleep(100);


    }
}
