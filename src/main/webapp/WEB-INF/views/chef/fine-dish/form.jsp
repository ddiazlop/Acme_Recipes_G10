<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command == 'show'}">
		<acme:input-textbox code="chef.fineDish.label.form.code" path="code" readonly="true"/>
		<acme:input-textarea code="chef.fineDish.label.form.request" path="request" readonly="true"/>
		<acme:input-textbox code="chef.fineDish.label.form.budget" path="budget" readonly="true"/>
		<acme:input-textbox code="chef.fineDish.label.form.creationDate" path="creationDate" readonly="true"/>
		<acme:input-textbox code="chef.fineDish.label.form.startDate" path="startDate" readonly="true"/>
		<acme:input-textbox code="chef.fineDish.label.form.endDate" path="endDate" readonly="true"/>
		<acme:input-textarea code="chef.fineDish.label.form.info" path="info" readonly="true"/>
		<acme:input-textarea code="chef.fineDish.label.form.status" path="status" readonly="true"/>
		<acme:input-textbox code="chef.fineDish.label.form.epicureName" path="epicureName" readonly="true"/>
		<acme:input-textbox code="chef.fineDish.label.form.epicureUserName" path="epicureUserName" readonly="true"/>
		<acme:input-textbox code="chef.fineDish.label.form.epicureOrganisation" path="epicureOrganisation" readonly="true"/>
		<acme:input-textbox code="chef.fineDish.label.form.epicureAssertion" path="epicureAssertion" readonly="true"/>
		<acme:input-textbox code="chef.fineDish.label.form.epicureLink" path="epicureLink" readonly="true"/>
		<acme:button code="chef.fineDish.form.button.memoranda.list" action="/chef/memoranda/list-from-fine-dish?fineDishId=${id}"/>
		<acme:button code="chef.fineDish.form.button.memoranda.create" action="/chef/memoranda/create?fineDishId=${id}"/>
	</jstl:if>
	
	<jstl:if test="${status.name() == 'PROPOSED'}">
		<acme:submit code="chef.fineDish.form.button.accept" action="/chef/fine-dish/change-status?operation=accept"/>		
		<acme:submit code="chef.fineDish.form.button.deny" action="/chef/fine-dish/change-status?operation=deny"/>		
	</jstl:if>
</acme:form>