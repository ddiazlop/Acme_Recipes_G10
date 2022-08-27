<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="inventor.patronage-report.form.label.moment"
		path="moment" readonly="true" />
	<acme:input-textbox
		code="inventor.patronage-report.form.label.memorandum"
		path="memorandum" />
	<acme:input-textbox code="inventor.patronage-report.form.label.info"
		path="info" />
		<acme:input-textbox
		code="inventor.patronage-report.form.label.sequenceNumber"
		path="sequenceNumber" readonly="true" />
		
		
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
		<acme:input-checkbox code="inventor.patronage-report.form.label.confirmation" path="confirmation"/>
			<acme:submit code="inventor.patronage-report.form.button.create"
				action="/inventor/patronage-report/create?patronageId=${patronageId}"/>
		</jstl:when>
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox
			code="inventor.patronage-report.form.label.patronageId"
			path="patronageId" readonly="true" />
		</jstl:when>
	</jstl:choose>

</acme:form>