<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="chef.kitchenware.form.label.code" path="code" />
	<acme:input-textbox code="chef.kitchenware.form.label.name" path="name" />
	<acme:input-textarea code="chef.kitchenware.form.label.description" path="description" />
	<acme:input-money code="chef.kitchenware.form.label.retailPrice" path="retailPrice" />
	<acme:input-url code="chef.kitchenware.form.label.info" path="info"/>
	<acme:input-select code="chef.kitchenware.form.label.wareType" path="wareType">
		<acme:input-option code="chef.kitchenware.form.label.ingredient" value="INGREDIENT" selected="${wareType == 'INGREDIENT'}"/>
		<acme:input-option code="chef.kitchenware.form.label.kitchen-utensil" value="KITCHEN_UTENSIL" selected="${wareType == 'KITCHEN_UTENSIL'}" />
	</acme:input-select>
	
	<acme:input-select code="chef.kitchenware.form.label.status" path="published">
		<acme:input-option code="chef.kitchenware.form.label.published" value="TRUE" selected="${published}"/>
		<acme:input-option code="chef.kitchenware.form.label.not-published" value="TRUE" selected="${!published}"/>
	</acme:input-select>
	

</acme:form>