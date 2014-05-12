package pos.common;

public class Auth {
	
	public Auth(String account, Integer amount, Integer authCode, String corrTag, String message) {
		this.account = account;
		this.amount = amount;
		this.authCode = authCode;
		this.corrTag = corrTag;
		this.message = message;
	}
	
	public String getAccount() {
		return account;
	}
	public Integer getAmount() {
		return amount;
	}
	public Integer getAuthCode() {
		return authCode;
	}
	public String getCorrTag() {
		return corrTag;
	}
	public String getMessage() {
		return message;
	}

	private String account;
	private Integer amount;
	private Integer authCode;
	private String corrTag;
	private String message;

}
