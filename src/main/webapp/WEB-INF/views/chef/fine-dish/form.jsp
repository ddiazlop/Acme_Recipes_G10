<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.fineDish.label.form.code" path="code"/>
	<acme:input-textarea code="chef.fineDish.label.form.request" path="request"/>
	<acme:input-textbox code="chef.fineDish.label.form.budget" path="budget"/>
	<acme:input-textbox code="chef.fineDish.label.form.creationDate" path="creationDate"/>
	<acme:input-textbox code="chef.fineDish.label.form.startDate" path="startDate"/>
	<acme:input-textbox code="chef.fineDish.label.form.endDate" path="endDate"/>
	<acme:input-textarea code="chef.fineDish.label.form.info" path="info"/>
	<acme:input-textbox code="chef.fineDish.label.form.epicureName" path="epicureName" readonly="true"/>
	<acme:input-textbox code="chef.fineDish.label.form.epicureUserName" path="epicureUserName" readonly="true"/>
	<acme:input-textbox code="chef.fineDish.label.form.epicureOrganisation" path="epicureOrganisation" readonly="true"/>
	<acme:input-textbox code="chef.fineDish.label.form.epicureAssertion" path="epicureAssertion" readonly="true"/>
	<acme:input-textbox code="chef.fineDish.label.form.epicureLink" path="epicureLink" readonly="true"/>

</acme:form>