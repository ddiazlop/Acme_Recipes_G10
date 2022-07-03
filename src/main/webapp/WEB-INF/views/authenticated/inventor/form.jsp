<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textarea
		code="authenticated.inventor.form.label.company"
		path="company" />
	<acme:input-textarea
		code="authenticated.inventor.form.label.statement" path="statement" />
	<acme:input-textarea
		code="authenticated.inventor.form.label.info" path="info" placeholder="http://info.com"/>

	<acme:submit test="${command == 'create'}"
		code="authenticated.inventor.form.button.create"
		action="/authenticated/inventor/create" />
	<acme:submit test="${command == 'update'}"
		code="authenticated.inventor.form.button.update"
		action="/authenticated/inventor/update" />
</acme:form>
