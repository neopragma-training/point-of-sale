package pos.common;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import pos.common.AccountNumber;
import pos.common.InvalidAccountNumberException;
import pos.testhelpers.UnitTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@Category(UnitTest.class)
public class AccountNumberTest {
	
	@Test(expected=InvalidAccountNumberException.class)
	public void when_value_is_too_short_it_throws() throws InvalidAccountNumberException {
		new AccountNumber("123456789012345");
	}
	
	@Test(expected=InvalidAccountNumberException.class)
	public void when_value_is_too_long_it_throws() throws InvalidAccountNumberException {
		new AccountNumber("12345678901234567");
	}
	
	@Test(expected=InvalidAccountNumberException.class)
	public void when_value_is_null_it_throws() throws InvalidAccountNumberException {
		new AccountNumber(null);
	}
	
	@Test(expected=InvalidAccountNumberException.class)
	public void when_value_contains_nonnumerics_it_throws() throws InvalidAccountNumberException {
		new AccountNumber("12345X7890123456");
	}
	
	@Test
	public void it_returns_the_last_four_digits() throws InvalidAccountNumberException {
		AccountNumber accountNumber = new AccountNumber("1111111111112222");
		assertThat(accountNumber.lastFourDigits(), equalTo("2222"));
	}
}
