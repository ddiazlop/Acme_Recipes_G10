<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.kitchenwareRecipe.list.label.name" path="kitchenware.name"/>
	<acme:list-column code="chef.kitchenwareRecipe.list.label.code" path="kitchenware.code"/>
	<acme:list-column code="chef.kitchenwareRecipe.list.label.wareType" path="kitchenware.wareType"/>
	<acme:list-column code="chef.kitchenwareRecipe.list.label.quantity" path="quantity"/>
	<acme:list-column code="chef.kitchenwareRecipe.list.label.unitType" path="unitType"/>
</acme:list>