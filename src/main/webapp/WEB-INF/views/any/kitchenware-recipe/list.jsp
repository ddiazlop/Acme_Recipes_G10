<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="any.kitchenware.list.label.code" path="kitchenware.code"
		width="20%" />
	<acme:list-column code="any.kitchenware.list.label.name" path="kitchenware.name"
		width="20%" />
	<acme:list-column code="any.kitchenware.list.label.quantity" path="quantity"
		width="20%" />
	<acme:list-column code="any.kitchenware.list.label.unitType" path="unitType"
		width="20%" />
	<acme:list-column code="any.kitchenware.list.label.price" path="price"
		width="20%" />
</acme:list>

