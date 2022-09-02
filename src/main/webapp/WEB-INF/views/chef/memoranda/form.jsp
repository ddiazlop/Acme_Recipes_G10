<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.memoranda.form.label.sequenceNumber" path="sequenceNumber"/>
	<acme:input-textbox code="chef.memoranda.form.label.fineDishCode" path="fineDishCode"/>
	<acme:input-textarea code="chef.memoranda.form.label.report" path="report"/>
	<acme:input-textbox code="chef.memoranda.form.label.moment" path="moment"/>
	<acme:input-textbox code="chef.memoranda.form.label.info" path="info"/>
	<acme:input-textbox code="chef.memoranda.form.label.epicureUsername" path="epicureUsername"/>
</acme:form>