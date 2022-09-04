package acme.entities.recipes;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class KitchenwareRecipe extends AbstractEntity {
	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Positive
	protected Double			quantity;
	
	protected UnitType          unitType;

	// Relationships ----------------------------------------------------------

	@ManyToOne(optional = false)
	@NotNull
	protected Kitchenware				kitchenware;

	@ManyToOne(optional = false)
	@NotNull
	protected Recipe			recipe;
}
