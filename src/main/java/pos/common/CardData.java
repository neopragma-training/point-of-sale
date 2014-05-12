package pos.common;

import java.util.Date;

/**
 * Represents data read from a payment card or equivalent device.
 * 
 * @author neopragma
 */
public class CardData {
    private CustomerName customerName;
	private Date expiryDate;
    private AccountNumber accountNumber;
    private Integer storedValue;
    private String loyaltyCode;
    
    public CardData(
    		CustomerName customerName,
    		Date expiryDate,
    		AccountNumber accountNumber,
    		Integer storedValue,
    		String loyaltyCode) {
    	this.customerName = customerName;
    	this.expiryDate = expiryDate;
    	this.accountNumber = accountNumber;
    	this.storedValue = storedValue;
    	this.loyaltyCode = loyaltyCode;
    }
    
    public CardData(
    		CustomerName customerName,
    		Date expiryDate,
    		AccountNumber accountNumber) {
    	this(customerName, expiryDate, accountNumber, 0, "None");
    }

    public Integer getStoredValue() {
		return storedValue;
	}
	public void setStoredValue(Integer storedValue) {
		this.storedValue = storedValue;
	}
	public String getLoyaltyCode() {
		return loyaltyCode;
	}
	public void setLoyaltyCode(String loyaltyCode) {
		this.loyaltyCode = loyaltyCode;
	}
	public CustomerName getCustomerName() {
		return customerName;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public AccountNumber getAccountNumber() {
		return accountNumber;
	}
}
