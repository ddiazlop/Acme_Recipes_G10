<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.code" path="kitchenware.code"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.name" path="kitchenware.name"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.quantity" path="quantity"/>
	
	<jstl:if test="${wareType == 'INGREDIENT'}">
	<acme:input-select code="chef.kitchenwareRecipe.form.label.unitType" path="unitType">
		<acme:input-option code="GRAM" value="GRAM" selected="${unitType == 'GRAM'}"/>
		<acme:input-option code="KILO" value="KILO" selected="${unitType == 'KILO'}"/>
		<acme:input-option code="CM3" value="CM3" selected="${unitType == 'CM3'}"/>
		<acme:input-option code="LITER" value="LITER" selected="${unitType == 'LITER'}"/>
		<acme:input-option code="SPOON" value="SPOON" selected="${unitType == 'SPOON'}"/>
	</acme:input-select>
	</jstl:if>
	
	<acme:input-textarea code="chef.kitchenwareRecipe.form.label.kitchenware.description" path="kitchenware.description"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.retailPrice" path="retailPrice"/>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.retailPrice-original" path="kitchenware.retailPrice"/>
	<jstl:if test="${wareType == 'INGREDIENT'}">
		<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.retailPrice-total" path="totalPrice"/>
	</jstl:if>
	<acme:input-textbox code="chef.kitchenwareRecipe.form.label.kitchenware.info" path="kitchenware.info"/>
	
	<acme:input-select code="chef.kitchenwareRecipe.form.label.kitchenware.wareType" path="wareType">
		<acme:input-option code="INGREDIENT" value="INGREDIENT" selected="${wareType == 'INGREDIENT'}"/>
		<acme:input-option code="KITCHEN_UTENSIL" value="KITCHEN_UTENSIL" selected="${wareType == 'KITCHEN_UTENSIL'}" />
	</acme:input-select>
	
	<acme:input-select code="chef.kitchenwareRecipe.form.label.kitchenware.published" path="published">
		<acme:input-option code="TRUE" value="TRUE" selected="${published}"/>
		<acme:input-option code="FALSE" value="FALSE" selected="${!published}"/>
	</acme:input-select>
	
</acme:form>


