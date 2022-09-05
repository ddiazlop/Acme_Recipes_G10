<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
		<jstl:if test="${command != 'create' }"> 
		<acme:input-textbox code="epicure.memorandum.form.label.moment" path="moment" readonly = "true"/>
		<acme:input-textbox code="epicure.memorandum.form.label.sequenceNumber" path="sequenceNumber" readonly = "true"/>
		<acme:input-textarea code="epicure.memorandum.form.label.report" path="report" readonly = "true"/>
		<acme:input-textbox code="epicure.memorandum.form.label.info" path="info" readonly = "true"/>
		<acme:input-textarea code="epicure.memorandum.form.label.chef" path="chef" readonly = "true"/>
		</jstl:if>
		
		<jstl:if test="${command == 'create' }"> 
		<acme:input-textbox code="epicure.memorandum.form.label.moment" path="moment"/>
		<acme:input-textbox code="epicure.memorandum.form.label.sequenceNumber" path="sequenceNumber"/>
		<acme:input-textarea code="epicure.memorandum.form.label.report" path="report"/>
		<acme:input-textbox code="epicure.memorandum.form.label.info" path="info"/>
		<acme:input-textarea code="epicure.memorandum.form.label.chef" path="chef" readonly = "true"/>
		</jstl:if>
		<acme:submit test="${command == 'create'}" code="epicure.memorandum.form.button.create" action="/epicure/memoranda/create" />
		
		
</acme:form>
