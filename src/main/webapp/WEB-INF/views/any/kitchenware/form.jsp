<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="any.kitchenware.form.label.code" path="code" />
	<acme:input-textbox code="any.kitchenware.form.label.name" path="name" />
	<acme:input-textarea code="any.kitchenware.form.label.description" path="description" />
	<acme:input-money code="any.kitchenware.form.label.retail-price" path="retailPrice" />
	<acme:input-money code="any.kitchenware.form.label.retail-price" path="retailPriceConverted" />
	
	<acme:input-url code="any.kitchenware.form.label.info" path="info"/>

	<acme:input-select code="any.kitchenware.form.label.kitchenware-type" path="wareType">
		<acme:input-option code="INGREDIENT" value="INGREDIENT" selected="${wareType == 'INGREDIENT'}"/>
		<acme:input-option code="KITCHEN_UTENSIL" value="KITCHEN_UTENSIL" selected="${wareType == 'KITCHEN_UTENSIL'}" />
	</acme:input-select>
	
	<acme:input-select code="any.kitchenware.list.label.published" path="published">
		<acme:input-option code="TRUE" value="TRUE" selected="${published}"/>
		<acme:input-option code="FALSE" value="FALSE" selected="${!published}"/>
	</acme:input-select>


</acme:form> 