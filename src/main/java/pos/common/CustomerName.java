package pos.common;

public class CustomerName {
	
	private static final String SPACE = " ";
	private static final String PERIOD = ".";
	private static final String PERIOD_SPACE = ". ";
	private static final String COMMA_SPACE = ", ";
	private String surname;
	private String givenName;
	private String middleName;
	private String honorific;
	private String suffix;
	
	public CustomerName(String surname, String givenName, String middleName, String honorific, String suffix) {
		this.surname = surname;
		this.givenName = givenName;
		this.middleName = middleName;
		this.honorific = honorific;
		this.suffix = suffix;
	}

	/**
	 * @return the name as "John B. Smith"
	 */
	public String displayName() {
		StringBuilder sb = new StringBuilder();
		sb.append(givenName);
		sb.append(SPACE);
		sb.append(middleName.substring(0,1));
		sb.append(PERIOD_SPACE);
		sb.append(surname);
		return sb.toString();
	}

	public String fullName() {
		StringBuilder sb = new StringBuilder();
		sb.append(honorific);
		sb.append(PERIOD_SPACE);
		sb.append(givenName);
		sb.append(SPACE);
		sb.append(middleName);
		sb.append(SPACE);
		sb.append(surname);
		if (!isEmpty(suffix)) {
			sb.append(COMMA_SPACE);
			sb.append(suffix);
			sb.append(PERIOD);
		}
		return sb.toString();
	}
	
	public String shortName() {
		StringBuilder sb = new StringBuilder();
		sb.append(surname.substring(0,8).toUpperCase());
		sb.append(givenName.substring(0,1));
		sb.append(middleName.substring(0,1));
		return sb.toString();
	}

	private boolean isEmpty(String value) {
		return (value == null || value.length() == 0);
	}

}
