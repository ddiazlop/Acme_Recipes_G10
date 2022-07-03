
package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.datatypes.StatData;
import acme.entities.patronages.PatronageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboard implements Serializable {

	private static final long						serialVersionUID	= 1L;

	Integer											numComponents;
	Integer											numTools;
	Integer											numPatronageRequested;
	Integer											numPatronageAccepted;
	Integer											numPatronageDenied;

	//StatData=[Sum, Average, Desviation, Minimum, Maximum]

	Map<String, StatData>							componentsDataByCurrency;

	Map<Pair<String, String>, StatData>				componentsDataByTechnology;

	Map<String, StatData>							toolsDataByCurrency;

	Map<Pair<String, String>, StatData>				toolsDataByTechnology;

	Map<Pair<PatronageStatus, String>, StatData>	patronageBudgetData;

}
