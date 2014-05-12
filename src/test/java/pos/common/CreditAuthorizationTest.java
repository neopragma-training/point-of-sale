package pos.common;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import pos.common.AccountNumber;
import pos.common.Auth;
import pos.common.CardData;
import pos.common.CustomerName;
import pos.common.InvalidAccountNumberException;
import pos.store.RegisterHandler;
import pos.testhelpers.IntegrationTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@Category(IntegrationTest.class)
public class CreditAuthorizationTest {
	
	@Test
	public void it_approves_a_valid_auth_request() throws Exception {
		RegisterHandler pm = new RegisterHandler(null);
		Auth result = pm.authorize(goodCard(), 15);
		assertThat(result.getAuthCode(), equalTo(30));
	}
	
	@Test
	public void it_denies_an_auth_request_for_an_invalid_account() throws Exception {
		RegisterHandler pm = new RegisterHandler(null);
		Auth result = pm.authorize(badCard(), 15);
		assertThat(result.getAuthCode(), equalTo(99));
	}
	
	@Test
	public void it_denies_an_auth_request_for_an_amount_above_the_limit() throws Exception {
		RegisterHandler pm = new RegisterHandler(null);
		Auth result = pm.authorize(goodCard(), 1201);
		assertThat(result.getAuthCode(), equalTo(99));
	}
	
	@Test
	public void it_denies_an_auth_request_for_an_expired_card() throws Exception {
		RegisterHandler pm = new RegisterHandler(null);
		Auth result = pm.authorize(expiredCard(), 100);
		assertThat(result.getAuthCode(), equalTo(66));
	}
	
	private CardData goodCard() throws InvalidAccountNumberException {
		return makeCardData("1111222233334444", futureDate());
	}
	
	private CardData badCard() throws InvalidAccountNumberException {
		return makeCardData("5111222233334444", futureDate());
	}
	private CardData expiredCard() throws InvalidAccountNumberException {
		Calendar pastDate = Calendar.getInstance();
		pastDate.add(Calendar.DATE, -1);
		return makeCardData("1111222233334444", pastDate.getTime());
	}
	
	private Date futureDate() {
		Calendar futureDate = Calendar.getInstance();
		futureDate.add(Calendar.DATE, 90);
		return futureDate.getTime();
	}
	
	private CardData makeCardData(String accountNumber, Date expiryDate) throws InvalidAccountNumberException {
		return new CardData(
				new CustomerName("Smith", "John", "Quincy", "Mr", "Jr"), 
				expiryDate,
				new AccountNumber(accountNumber));
	}

}
