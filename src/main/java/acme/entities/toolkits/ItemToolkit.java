
package acme.entities.toolkits;

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
public class ItemToolkit extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Positive
	protected Integer			quantity;

	// Relationships ----------------------------------------------------------

	@ManyToOne(optional = false)
	@NotNull
	protected Item				item;

	@ManyToOne(optional = false)
	@NotNull
	protected Toolkit			toolkit;

}
