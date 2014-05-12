package pos.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import pos.testhelpers.DatabaseHelper;
import pos.testhelpers.IntegrationTest;

@Category(IntegrationTest.class)
public class ProductTest {
	
	Properties config;
	DatabaseHelper databaseHelper = new DatabaseHelper();
	
	@Before 
	public void before_each_test_case() {
	}
	
	@Test
	public void it_saves_a_new_product() throws Exception {
		databaseHelper.clearCollection("products");
		Product.newProduct("12345", "Bubble Gum", new BigDecimal("0.85"), true);
		assertThat(Product.exists("12345"), equalTo(true));
	}
	
	@Test(expected=DuplicateSkuException.class)
	public void it_does_not_save_a_duplicate_sku() throws UnknownHostException, DuplicateSkuException {
		Product.newProduct("12345", "Bubble Gum", new BigDecimal("0.85"), true);
	}
	
	@Test
	public void it_returns_the_complete_list_of_products() throws Exception {
		Product.findAll();
	}

}
