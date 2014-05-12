package pos.common;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

	private static final String BASE_NAME = "messages";
	private ResourceBundle bundle = null;
	public Locale locale = Utils.locale();
	
	public Messages() throws IOException { }
	
	public String message(String key) {
		Locale.setDefault(locale);
		return bundle().getString(key);
	}
	
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
	
	private ResourceBundle bundle() {
		if (bundle == null) {
			bundle = ResourceBundle.getBundle(BASE_NAME, locale);
		}
		return bundle;
	}
}
