package acme.testing.patron.patronageReport;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronageReportListTest extends TestHarness {
	

	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage-report/list-patronage-report.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void checkListAllPatronageReportShow(final int patronageIndex,final int recordIndex, final String reference,
		 final String moment, final String memorandum, final String info, final String sequenceNumber) {
	
		super.signIn("patron2", "patron2");
		
		super.navigateHome();
		
		//patronages
		super.clickOnMenu("Patron", "My Patronages");
		this.clickOnListingRecord(patronageIndex); 
		super.clickOnButton("Reports");

		//reports
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 1, memorandum);
		super.checkColumnHasValue(recordIndex, 2, info);
		this.clickOnListingRecord(recordIndex); 

		super.checkFormExists();
		super.checkInputBoxHasValue("moment", moment);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("sequenceNumber", sequenceNumber);
		
		super.signOut();

		
		
		
		
	}

	

	

}
