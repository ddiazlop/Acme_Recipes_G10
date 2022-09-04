package acme.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SpamDetector2 {
	
	private final Map<String, Double>	spamWeightsMap	= new HashMap<String, Double>();
	private final Double						threshold;

	public SpamDetector2(final String spamTuple, final Double threshold) {
		//sex:0.1,sexo:0.1,hard core:0.1,viagra:0.1,cialis:0.1,sexy:0.05,nigeria:0.05,you’ve won:0.05,has ganado:0.05
		final String[] tuples = spamTuple.trim().split(",");
		for (int i = 0; i < tuples.length; i++) {
			final String[] tuple = tuples[i].split(":");
			this.spamWeightsMap.put(tuple[0].trim(), Double.valueOf(tuple[1].trim()));
		}
		this.threshold = threshold;
	}

	public boolean stringHasManySpam(final String stringToTest) {
		Double totalWeight = 0.;
		Integer numberOWords = this.countWords2(stringToTest);
		for (final Entry<String, Double> entry : this.spamWeightsMap.entrySet()) {
			final String key = entry.getKey();
			final Double val = entry.getValue();
			if (stringToTest.contains(key)) {
				Integer ocurrences = (numberOWords==1)?1:this.countSubstring2(stringToTest, key);
				if (key.split(" ").length>=1) {
					ocurrences = ocurrences*2;
					numberOWords -=1;
				}
				totalWeight += ocurrences * val;
			}
		}
		
		return (totalWeight / numberOWords >= this.threshold);
	}

	private Integer countSubstring2(final String string, final String substring) {
		
		return string.split(substring, -1).length -1;
	}

	private Integer countWords2(final String string) {
		return string.split(" ").length;
	}

}
