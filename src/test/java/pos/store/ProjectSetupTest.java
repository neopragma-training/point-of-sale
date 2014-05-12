package pos.store;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import pos.store.RegisterHandler;
import pos.testhelpers.IntegrationTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@Category(IntegrationTest.class)
public class ProjectSetupTest {
	
	@Test
	public void healthCheck_should_echo_the_string_passed_in() throws Exception {
		RegisterHandler pm = new RegisterHandler(null);
		assertThat("Project is not set up correctly.", pm.doEcho("echo").getMessage(), equalTo("echo"));
	}

}
