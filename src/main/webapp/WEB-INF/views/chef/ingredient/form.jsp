<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
		<acme:input-textbox code="chef.ingredient.form.label.name" path="name" readonly = "true"/>
		<acme:input-textbox code="chef.ingredient.form.label.code" path="code" readonly = "true"/>
		<acme:input-textbox code="chef.ingredient.form.label.description" path="description" readonly = "true"/>
		<acme:input-textbox code="chef.ingredient.form.label.retailPrice" path="retailPrice" readonly = "true"/>
		<acme:input-textbox code="chef.ingredient.form.label.info" path="info" readonly = "true"/>
</acme:form>