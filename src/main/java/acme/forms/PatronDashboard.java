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
public class PatronDashboard implements Serializable {

	private static final long					serialVersionUID	= 1L;
	
	Integer											numPatronageProposed;
	Integer											numPatronageAccepted;
	Integer											numPatronageDenied;
	
	Map<Pair<PatronageStatus, String>, StatData>	patronageBudgetData;
}
