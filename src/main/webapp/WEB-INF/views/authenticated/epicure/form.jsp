<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textarea
		code="authenticated.epicure.form.label.organisation"
		path="organisation" />
	<acme:input-textarea
		code="authenticated.epicure.form.label.assertion" path="assertion" />
	<acme:input-url
		code="authenticated.epicure.form.label.link" path="link" />

	<acme:submit test="${command == 'create'}"
		code="authenticated.epicure.form.button.create"
		action="/authenticated/epicure/create" />
		
</acme:form>
