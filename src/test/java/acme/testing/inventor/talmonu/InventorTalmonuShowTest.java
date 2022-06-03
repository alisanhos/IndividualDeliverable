package acme.testing.inventor.talmonu;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorTalmonuShowTest  extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/talmonu/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String item, final String creationMoment, final String theme, final String explanation, final String startPeriod, final String endPeriod, final String expenditure,final String furtherInfo) {
		
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my talmonu");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("item", item);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("theme", theme);
		super.checkInputBoxHasValue("explanation", explanation);
		super.checkInputBoxHasValue("startPeriod", startPeriod);
		super.checkInputBoxHasValue("endPeriod", endPeriod);
		super.checkInputBoxHasValue("expenditure", expenditure);
		super.checkInputBoxHasValue("furtherInfo", furtherInfo);
		
		super.signOut();
	}

}
