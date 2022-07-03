/*
 * FavouriteLinkTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class FavouriteLinkTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	
	@ParameterizedTest
	@CsvFileSource(resources = "/favorite/links.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final String menuSubOption, final String url) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", menuSubOption);
		super.checkCurrentUrl(url);
	}
	
	// Ancillary methods ------------------------------------------------------ 
}
