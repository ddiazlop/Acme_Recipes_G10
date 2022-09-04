<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox  readonly="${readOnly}" placeholder= "Code structure like AA:ABC-123 or AAA-111" code="chef.recipes.form.label.code" path="code"/>
	<acme:input-textbox code="chef.recipes.form.label.heading" path="heading"/>
	<acme:input-textarea code="chef.recipes.form.label.description" path="description"/>
	<acme:input-textarea code="chef.recipes.form.label.preparationNotes" path="preparationNotes"/>
	<acme:input-url code="chef.recipes.form.label.info" path="info"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, delete, update, publish')}">
			<acme:input-textbox readonly="true" code="chef.recipes.form.label.chef" path="chef"/>
			<acme:input-textbox readonly="true" code="chef.recipes.form.label.price" path="price"/>
		</jstl:when> 
	</jstl:choose>	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="chef.recipes.form.button.delete" action="/chef/recipe/delete"/>
			<jstl:if test="${ableToPublish==true}">
				<acme:submit code="chef.recipes.form.button.publish" action="/chef/recipe/publish"/>
			</jstl:if>
			
			<acme:submit code="chef.recipes.form.button.update" action="/chef/recipe/update"/>
			<acme:button code="chef.recipes.form.button.add-content" action="/chef/kitchenware-recipe/create?recipeId=${id}"/>
		
		</jstl:when>
		<jstl:when test="${command=='create'}">
			<acme:submit code="chef.recipes.form.button.create" action="/chef/recipe/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
<br>
<br>
<jstl:if test = "${command != 'create'}">
	<acme:button code="chef.recipes.form.button.kitchenwareRecipe" action="/chef/kitchenware-recipe/list?recipeId=${id}"/>
</jstl:if>
