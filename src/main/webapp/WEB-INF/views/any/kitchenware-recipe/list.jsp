<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.recipe.form.label.code" path="code" width="20%"/>
	<acme:list-column code="chef.recipe.form.label.heading" path="heading" width="20%"/>
	<acme:list-column code="chef.recipe.form.label.description" path="description" width="20%"/>
	<acme:list-column code="chef.recipe.form.label.price" path="price" width="10%"/>
</acme:list>