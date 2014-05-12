package pos.common;

public class AccountNumber {
	
	private String accountNumber;
	
	public AccountNumber(String accountNumber) throws InvalidAccountNumberException {
		if (accountNumber == null ||
		    !accountNumber.matches("^\\d{16}$")) {
			throw new InvalidAccountNumberException();
		}
		this.accountNumber = accountNumber;
	}
	
	public String lastFourDigits() {
		return accountNumber.substring(12);
	}
	
	public String asString() {
		return accountNumber;
	}

}
