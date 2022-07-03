/*
 * EmployerDutyCreateTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.framework.testing.BrowserDriver;
import acme.testing.TestHarness;

public class AdministratorAnnouncementCreateTest extends TestHarness {

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-announcement-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String body, final String critical, final String link) {

		super.signIn("administrator", "administrator");
		super.navigateHome();
		super.clickOnMenu("Administrator", "Create Announcements");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@name=\"critical_proxy\"]/option[\""+ critical +"\"]")).click();
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");

		super.navigateHome();
		super.clickOnMenu("Authenticated", "Announcements");
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 2, body);
		
		super.signOut();


	}

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-announcement-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String title, final String body, final String critical, final String link) {

		super.signIn("administrator", "administrator");
		super.navigateHome();
		super.clickOnMenu("Administrator", "Create Announcements");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		final BrowserDriver driver = super.getDriver();
		driver.locateOne(By.xpath("//*[@name=\"critical_proxy\"]/option[\""+ critical +"\"]")).click();
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirmation", "false");

		super.clickOnSubmit("Create");

		super.checkErrorsExist();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		
		super.signIn("patron1", "patron1");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		super.signOut();
	}
}
