package pos.store;

/**
 * Main class for the store Point of Sale application
 */
import static pos.common.Utils.log;
import static pos.common.Utils.posPort;

import java.net.ServerSocket;
import java.net.Socket;

public class PosManager {

    private ServerSocket ssock;
    
    void run() throws Exception {
        ssock = new ServerSocket(posPort());
        log("listening", String.valueOf(posPort()));
        while (true) {
            Socket sock = ssock.accept();
            log("connection.accepted", (Object[]) null);
            new Thread(new RegisterHandler(sock)).start();
        }
    }

    public static void main(String args[]) throws Exception {
    	PosManager pos = new PosManager();
    	pos.run();
    	System.exit(0);
    }
   
}
