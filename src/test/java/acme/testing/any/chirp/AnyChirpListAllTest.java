package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpListAllTest extends TestHarness {

	@ParameterizedTest	
	@CsvFileSource(resources = "/any/chirp/list-recent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String email) {

		super.clickOnMenu("Anonymous", "List recent chirps");
		super.checkListingExists();
		super.sortListing(1, "asc");	
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		//There is no possible hacking
	}
}