<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	

		<acme:input-textbox code="any.chirp.form.label.title" path="title" />
		<acme:input-textbox code="any.chirp.form.label.author" path="author" />
		<acme:input-textarea code="any.chirp.form.label.body" path="body" />
		<acme:input-email code="any.chirp.form.label.email" path="email" placeholder="someone@email.com" />
		<acme:input-checkbox code="any.chirp.form.label.confirmation" path="confirmation"/>
		

		<acme:submit code="any.chirp.form.button.create"
			action="/any/chirp/create" />
	

</acme:form>