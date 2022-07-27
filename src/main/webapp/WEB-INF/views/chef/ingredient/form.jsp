<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="chef.ingredient.form.label.code" path="code" />
	<acme:input-textbox code="chef.ingredient.form.label.name" path="name" />
	<acme:input-textarea code="chef.ingredient.form.label.description" path="description" />
	<acme:input-money code="chef.ingredient.form.label.retail-price" path="retailPrice" />
	<acme:input-url code="chef.ingredient.form.label.info" path="info"/>
	<jstl:if test="${command == 'show' || command == 'publish'}">
		<acme:input-textbox code="chef.ingredient.form.label.published" path="published"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:submit code="chef.ingredient.form.submit.create" action="/chef/ingredient/create" />
	</jstl:if>
	

</acme:form>