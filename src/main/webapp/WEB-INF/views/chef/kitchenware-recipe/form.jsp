<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.name" path="kitchenware.name"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.code" path="kitchenware.code"/>
	<acme:input-textarea code="chef.kitchenwareRecipe.form.label.kitchenware.description" path="kitchenware.description"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.wareType" path="kitchenware.wareType"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.retailPrice" path="kitchenware.retailPrice"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.info" path="kitchenware.info"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.published" path="kitchenware.published"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.quantity" path="quantity"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.unitType" path="unitType"/>
	
</acme:form>


