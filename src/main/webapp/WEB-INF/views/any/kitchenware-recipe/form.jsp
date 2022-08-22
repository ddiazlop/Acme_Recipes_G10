<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
<acme:input-textbox code="chef.recipe.form.label.code" path="code"/>
	<acme:input-textbox code="chef.recipe.form.label.heading" path="heading"/>
	<acme:input-textarea code="chef.recipe.form.label.description" path="description"/>
	<acme:input-textarea code="chef.recipe.form.label.preparationNotes" path="preparationNotes"/>
	<acme:input-textbox code="chef.recipe.form.label.chef" path="chef"/>
	<acme:input-money code="chef.recipe.form.label.price" path="price"/>
	<acme:input-url code="chef.recipe.form.label.info" path="info"/>
</acme:form>

<acme:button code="chef.recipe.form.label.content" action="/any/kitchenware-recipe/list?recipeId=${id}"/>

