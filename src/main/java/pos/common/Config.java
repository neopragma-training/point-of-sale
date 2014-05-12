package pos.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Properties;

public class Config {
	
	private Properties config = null;
	private static final String CONFIG_FILE = "pos.properties";
		
	public String get(String key) throws IOException {
		return config().getProperty(key);
	}
	
	public void dumpConfigSettingsTo(PrintStream ps) {
	    for (Iterator<Object> iter = config.keySet().iterator() ; iter.hasNext() ;) {
	    	String key = (String) iter.next();
	    	ps.println(key + " = " + config.get(key));
	    }
	}
	
	private Properties config() throws IOException {
		if (config == null) {
		    config = new Properties();
		    ClassLoader loader = Thread.currentThread().getContextClassLoader();           
		    InputStream stream = loader.getResourceAsStream(CONFIG_FILE);
		    config.load(stream);
		}
		return config;
	}

	
	
}
