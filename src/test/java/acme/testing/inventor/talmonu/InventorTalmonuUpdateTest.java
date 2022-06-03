package acme.testing.inventor.talmonu;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorTalmonuUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/talmonu/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String theme, final String explanation,
		final String startPeriod, final String endPeriod, final String expenditure, final String furtherInfo) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my talmonu");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("theme", theme);
		super.fillInputBoxIn("explanation", explanation);
		super.fillInputBoxIn("startPeriod", startPeriod);
		super.fillInputBoxIn("endPeriod", endPeriod);
		super.fillInputBoxIn("expenditure", expenditure);
		super.fillInputBoxIn("furtherInfo", furtherInfo);
		
		super.checkSubmitExists("Update");
		super.clickOnSubmit("Update");
		super.checkListingExists();
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("theme", theme);
		super.checkInputBoxHasValue("explanation", explanation);
		super.checkInputBoxHasValue("startPeriod", startPeriod);
		super.checkInputBoxHasValue("endPeriod", endPeriod);
		super.checkInputBoxHasValue("expenditure", expenditure);
		super.checkInputBoxHasValue("furtherInfo", furtherInfo);
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/talmonu/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String theme, final String explanation,
		final String startPeriod, final String endPeriod, final String expenditure, final String furtherInfo) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my talmonu");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.fillInputBoxIn("theme", theme);
		super.fillInputBoxIn("explanation", explanation);
		super.fillInputBoxIn("startPeriod", startPeriod);
		super.fillInputBoxIn("endPeriod", endPeriod);
		super.fillInputBoxIn("expenditure", expenditure);
		super.fillInputBoxIn("furtherInfo", furtherInfo);
		
		super.checkSubmitExists("Update");
		super.clickOnSubmit("Update");
	
		super.checkNotPanicExists();
		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		//		a) update a talmonu with a role other than "Inventor";
	}
}
