package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.datatypes.StatData;
import acme.entities.fineDish.DishStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EpicureDashboard implements Serializable{

		private static final long					serialVersionUID	= 1L;
		
		Integer											numDishesProposed;
		Integer											numDishesAccepted;
		Integer											numDishesDenied;
		
		
		
		//StatData=[Sum, Average, Desviation, Minimum, Maximum] by currency

		Map<Pair<DishStatus, String>, StatData>	dishesBudgetData;
}
