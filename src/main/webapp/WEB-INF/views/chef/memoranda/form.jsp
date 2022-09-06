<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="chef.memoranda.form.label.fineDishId" path="fineDishId" readonly = "true"/>
	<acme:input-textbox code="chef.memoranda.form.label.sequenceNumber" path="sequenceNumber" readonly = "true"/>
	<acme:input-textbox code="chef.memoranda.form.label.fineDishCode" path="fineDishCode" readonly = "true"/>
	<acme:input-textarea code="chef.memoranda.form.label.report" path="report"/>
	<acme:input-textbox code="chef.memoranda.form.label.moment" path="moment" readonly = "true"/>
	<acme:input-textbox code="chef.memoranda.form.label.info" path="info"/>
	<jstl:if test="${command == 'create'}">
		<acme:input-checkbox code="chef.memoranda.form.label.confirmation" path="confirmation"/>
			<acme:submit code="chef.memoranda.form.label.button.create"
				action="/chef/memoranda/create" />
	</jstl:if>
	
</acme:form>