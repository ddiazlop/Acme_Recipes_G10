<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.toolkit.form.label.code" path="code" width="20%"/>
	<acme:list-column code="inventor.toolkit.form.label.title" path="title" width="20%"/>
	<acme:list-column code="inventor.toolkit.form.label.description" path="description" width="20%"/>
	<acme:list-column code="inventor.toolkit.form.label.price" path="price" width="10%"/>
	<acme:list-column code="inventor.toolkit.form.label.published" path="state" width="20%"/>
</acme:list>

<acme:button code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>
