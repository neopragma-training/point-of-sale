package pos.common;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import pos.common.CustomerName;
import pos.testhelpers.UnitTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@Category(UnitTest.class)
public class CustomerNameTest {

	private CustomerName customerName = new CustomerName("Bettencourt", "John", "Westinghouse", "Mr", "Jr");
	
	@Test
	public void it_returns_the_display_name() {
        assertThat(customerName.displayName(), equalTo("John W. Bettencourt"));
	}
	
	@Test
	public void it_returns_the_full_name() {
		assertThat(customerName.fullName(), equalTo("Mr. John Westinghouse Bettencourt, Jr."));
	}
	
	@Test
	public void it_returns_the_short_name() {
		assertThat(customerName.shortName(), equalTo("BETTENCOJW"));
	}
	
}
