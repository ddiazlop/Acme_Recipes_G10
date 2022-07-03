package acme.components;

import java.util.Arrays;

import acme.components.configuration.SystemConfiguration;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;

public class SpamDetector {

	private final Double strongSpamThreshold;
	private final Double weakSpamThreshold;
	private final String[] strongSpamWords;
	private final String[] weakSpamWords;
	
	
	
	public static SpamDetector fromRepository(final AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository) {
		final SystemConfiguration sysConfig = administratorSystemConfigurationRepository.findSystemConfiguration();
		final String[] strongWords = sysConfig.getStrongSpamTerms().split(",");
		final String[] weakWords = sysConfig.getWeakSpamTerms().split(",");
		final Double strongThreshold = sysConfig.getWeakSpamThreshold();
		final Double weakThreshold = sysConfig.getWeakSpamThreshold();
		return new SpamDetector(strongThreshold, weakThreshold, strongWords, weakWords);
	}
	
	public SpamDetector(final Double strongSpamThreshold, final Double weakSpamThreshold, final String[] strongSpamWords, final String[] weakSpamWords) {
		this.strongSpamThreshold = strongSpamThreshold;
		this.weakSpamThreshold = weakSpamThreshold;
		this.strongSpamWords = strongSpamWords;
		this.weakSpamWords = weakSpamWords;
	}
	
	public Boolean stringHasSpam(final String string) {
		final int countStrongSpam = Arrays.stream(this.strongSpamWords).mapToInt(w-> this.countSubstring(string, w)).sum();
		final int countWeakSpam = Arrays.stream(this.weakSpamWords).mapToInt(w-> this.countSubstring(string, w)).sum();
		final Double selectedThreshold = (countStrongSpam >=1)?this.strongSpamThreshold:this.weakSpamThreshold;
		final Integer numberOfWords = this.countWords(string);
		return (countStrongSpam + countWeakSpam)/numberOfWords > selectedThreshold/100;
	}
	
	private Integer countSubstring(final String string, final String substring) {
		return string.split(substring,-1).length-1;
	}
	
	private Integer countWords(final String string) {
		return string.split(" ").length;
	}
	
	
	public Boolean stringHasNoSpam(final String string) {
		return !this.stringHasSpam(string);
	}
	
}


