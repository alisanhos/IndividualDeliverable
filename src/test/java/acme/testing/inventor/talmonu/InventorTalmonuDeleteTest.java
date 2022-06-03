package acme.testing.inventor.talmonu;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorTalmonuDeleteTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/talmonu/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my talmonu");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkSubmitExists("Delete");
		super.clickOnSubmit("Delete");
		super.checkListingExists();
		
		super.signOut();
	}
	
	@Order(30)
	public void hackingTest() {
		//		a) delete a talmonu with a role other than "Inventor";
	}

}
