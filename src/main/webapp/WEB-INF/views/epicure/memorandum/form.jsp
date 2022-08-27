<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
		<acme:input-textbox code="epicure.memorandum.form.label.moment" path="moment" readonly = "true"/>
		<acme:input-textbox code="epicure.memorandum.form.label.sequenceNumber" path="sequenceNumber" readonly = "true"/>
		<acme:input-textbox code="epicure.memorandum.form.label.report" path="report" readonly = "true"/>
		<acme:input-textbox code="epicure.memorandum.form.label.info" path="info" readonly = "true"/>
</acme:form>
