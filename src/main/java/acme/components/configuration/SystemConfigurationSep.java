package acme.components.configuration;

import javax.persistence.Entity;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class SystemConfigurationSep extends AbstractEntity{

// TODO DEPRECATED, NEEDS TO BE CHANGED
//		// Serialisation identifier -----------------------------------------------
//
//		protected static final long	serialVersionUID	= 1L;
//
//		// Attributes -------------------------------------------------------------
//		@NotBlank
//		protected String			systemCurrency;
//
//		@NotBlank
//		@Pattern(regexp = "^[A-Z]{3}(,[A-Z]{3})*$")
//		protected String			acceptedCurrencies;
//
////		@NotBlank
////		protected Map<String,Double>	SpamTuple;
//
//		//Supuestamente el rango es de 0.00 a 1.00, por comodidad de 0 a 100
//		@Range(max = 100, min = 0)
//		@NotNull
//		protected Double			SpamThreshold;
//
//		// Derived attributes -----------------------------------------------------
//
//		// Relationships ----------------------------------------------------------

}
