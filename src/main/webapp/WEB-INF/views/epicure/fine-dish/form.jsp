<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:message code="epicure.fine-dish.form.message.with-memoranda-associated"/>
<acme:form>
	<acme:input-textbox code="epicure.fine-dish.form.label.code" path="code" readonly="${readOnly}"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.request" path="request" />
	<acme:input-select code="epicure.fineDish.form.label.status" path="status" readonly ="true" >
		<acme:input-option code="epicure.fineDish.form.label.status.PROPOSED" value="${PROPOSED}" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="epicure.fineDish.form.label.status.ACCEPTED" value="${ACCEPTED}" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="epicure.fineDish.form.label.status.DENIED" value="${DENIED}" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="epicure.fine-dish.form.label.budget" path="budget"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.creationDate" path="creationDate" readonly="true" />	
	<acme:input-textbox code="epicure.fine-dish.form.label.startDate" path="startDate"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.endDate" path="endDate"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.info" path="info" />
	<acme:input-select code="epicure.fineDish.form.label.is-published" path="published" readonly ="true" >
		<acme:input-option code="epicure.fineDish.form.label.published" value="TRUE" selected="${published}"/>
		<acme:input-option code="epicure.fineDish.form.label.not-published" value="FALSE" selected="${!published}"/>
	</acme:input-select>
	<jstl:if test="${command == 'create'}">
		<acme:input-textbox code="epicure.fine-dish.form.label.chefUsername" path="chefUsername" />
	</jstl:if>
	<jstl:if test="${command != 'create'}">
		<acme:input-textbox code="epicure.fine-dish.form.label.chefUsername" path="chef.username" />
		<acme:input-textbox code="epicure.fine-dish.form.label.chef.organisation" path="chef.organisation" readonly="true"/>
		<acme:input-textbox code="epicure.fine-dish.form.label.chef.assertion" path="chef.assertion" readonly="true"/>
		<acme:input-textbox code="epicure.fine-dish.form.label.chef.link" path="chef.link" readonly="true"/>
		<jstl:if test="${!published}">
			<acme:submit code="epicure.fine-dish.form.submit.update" action="/epicure/fine-dish/update"/>
			<acme:submit code="epicure.fine-dish.form.submit.publish" action="/epicure/fine-dish/publish"/>
			<acme:submit code="epicure.fine-dish.form.submit.delete" action="/epicure/fine-dish/delete"/>
		</jstl:if>
	</jstl:if>
	
	<jstl:if test="${command == 'create'}">
		<acme:submit code="epicure.fine-dish.form.submit.create" action="/epicure/fine-dish/create"/>
	</jstl:if>
</acme:form>


	