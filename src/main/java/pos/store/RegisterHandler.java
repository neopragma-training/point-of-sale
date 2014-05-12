package pos.store;

import static pos.common.Utils.authEndpoint;
import static pos.common.Utils.log;
import static pos.common.Utils.today;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.Calendar;

import org.apache.log4j.Logger;

import pos.common.Auth;
import pos.common.CardData;

import com.google.gson.Gson;

/**
 * Handles interaction with a register.
 * 
 * @author neopragma
 * 
 * Copyright 2014 Our Fine Company Inc.
 */
public class RegisterHandler implements Runnable {

	private Socket sock;
    private PrintStream output;
    private InputStream input;
    private static final String SEPARATOR = "/";
	private static Logger log;
	private boolean running;
	
	public RegisterHandler(Socket sock) {
		this.sock = sock;
	}
    
    Auth doEcho(String value) throws Exception {
    	URL endpointURL = new java.net.URL(authEndpoint() + "echo");
        HttpURLConnection request = (HttpURLConnection)endpointURL.openConnection();
        request.setRequestMethod("GET");
        request.connect();
        BufferedReader rd  = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line = null;
        while ((line = rd.readLine()) != null){
          response.append(line);
        }
        request.disconnect();
        rd.close();
        return new Auth(null, null, null, null, response.toString());
    }
    
    public Auth authorize(CardData cardData, Integer amount) throws Exception {
    	Calendar cardExpiryDate = Calendar.getInstance();
    	cardExpiryDate.setTime(cardData.getExpiryDate());
    	if (today().after(cardExpiryDate)) {
    		return new Auth(null, null, 66, null, null);
    	}
    	return doAuth(cardData.getAccountNumber().asString(), amount);
    }
    
    private Auth doAuth(String accountNumber, Integer amount) throws Exception {
    	StringBuilder sb = new StringBuilder(authEndpoint());
    	sb.append("auth");
    	sb.append(SEPARATOR);
    	sb.append("12345");
    	sb.append(SEPARATOR);
    	sb.append(accountNumber);
    	sb.append(SEPARATOR);
    	sb.append(amount);
    	URL endpointUrl = new java.net.URL(sb.toString());
        HttpURLConnection request = (HttpURLConnection)endpointUrl.openConnection();
        request.setRequestMethod("GET");
        request.connect();
    	Gson gson = new Gson();
    	Auth auth = null; 
   		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
   		auth = gson.fromJson(br, Auth.class);
        request.disconnect();
        br.close();
        return auth;
    }

	@Override
	public void run() {
        try {
		    running = true;
	        output = new PrintStream(sock.getOutputStream());
	        input = new BufferedInputStream(sock.getInputStream());
            while(running) {
    		    byte[] buffer = new byte[4096];
			    input.read(buffer);
    			String received = new String(buffer).trim();
    			if (received.length() > 0) {
			        log("received", this.getClass().getSimpleName(), received);
			        if (received.equals("close")) {
			            close();
			            return;
			        }
			        log("sending", getClass().getSimpleName(), received);
                    output.println(received);
    			}
            }
        } catch (IOException e) {
		    log.info("IOException: " + e.getMessage());
		    e.printStackTrace();
	    }
	}
	
	private void close() throws IOException {
		log("closing", getClass().getSimpleName());
		running = false;
		output.println("close");
        output.close();
        sock.close();
	}
        
}
