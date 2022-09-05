<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.fineDish.label.form.code" path="code"/>
	<acme:input-textarea code="chef.fineDish.label.form.request" path="request"/>
	<acme:input-select code="chef.fineDish.form.label.status" path="status" readonly ="true" >
		<acme:input-option code="chef.fineDish.form.label.status.PROPOSED" value="${PROPOSED}" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="chef.fineDish.form.label.status.ACCEPTED" value="${ACCEPTED}" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="chef.fineDish.form.label.status.DENIED" value="${DENIED}" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="chef.fineDish.label.form.budget" path="budget"/>
	<acme:input-textbox code="chef.fineDish.label.form.creationDate" path="creationDate"/>
	<acme:input-textbox code="chef.fineDish.label.form.startDate" path="startDate"/>
	<acme:input-textbox code="chef.fineDish.label.form.endDate" path="endDate"/>
	<acme:input-textarea code="chef.fineDish.label.form.info" path="info"/>
	
	<acme:input-select code="epicure.fineDish.form.label.is-published" path="published" readonly ="true" >
		<acme:input-option code="chef.fineDish.form.label.published" value="TRUE" selected="${published}"/>
		<acme:input-option code="chef.fineDish.form.label.not-published" value="FALSE" selected="${!published}"/>
	</acme:input-select>
	<acme:input-textbox code="chef.fineDish.label.form.epicureName" path="epicureName" readonly="true"/>
	<acme:input-textbox code="chef.fineDish.label.form.epicureUserName" path="epicureUserName" readonly="true"/>
	<acme:input-textbox code="chef.fineDish.label.form.epicureOrganisation" path="epicureOrganisation" readonly="true"/>
	<acme:input-textbox code="chef.fineDish.label.form.epicureAssertion" path="epicureAssertion" readonly="true"/>
	<acme:input-textbox code="chef.fineDish.label.form.epicureLink" path="epicureLink" readonly="true"/>

</acme:form>