import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.peter.Bank;
import com.peter.Customer;

class Tests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		Bank theBank = new Bank("Bank of America");
		assertEquals("Bank of America", theBank.getName());
	}
	
	@Test
	void test2() {
		Bank theBank = new Bank("Bank of America");
		Customer aUser = theBank.addCustomer("John123", "123");
		
		assertNotNull(aUser);
		assertEquals("John123", aUser.getFirstName());
		
	}
	

}
