<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form >
		<acme:input-textbox code="epicure.memorandum.form.label.moment" path="moment" readonly = "true"/>
		
		<jstl:if test="${command != 'create'}">
			<acme:input-textbox code="epicure.memorandum.form.label.sequenceNumber" path="sequenceNumber" readonly = "true"/>
		</jstl:if>
		
		<acme:input-textarea code="epicure.memorandum.form.label.report" path="report" readonly = "${readOnly}"/>
		<acme:input-textbox code="epicure.memorandum.form.label.info" path="info" readonly = "${readOnly}"/>
		
		<jstl:if test="${command != 'create'}">
			<acme:input-textbox code="epicure.memorandum.form.label.chef" path="chef.userAccount.username" readonly = "${readOnly}"/>
		</jstl:if>
		
		<acme:input-textbox code="epicure.memorandum.form.label.fineDish" path="fineDish.code" readonly="${readOnly}"/>
		
		<acme:hidden-data path="confirmation"/>
		
			
		
		<jstl:if test="${command == 'create'}">
			<acme:input-checkbox code="epicure.memoranda.form.create.confirm" path="confirmed"/>
			<acme:submit code="epicure.memorandum.button.submit" action="/epicure/memoranda/create"/>
		</jstl:if>
		
</acme:form>
