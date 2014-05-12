package pos.register;

import static pos.common.Utils.log;
import static pos.common.Utils.posConnectionTimeoutMillis;
import static pos.common.Utils.posHost;
import static pos.common.Utils.posPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import pos.common.RegisterStatus;
import pos.register.ui.RegisterUI;

public class Register {
	
	private static final String EMPTY_STRING = "";
	private String currentProductSelection = EMPTY_STRING;
	private int currentQuantity = 1;
	private Socket posManagerSocket;
	private PrintStream toPosManager;
	private InputStream fromPosManager;
	private byte[] buffer;
	private boolean running;
	private String registerIdentifier;
	RegisterStatus status = RegisterStatus.CLOSED;
	private RegisterUI ui;
	
	public Register(String registerIdentifier) {
		this.registerIdentifier = registerIdentifier;
	}
	
	public void init() throws UnknownHostException, IOException {
		ui = new RegisterUI();
		ui.init(this);
		connectToStoreSystem();
		run();
	}

	public String getCurrentProductSelection() {
		return currentProductSelection;
	}
	
	public void setCurrentProductSelection(String currentProductSelection) {
		this.currentProductSelection = currentProductSelection;
	}

	public int getCurrentQuantity() {
		return currentQuantity;
	}
	
	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	
	public String getRegisterIdentifier() {
		return registerIdentifier;
	}
	
	public RegisterStatus getStatus() {
		return status;
	}
	
	private void connectToStoreSystem() throws UnknownHostException, IOException {
		posManagerSocket = new Socket();
		log("attempting.connection", registerIdentifier);
		try {
			posManagerSocket.connect(
				new InetSocketAddress(posHost(), posPort()), posConnectionTimeoutMillis());
		} catch (Exception e) {
            log("unable.to.connect", registerIdentifier);
            return;
		}		
		toPosManager = new PrintStream(posManagerSocket.getOutputStream());
	    fromPosManager = posManagerSocket.getInputStream();
		toPosManager.println("init " + registerIdentifier);
		toPosManager.flush();
		running = true;		
	}
	
	public void run() throws IOException {
		while (running) {
		    buffer = new byte[4096];
			fromPosManager.read(buffer);
			String received = new String(buffer);
			if (received.trim().length() > 0) {
			    processMessageFromPosManager(new String(buffer));
			}
		}
	}

	private void processMessageFromPosManager(String message) throws IOException {
		if (message.equals("close")) {
			close();
			running = false;
		}
	}
	
	private void close() throws IOException {
		log("closing", registerIdentifier);
		toPosManager.println("close");
		toPosManager.flush();
		//TODO need ack from server here before closing socket
		posManagerSocket.close();
		System.exit(0);
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		if (args.length < 1) {
			throw new RuntimeException("Register identifier must be passed as the first command line argument");
		}
		Register register = new Register(args[0]);
		register.init();
		System.exit(0);
	}

}
