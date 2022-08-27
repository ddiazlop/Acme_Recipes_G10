<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="any.component.list.label.quantity" path="quantity"
		width="20%" />
	<acme:list-column code="any.component.list.label.name" path="item.name"
		width="20%" />
	<acme:list-column code="any.component.list.label.itemType" path="item.itemType"
		width="20%" />
	<acme:list-column code="any.component.list.label.code" path="item.code"
		width="20%" />
	<acme:list-column code="any.component.list.label.tech" path="item.technology"
		width="20%" />
	<acme:list-column code="any.component.list.label.price" path="item.retailPrice"
		width="20%" />
</acme:list>

