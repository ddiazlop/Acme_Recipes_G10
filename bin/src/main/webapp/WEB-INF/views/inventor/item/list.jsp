<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.component.list.label.code" path="code"
		width="20%" />
	<acme:list-column code="inventor.component.list.label.name" path="name"
		width="20%" />
	<acme:list-column code="inventor.component.list.label.tech" path="technology"
		width="20%" />
	<acme:list-column code="inventor.component.list.label.price" path="retailPrice"
		width="20%" />
</acme:list>

<jstl:choose>
	<jstl:when test="${acme:anyOf(command, 'list-component')}">
		<acme:button code="inventor.item.form.button.create" action="/inventor/item/create-component"/>
	</jstl:when>
	<jstl:otherwise>
		<acme:button code="inventor.item.form.button.create" action="/inventor/item/create-tool"/>
	</jstl:otherwise>
</jstl:choose>

