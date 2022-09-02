
<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.component.list.label.code" path="code"
		width="20%" />
	<acme:list-column code="any.component.list.label.name" path="name"
		width="20%" />
	<acme:list-column code="any.component.list.label.tech" path="technology"
		width="20%" />
	<acme:list-column code="any.component.list.label.price" path="retailPrice"
		width="20%" />
</acme:list>
