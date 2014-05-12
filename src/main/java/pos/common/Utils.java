package pos.common;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.log4j.Logger;

public class Utils {
	
	private static Config config = null;
	private static Logger logger = null;
    private static Messages messages = null;
	private static String DEFAULT_LOCALE = "en_US";

	private static final String DATABASE_HOST_KEY = "database.host";
	private static final String DATABASE_HOST_MESSAGE = 
		    "Database host must be defined in pos.properties, e.g. " + 
		    		DATABASE_HOST_KEY + " = localhost";

	private static final String DATABASE_NAME_KEY = "database.name";
	private static final String DATABASE_NAME_MESSAGE = 
	    "Database name must be defined in pos.properties, e.g. " + 
		DATABASE_NAME_KEY + " = pos_test";

	private static final String AUTH_ENDPOINT_KEY = "auth.endpoint";
	private static final String AUTH_ENDPOINT_MESSAGE = 
	    "Authorization service endpoint must be defined in pos.properties, e.g. " + 
		AUTH_ENDPOINT_KEY + " = https://something/";

	private static final String POS_HOST_KEY = "pos.host";
	private static final String POS_HOST_MESSAGE = 
		    "Point of Sale store host must be defined in pos.properties, e.g. " + 
		    		POS_HOST_KEY + " = localhost";

	private static final String POS_PORT_KEY = "pos.port";
	private static final String POS_PORT_MESSAGE = 
		    "Point of Sale store port must be defined in pos.properties, e.g. " + 
		    		POS_PORT_KEY + " = 1234";

	private static final String POS_CONNECTION_TIMEOUT_MILLIS_KEY = "pos.connection.timeout.millis";
	private static final String POS_CONNECTION_TIMEOUT_MILLIS_MESSAGE =
			"Timeout for connecting to the POS store application must be defined in pos.properties, e.g. " +
	               POS_CONNECTION_TIMEOUT_MILLIS_KEY + " 3000";

	private static final String LOGGER_NAME_KEY = "logger.name";
	private static final String LOGGER_NAME_MESSAGE = 
		    "Logger name must be defined in pos.properties, e.g. " + 
		    		LOGGER_NAME_KEY + " = PosManager";

	private static final String LOCALE_KEY = "locale";
	@SuppressWarnings("unused")
	private static final String LOCALE_MESSAGE = "using locale ";

	private static final String noFileMessage = "Unable to read pos.properties file.";

	private static TimeSource timeSource = null;
	
    public static String databaseHost() {
   		return get(DATABASE_HOST_KEY, DATABASE_HOST_MESSAGE);
    }
	
    public static String databaseName() {
   		return get(DATABASE_NAME_KEY, DATABASE_NAME_MESSAGE);
    }
	
    public static String authEndpoint() {
   		return get(AUTH_ENDPOINT_KEY, AUTH_ENDPOINT_MESSAGE);
    }
	
    public static String posHost() {
   		return get(POS_HOST_KEY, POS_HOST_MESSAGE);
    }
	
    public static int posPort() {
   		return Integer.parseInt(get(POS_PORT_KEY, POS_PORT_MESSAGE));
    }
	
    public static String loggerName() {
   		return get(LOGGER_NAME_KEY, LOGGER_NAME_MESSAGE);
    }
    
    public static int posConnectionTimeoutMillis() {
    	return Integer.parseInt(get(POS_CONNECTION_TIMEOUT_MILLIS_KEY, POS_CONNECTION_TIMEOUT_MILLIS_MESSAGE));
    }
	
    public static Locale locale() throws IOException {
    	String languageTag = config().get(LOCALE_KEY);
    	if (languageTag == null) {
    		languageTag = DEFAULT_LOCALE;
    	}
   		return Locale.forLanguageTag(languageTag);
    }
    
    public static String get(String key, String notFoundMessage) {
    	try {
		    String value = config().get(key);
		    if (value == null) {
			    throw new ConfigurationException(notFoundMessage);
		    }
		    return value;
	    } catch (IOException e) {
		    throw new ConfigurationException(noFileMessage);
	    }
    }
    
    private static Config config() {
    	if (config == null) {
    		config = new Config();
    	}
    	return config;
    }
        
    public static void setConfig(Config newConfig) {
    	config = newConfig;
    }

    public static void log(String messageKey) throws IOException {
    	log(messageKey, new Object[] { });
    }
    
    public static void log(String messageKey, Object...args) throws IOException {
        logger().info(MessageFormat.format(messages().message(messageKey), args));
    }
    
    public static String message(String messageKey, Object...args) throws IOException {
    	return MessageFormat.format(messages().message(messageKey), args);
    }
    
    private static Logger logger() {
    	if (logger == null) {
    		logger = Logger.getLogger(loggerName());
    	}
   	    return logger;
    }
    
    private static Messages messages() throws IOException {
    	if (messages == null) {
    		messages = new Messages();
    	}
    	return messages;
    }
    
    public static Calendar today() {
    	Calendar today = Calendar.getInstance();
    	today.setTimeInMillis(timeSource().currentTimeMillis());
    	return today;
    }
    
    private static TimeSource timeSource() {
    	if (timeSource == null) {
    		timeSource = new DefaultTimeSource();
    	}
    	return timeSource;
    }

    public static void setTimeSource(TimeSource newTimeSource) {
    	timeSource = newTimeSource;
    }
    

}
