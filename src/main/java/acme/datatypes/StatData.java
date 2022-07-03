
package acme.datatypes;

import javax.persistence.Tuple;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatData {

	Money	sum;
	Money	average;
	Money	desviation;
	Money	minimum;
	Money	maximum;


	public StatData(final Money sum, final Money average, final Money desviation, final Money minimum, final Money maximum) {
		this.sum = sum;
		this.average = average;
		this.desviation = desviation;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public StatData() {
	}

	public static StatData of(final Tuple st, final String currency) {
		final StatData res = new StatData();

		if (st.get(0) != null) {
			final Money sum = new Money();
			sum.setCurrency(currency);
			final Money avg = new Money();
			avg.setCurrency(currency);
			final Money desv = new Money();
			desv.setCurrency(currency);
			final Money min = new Money();
			min.setCurrency(currency);
			final Money max = new Money();
			max.setCurrency(currency);
			sum.setAmount(Double.valueOf(st.get(0).toString()));
			avg.setAmount(Double.valueOf(st.get(1).toString()));
			desv.setAmount(Double.valueOf(st.get(2).toString()));
			min.setAmount(Double.valueOf(st.get(3).toString()));
			max.setAmount(Double.valueOf(st.get(4).toString()));
			res.setSum(sum);
			res.setAverage(avg);
			res.setDesviation(desv);
			res.setMinimum(min);
			res.setMaximum(max);
		} else {
			final Money mo = new Money();
			mo.setCurrency(currency);
			mo.setAmount(0.);
			res.setSum(mo);
			res.setAverage(mo);
			res.setDesviation(mo);
			res.setMinimum(mo);
			res.setMaximum(mo);
		}
		return res;
	}

}
