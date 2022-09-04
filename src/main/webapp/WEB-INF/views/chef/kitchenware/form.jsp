<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:message code="chef.kitchenware.form.message.with-recipe-associated"/>
<acme:form>

	<acme:input-textbox code="chef.kitchenware.form.label.code" path="code" readonly="${readOnly}"  placeholder="AB:CDE-123, ABC-123..."/>	
	<acme:input-textbox code="chef.kitchenware.form.label.name" path="name" />
	<acme:input-textarea code="chef.kitchenware.form.label.description" path="description" />
	<acme:input-select code="chef.kitchenware.form.label.wareType" path="wareType" readonly="${readOnly}" >
		<acme:input-option code="chef.kitchenware.form.label.ingredient" value="${IngredientType}" selected="${wareType == 'INGREDIENT'}"/>
		<acme:input-option code="chef.kitchenware.form.label.kitchen-utensil" value="${KitchenUtensilType}" selected="${wareType == 'KITCHEN_UTENSIL'}" />
	</acme:input-select>
	<acme:input-url code="chef.kitchenware.form.label.info" path="info"/>
	<acme:input-money code="chef.kitchenware.form.label.retailPrice" path="retailPrice" />  
	<acme:input-money code="chef.kitchenware.form.label.retailPriceConverted" path="retailPriceConverted" readonly="true" />
	
	<acme:input-select code="chef.kitchenware.form.label.status" path="published" readonly ="true" >
		<acme:input-option code="chef.kitchenware.form.label.published" value="TRUE" selected="${published}"/>
		<acme:input-option code="chef.kitchenware.form.label.not-published" value="FALSE" selected="${!published}"/>
	</acme:input-select>
	
	<jstl:if test="${!published && command != 'create'}">
			<acme:submit code="chef.kitchenware.form.submit.update" action="/chef/kitchenware/update"/>
			<acme:submit code="chef.kitchenware.form.submit.publish" action="/chef/kitchenware/publish"/>
			<acme:submit code="chef.kitchenware.form.submit.delete" action="/chef/kitchenware/delete"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:submit code="chef.kitchenware.form.submit.create" action="/chef/kitchenware/create"/>
	</jstl:if>
	

</acme:form>