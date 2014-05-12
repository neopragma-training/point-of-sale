package pos.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import pos.testhelpers.DatabaseHelper;
import pos.testhelpers.IntegrationTest;

@Category(IntegrationTest.class)
public class EmployeeTest {
	
	Properties config;
	DatabaseHelper databaseHelper = new DatabaseHelper();
	
	@Before 
	public void before_each_test_case() {
	}
	
	@Test
	public void it_saves_a_new_employee() throws Exception {
		databaseHelper.clearCollection("employees");
		Employee.newEmployee("123", "FirstName", "LastName", "Position", "2000-07-15", "2014-06-14" );
		assertThat(Employee.exists("123"), equalTo(true));
	}
	
	@Test(expected=DuplicateEmployeeException.class)
	public void it_does_not_save_a_duplicate_record() throws Exception {
		Employee.newEmployee("123", "FirstName", "LastName", "Position", "2000-07-15", "2014-06-14" );
	}

}
