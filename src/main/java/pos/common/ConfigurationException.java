package pos.common;

public class ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1649704341288709516L;
	private String message;
	
	public ConfigurationException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
