<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="epicure.fine-dish.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.request" path="request" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.status" path="status" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.budget" path="budget" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.creationDate" path="creationDate" readonly="true"/>	
	<acme:input-textbox code="epicure.fine-dish.form.label.startDate" path="startDate" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.endDate" path="endDate" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.info" path="info" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.chef.username" path="chef.username" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.chef.organisation" path="chef.organisation" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.chef.assertion" path="chef.assertion" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.chef.link" path="chef.link" readonly="true"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, delete, update, publish')}">
			<acme:input-textbox readonly="true" code="chef.recipes.form.label.chef" path="chef"/>
			<acme:input-textbox readonly="true" code="chef.recipes.form.label.price" path="price"/>
		</jstl:when> 
	</jstl:choose>	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="epicure.fine-dish.form.button.delete" action="/epicure/fine-dish/delete"/>
			<jstl:if test="${ableToPublish==true}">
				<acme:submit code="epicure.fine-dish.form.button.publish" action="/epicure/fine-dish/publish"/>
			</jstl:if>
			
			<acme:submit code="epicure.fine-dish.form.button.update" action="/epicure/fine-dish/update"/>
			>
		
		</jstl:when>
		<jstl:when test="${command=='create'}">
			<acme:submit code="epicure.fine-dish.form.button.create" action="/epicure/fine-dish/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
<br>
<br>
<jstl:if test = "${command != 'create'}">
	<acme:button code="epicure.fine-dish.form.button.kitchenwareRecipe" action="/epicure/kitchenware-recipe/list?recipeId=${id}"/>
</jstl:if>

	