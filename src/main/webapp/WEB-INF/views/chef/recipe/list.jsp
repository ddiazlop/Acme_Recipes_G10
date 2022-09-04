<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.recipes.list.label.code" path="code"/>
	<acme:list-column code="chef.recipes.list.label.heading" path="heading"/>
	<acme:list-column code="chef.recipes.list.label.description" path="description"/>
	<acme:list-column code="chef.recipes.list.label.price" path="price" />
	<acme:list-column code="chef.recipes.list.label.state" path="state"/>
</acme:list>

<acme:button code="chef.recipes.form.button.create" action="/chef/recipe/create"/>